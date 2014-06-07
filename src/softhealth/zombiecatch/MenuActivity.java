package softhealth.zombiecatch;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MenuActivity extends Activity {

	String userEmail;
	Button create, join, profile, howto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		create = (Button) findViewById(R.id.menu_button_create);
		join = (Button) findViewById(R.id.menu_button_join);
		profile = (Button) findViewById(R.id.menu_button_profile);
		howto = (Button) findViewById(R.id.menu_button_howto);

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToCreate();

			}
		});

		join.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToJoin();

			}
		});

		profile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToProfile();

			}
		});

		howto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToHowto();

			}
		});
		
		Bundle extras = getIntent().getExtras();

		if (extras != null) {

			userEmail = extras.getString("theEmail");

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

	public void goToCreate() {

	}

	public void goToJoin() {

		Intent intent = new Intent(this, JoinActivity.class);

		intent.putExtra("theEmail", userEmail);

		startActivity(intent);
		
	}

	public void goToProfile() {
		Intent intent = new Intent(this, ProfileActivity.class);

		intent.putExtra("theEmail", userEmail);

		startActivity(intent);
		
		
	}

	public void goToHowto() {

		Intent intent = new Intent(this, HowToActivity.class);

		intent.putExtra("theEmail", userEmail);

		startActivity(intent);
		
	}

}
