package softhealth.zombiecatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GameScreenHActivity extends Activity {

	String email;
	ImageView image1, image2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamescreenh);
		image1 = (ImageView) findViewById(R.id.gameScreenH_radar);

		image1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToScoreBoard();

			}
		});

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
}
