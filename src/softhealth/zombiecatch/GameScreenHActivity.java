package softhealth.zombiecatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import softhealth.zombiecatch.playerendpoint.Playerendpoint;
import softhealth.zombiecatch.playerendpoint.Playerendpoint.UpdatePlayer;
import softhealth.zombiecatch.playerendpoint.model.CollectionResponsePlayer;
import softhealth.zombiecatch.playerendpoint.model.Player;
import softhealth.zombiecatch.userendpoint.Userendpoint.UpdateUser;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameScreenHActivity extends Activity implements LocationListener {

	private String email, gameTitle;
	private ImageView radar, image2;
	private double simLat, simLon;
	private double lat, lon;
	private double score = 0.0;
	private List<Double> lats;
	private List<Double> lons;
	private List<String> users;
	private List<Double> scores;
	private RelativeLayout rLayout;
	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamescreenh);

		lats = new ArrayList<Double>();
		lons = new ArrayList<Double>();
		users = new ArrayList<String>();
		scores = new ArrayList<Double>();

		rLayout = (RelativeLayout) findViewById(R.id.gameScreenH_rLayout);
		radar = (ImageView) findViewById(R.id.gameScreenH_radar);

		image2 = (ImageView) findViewById(R.id.gameScreenH_youAre);

		image2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				bitten();

			}
		});

		Bundle extras = getIntent().getExtras();

		if (extras != null) {

			email = extras.getString("theEmail");
			gameTitle = extras.getString("theGame");

		}

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {

			onLocationChanged(location);

		}

		ScheduledExecutorService scheduleTaskExecutor = Executors
				.newScheduledThreadPool(5);

		// This schedule a runnable task every 2 minutes
		scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
			public void run() {

				updatePlayers();

			}
		}, 0, 30, TimeUnit.SECONDS);

		// Más a la iz menor longitud
		// Más abajo menor latitud

		// 51.774091, 19.448061
		// 51.774238, 19.448759 (50 metros a la derecha)
		// 51.773653, 19.448190 (50 metros abajo)

		// X -> 50 metros = 150
		// Y -> 50 metros = 150

	}

	public void updatePlayers() {

		new ListOfPlayerAsyncRetriever().execute();
	//	new UpdatePlayerTask().execute();
	}

	public void refreshRadar() {

		while (rLayout.getChildCount() > 1) {

			rLayout.removeViewAt(2);

		}

		for (String user : users) {
			
			int index = users.indexOf(user);

			Double uLat = lats.get(index);
			Double uLon = lons.get(index);

			double diffLat = uLat - lat;
			double diffLon = uLon - lon;

			if (diffLat < 1) {
				diffLat = 0;
			}
			if (diffLon < 10) {
				diffLon = 0;
			}

			int dLat = (int) Math.floor((Math.abs(diffLat) ));
			int dLon = (int) Math.floor((Math.abs(diffLon) ));
			
			System.out.println(dLat +" "+ dLon);
		
			ImageView dot = new ImageView(this);
			dot.setImageResource(R.drawable.dot_blue);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(dLat + 150, dLon + 150, 0, 0);
			dot.setLayoutParams(lp);

			rLayout.addView(dot, index);
			System.out.println("Dot should be added");
					

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void goToScoreBoard() {

		Intent intent = new Intent(this, ScoreBoardActivity.class);

		String emailMessage = email;

		intent.putExtra("theEmail", emailMessage);

		startActivity(intent);

	}

	public void bitten() {

		Intent intent = new Intent(this, GameScreenZActivity.class);

		String emailMessage = email;

		intent.putExtra("theEmail", emailMessage);

		startActivity(intent);

	}

	private class ListOfPlayerAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponsePlayer> {

		@Override
		protected CollectionResponsePlayer doInBackground(Void... params) {

			Playerendpoint.Builder endpointBuilder = new Playerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			CollectionResponsePlayer result;

			Playerendpoint endpoint = endpointBuilder.build();

			try {
				result = endpoint.listPlayer().execute();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = null;
			}
			return result;
		}

		@Override
		@SuppressWarnings("null")
		protected void onPostExecute(CollectionResponsePlayer result) {

			if (result == null || result.getItems() == null
					|| result.getItems().size() < 1) {
				if (result == null) {

				} else {
				}

				return;
			}

			List<Player> players = result.getItems();

			users.clear();
			lats.clear();
			lons.clear();
			scores.clear();

			for (Player p : players) {

				if (p.getGameTitle().equals(gameTitle)) {

					if (p.getIsHuman()) {

						String pMail = p.getUserEmail();
						Double pLat = p.getUserLat();
						Double pLon = p.getUserLon();
						Double pScore = p.getScore();

						if (!(pMail.equals(email))) {

							if (!(users.contains(pMail))) {
								lats.add(pLat);
								lons.add(pLat);
								users.add(pMail);
								scores.add(pScore);

							} else {

								int index = users.indexOf(pMail);

								if (scores.get(index) < pScore) {

									lons.remove(index);
									lats.remove(index);
									scores.remove(index);

									lons.add(index, pLon);
									lats.add(index, pLat);
									scores.add(index, pScore);

								}

							}

						}
					}
				}

			}

			refreshRadar();

		}
	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 0, 0, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		// lat = location.getLatitude();
		// lon = location.getLongitude();

		lat = lat + 20;
		lon = lon + 20;

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	private class UpdatePlayerTask extends AsyncTask<Void, Void, Void> {

		/**
		 * Calls appropriate CloudEndpoint to indicate that user checked into a
		 * place.
		 * 
		 * @param params
		 *            the place where the user is checking in.
		 */
		@Override
		protected Void doInBackground(Void... params) {

			Player player = new Player();

			// Set the ID of the store where the user is.
			// This would be replaced by the actual ID in the final version of
			// the code.

			player.setGameTitle(gameTitle);
			player.setUserEmail(email);
			player.setUserLat(lat + 20);
			player.setUserLon(lon + 20);
			player.setScore(score + 2.0);
			score = player.getScore();
			lat = player.getUserLat();
			lon = player.getUserLon();
			player.setSneakLvl(0);
			player.setIsHuman(true);

			Playerendpoint.Builder builder = new Playerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			builder = CloudEndpointUtils.updateBuilder(builder);

			Playerendpoint endpoint = builder.build();

			try {
				endpoint.insertPlayer(player).execute();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

}
