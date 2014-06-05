package softhealth.zombiecatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {

	Button buttonLogin;
	Button buttonRegister;
	EditText email;
	EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonLogin = (Button) findViewById(R.id.main_button_login);
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Maybe call the activity "goToLoging" or something more
				// relevant
				goToLogin(null);
			}
		});

		buttonRegister = (Button) findViewById(R.id.main_button_register);
		buttonRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Maybe call the activity "goToLoging" or something more
				// relevant
				goToRegister(null);
			}
		});

		email = (EditText) findViewById(R.id.main_editText_email);
		password = (EditText) findViewById(R.id.main_editText_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the Send button */
	public void goToLogin(View view) {
		Intent intent = new Intent(this, LoginActivity.class);

		String emailMessage = email.getText().toString();
		String passwordMessage = password.getText().toString();

		intent.putExtra("theEmail", emailMessage);
		intent.putExtra("thePassword", passwordMessage);

		startActivityForResult(intent, 11111);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Toast.makeText(this, "test1",Toast.LENGTH_SHORT).show();
		if (data != null) {

			if (requestCode == 11111) {

				if (resultCode == RESULT_OK) {

					goToMenu(null);

				} else {
					Toast.makeText(this,
							data.getExtras().getString("errorMessage"),
							Toast.LENGTH_SHORT).show();

				}

			}

		}

	}

	public void goToMenu(View view) {
		Intent intent = new Intent(this, MenuActivity.class);

		String emailMessage = email.getText().toString();

		intent.putExtra("theEmail", emailMessage);

		startActivity(intent);

	}

	public void goToRegister(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);

		startActivity(intent);

	}

}
