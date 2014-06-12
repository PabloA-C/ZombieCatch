package softhealth.zombiecatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import softhealth.zombiecatch.gameendpoint.Gameendpoint;
import softhealth.zombiecatch.gameendpoint.model.CollectionResponseGame;
import softhealth.zombiecatch.gameendpoint.model.Game;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

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

public class JoinActivity extends Activity implements LocationListener {

	String userEmail;
	Button join;
	private LocationManager locationManager;
	private String provider;
	private double lat, lon;
	private String gameTitle;
	private List<String> gamesName;
	private LinearLayout gameList;
	private boolean ready = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);

		gamesName = new ArrayList<String>();
		gameTitle = "";
		join = (Button) findViewById(R.id.join_button);
		join.setText("Refresh");
		gameList = (LinearLayout) findViewById(R.id.join_gameList);
		join.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				join();

			}
		});

		Bundle extras = getIntent().getExtras();

		if (extras != null) {

			userEmail = extras.getString("theEmail");

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

		new ListOfGameAsyncRetriever().execute();
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

	public void join() {

		if (gamesName.isEmpty()) {

			// new ListOfGamesAsyncRetriever().execute();

		} else {

			if (ready) {
				goToLobby();
			} else {

				Toast.makeText(this, "Select", Toast.LENGTH_SHORT)
						.show();
			}

		}

	}

	public void printGames() {

		if (!gamesName.isEmpty()) {

			join.setText("Select a game.");

			for (String s : gamesName) {

				System.out.println("Printing " + s);

				TextView newGame = new TextView(this);

				newGame.setText(s);
				newGame.setTextAppearance(this, R.style.PlainText);
				newGame.setGravity(Gravity.CENTER);
				newGame.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						TextView tv = (TextView) v;
						gameTitle = tv.getText().toString();
						join.setText("Join " + gameTitle);
						ready = true;

					}
				});

				gameList.addView(newGame);

			}
		}

	}

	public void goToLobby() {

		Intent intent = new Intent(this, LobbyActivity.class);

		intent.putExtra("theEmail", userEmail);

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

	private class ListOfGameAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponseGame> {

		@Override
		protected CollectionResponseGame doInBackground(Void... params) {

			Gameendpoint.Builder endpointBuilder = new Gameendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			CollectionResponseGame result;

			Gameendpoint endpoint = endpointBuilder.build();

			try {
				result = endpoint.listGame().execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = null;
			}
			return result;
		}

		@Override
		@SuppressWarnings("null")
		protected void onPostExecute(CollectionResponseGame result) {

			if (result == null || result.getItems() == null
					|| result.getItems().size() < 1) {
				if (result == null) {

				} else {
				}

				return;
			}

			List<Game> Games = result.getItems();

			for (Game g : Games) {

				gamesName.add(g.getGameTitle());

			}

			printGames();

		}
	}

}
