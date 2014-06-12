package softhealth.zombiecatch;

import android.app.Activity;
import android.content.Intent;
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

public class CreateActivity extends Activity {

	String userEmail;
	EditText title, pass;
	TextView size, maxP;
	SeekBar maxPlayers, radious;
	Button create;
	double playerN, fieldSize;

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

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToLobby();

			}
		});

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

	public void goToLobby() {

		Intent intent = new Intent(this, LobbyActivity.class);

		intent.putExtra("theEmail", userEmail);

		startActivity(intent);

	}

}
