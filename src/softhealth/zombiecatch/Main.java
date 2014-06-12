package softhealth.zombiecatch;

import java.io.IOException;
import java.util.List;

import softhealth.zombiecatch.userendpoint.Userendpoint;
import softhealth.zombiecatch.userendpoint.model.CollectionResponseUser;
import softhealth.zombiecatch.userendpoint.model.User;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
	String status;

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
				login();
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
		email.setText("Juan");
		password.setText("e");
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the Send button */
	public void login() {
		status = "OK";
		if (email.getText().toString().isEmpty()
				|| password.getText().toString().isEmpty()) {
			status = "Incorrect Data";
			Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

		} else {

			new ListOfUserAsyncRetriever().execute();

		}

	}

	public void finishOperation(boolean itsCorrect) {

		if (itsCorrect) {

			goToMenu();

		} else {

			Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Toast.makeText(this, "test1",Toast.LENGTH_SHORT).show();
		if (data != null) {

			if (requestCode == 1) {

				if (resultCode == RESULT_OK) {

					Toast.makeText(this, "User registered", Toast.LENGTH_SHORT)
							.show();

				}

			}

		}

	}

	public void goToMenu() {
		Intent intent = new Intent(this, MenuActivity.class);

		String emailMessage = email.getText().toString();

		intent.putExtra("theEmail", emailMessage);

		startActivity(intent);

	}

	public void goToRegister(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);

		startActivityForResult(intent, 1);

	}

	private class ListOfUserAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponseUser> {

		@Override
		protected CollectionResponseUser doInBackground(Void... params) {

			Userendpoint.Builder endpointBuilder = new Userendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			CollectionResponseUser result;

			Userendpoint endpoint = endpointBuilder.build();

			try {
				result = endpoint.listUser().execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = null;
			}
			return result;
		}

		@Override
		@SuppressWarnings("null")
		protected void onPostExecute(CollectionResponseUser result) {

			if (result == null || result.getItems() == null
					|| result.getItems().size() < 1) {
				if (result == null) {
					status = "Retrieving users failed.";
				} else {
					status = "No users found. - ";
				}

				return;
			}

			List<User> users = result.getItems();

			boolean itsCorrect = false;

			for (User u : users) {

				if (u.getEmail().equals(email.getText().toString())) {

					if (u.getPassword().equals(password.getText().toString())) {

						itsCorrect = true;
					}
				}

			}

			finishOperation(itsCorrect);

		}
	}

}
