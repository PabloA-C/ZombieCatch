package softhealth.zombiecatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import softhealth.zombiecatch.playerendpoint.Playerendpoint;
import softhealth.zombiecatch.playerendpoint.model.CollectionResponsePlayer;
import softhealth.zombiecatch.playerendpoint.model.Key;
import softhealth.zombiecatch.playerendpoint.model.Player;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

public class LobbyActivity extends Activity implements LocationListener {

	private String userEmail;
	private String gameTitle;
	private String userID;
	private Button ready, refresh;
	private double lat, lon;
	private LocationManager locationManager;
	private String provider;
	private boolean readyToGo = false;
	private boolean locationReady = false;
	private LinearLayout playerList;
	private List<String> playersName;
	private TextView waiting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);
		lat = 0.0;
		lon = 0.0;
		playersName = new ArrayList<String>();
		Bundle extras = getIntent().getExtras();

		waiting = (TextView) findViewById(R.id.lobby_waiting);
		ready = (Button) findViewById(R.id.lobby_button_ready);
		refresh = (Button) findViewById(R.id.lobby_button_refresh);
		playerList = (LinearLayout) findViewById(R.id.lobby_playerList);

		if (extras != null) {

			userEmail = extras.getString("theEmail");
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

		ready.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ready();
			}
		});

		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				refresh();

			}
		});

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

	public void loadPlayers() {
		new ListOfPlayerAsyncRetriever().execute();
	}

	public void printPlayers() {

		boolean playerLoaded = false;

		playerList.removeAllViews();

		if (playersName.contains(userEmail)) {
			waiting.setText("Players: ");
			readyToGo = true;

			for (String s : playersName) {

				TextView newPlayer = new TextView(this);
				newPlayer.setText(s);
				newPlayer.setTextAppearance(this, R.style.PlainText);
				newPlayer.setGravity(Gravity.CENTER);

				playerList.addView(newPlayer);

			}

		}

	}

	public void ready() {

		// CHECK if there are at least two players. check if you are the host to
		// start the game.
		// If you are not the host, checking the time left can help check if the
		// game has started or not.

		if (readyToGo) {

			goToGameScreen();

		} else {
			Toast.makeText(this, "Waiting", Toast.LENGTH_SHORT).show();
		}

	}

	public void refresh() {

		loadPlayers();

	}

	public void finishedOperations() {

		// CHECK if there are at least two players. check if you are the host to
		// start the game.
		// If you are not the host, checking the time left can help check if the
		// game has started or not.

		goToGameScreen();
	}

	public void goToGameScreen() {

		// set game time not 0

		Intent intent = new Intent(this, GameScreenHActivity.class);

		intent.putExtra("theEmail", userEmail);
		intent.putExtra("theGame", gameTitle);
		intent.putExtra("theID", userID);

		startActivity(intent);
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
		lat = location.getLatitude();
		lon = location.getLongitude();

		if (!locationReady) {
			locationReady = true;
			new AddPlayerTask().execute();
		}
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

	private class AddPlayerTask extends AsyncTask<Void, Void, Void> {

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
			player.setUserEmail(userEmail);
			player.setUserLat(lat);
			player.setUserLon(lon);
			player.setScore(0.0);
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

			loadPlayers();

			return null;
		}
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

			for (Player p : players) {

				if (p.getGameTitle().equals(gameTitle)) {

					playersName.add(p.getUserEmail());

					if (p.getUserEmail().equals(userEmail)) {

						userID = String.valueOf(p.getKey().getId());
					}

				}

			}
			printPlayers();

		}
	}

}
