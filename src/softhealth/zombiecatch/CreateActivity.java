package softhealth.zombiecatch;

import java.io.IOException;

import softhealth.zombiecatch.gameendpoint.Gameendpoint;
import softhealth.zombiecatch.gameendpoint.model.Game;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

public class CreateActivity extends Activity implements LocationListener {

	private String userEmail;
	private EditText title, pass;
	private TextView size, maxP;
	private SeekBar maxPlayers, radious;
	private Button create;
	private Integer playerN;
	private double fieldSize;
	private double gameLat, gameLon;
	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);

		playerN = 2;
		fieldSize = 50;

		create = (Button) findViewById(R.id.create_buttonCreate);
		title = (EditText) findViewById(R.id.create_inputTitle);
		pass = (EditText) findViewById(R.id.create_inputPassword);
		maxPlayers = (SeekBar) findViewById(R.id.create_seekbar_maxPlayers);
		radious = (SeekBar) findViewById(R.id.create_seekbar_fieldSize);
		maxP = (TextView) findViewById(R.id.create_maxPlayers);
		size = (TextView) findViewById(R.id.create_size);
		maxP.setText("Maximum players: 2");
		size.setText("Field size: 50 m");

		create.setText("Waiting for GPS");
		create.setTextColor(Color.DKGRAY);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {

			userEmail = extras.getString("theEmail");

		}

		maxPlayers.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				int valorReducido = (int) Math.round(2 + progress / 3.6);

				maxP.setText("Maximum players: " + valorReducido);
			}
		});

		radious.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				int valorReducido = (int) Math.round(50 + progress * 3);

				size.setText("Field size: " + valorReducido + " m");
			}
		});

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				createGame();

			}
		});

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

	public void ready() {
		create.setText("Create game");
		create.setTextColor(Color.WHITE);
	}

	public void createGame() {

		if (!(title.getText().toString().isEmpty())) {

			if (!(create.getText().equals("Create game"))) {
				Toast.makeText(this, "Waiting for GPS location.",
						Toast.LENGTH_SHORT);

			} else {

				new AddGameTask().execute();
			}

		} else {
			Toast.makeText(this, "Please enter a title.", Toast.LENGTH_SHORT)
					.show();
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
		gameLat = location.getLatitude();
		gameLon = location.getLongitude();
		ready();

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

	private class AddGameTask extends AsyncTask<Void, Void, Void> {

		/**
		 * Calls appropriate CloudEndpoint to indicate that user checked into a
		 * place.
		 * 
		 * @param params
		 *            the place where the user is checking in.
		 */
		@Override
		protected Void doInBackground(Void... params) {

			Game game = new Game();

			// Set the ID of the store where the user is.
			// This would be replaced by the actual ID in the final version of
			// the code.

			// chopLat, chopLon, timeLeft;

			game.setGameTitle(title.getText().toString());
			game.setPass(pass.getText().toString());
			game.setHostEmail(userEmail);
			game.setMaxPlayers(playerN);
			game.setRad(fieldSize);
			game.setLat(gameLat);
			game.setLon(gameLon);
			game.setChopLat(0.0);
			game.setChopLon(0.0);
			game.setTimeLeft(1000.0);

			Gameendpoint.Builder builder = new Gameendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			builder = CloudEndpointUtils.updateBuilder(builder);

			Gameendpoint endpoint = builder.build();

			try {
				endpoint.insertGame(game).execute();
				goToLobby();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}
}
