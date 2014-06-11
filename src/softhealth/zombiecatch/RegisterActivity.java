package softhealth.zombiecatch;

import java.io.IOException;
import java.util.List;

import softhealth.zombiecatch.userendpoint.Userendpoint;
import softhealth.zombiecatch.userendpoint.model.CollectionResponseUser;
import softhealth.zombiecatch.userendpoint.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

public class RegisterActivity extends Activity {

	private EditText email, password1, password2;
	private Button confirm;
	public String status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		email = (EditText) findViewById(R.id.register_enterEmail);
		password1 = (EditText) findViewById(R.id.register_enterPassword);
		password2 = (EditText) findViewById(R.id.register_enterPasswordAgain);
		confirm = (Button) findViewById(R.id.register_buttonConfirm);

		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				registerUser();

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

	public void registerUser() {
		status = "OK";
		if (email.getText().toString().isEmpty()
				|| password1.getText().toString().isEmpty()
				|| password2.getText().toString().isEmpty()
				|| !(password1.getText().toString().equals(password2.getText()
						.toString()))) {
			status = "Incorrect Data";
			Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

		} else {

			new ListOfPlayerLocationAsyncRetriever().execute();

		}

	}

	public void finishOperation(boolean itsRepeated) {

		if (!itsRepeated) {

			new AddUserTask().execute();
			
			Intent intent = new Intent();
			
			setResult(RESULT_OK, intent);

			finish();

		} else {
			Toast.makeText(this, "This email is already being used.", Toast.LENGTH_SHORT).show();
		}

	}

	private class AddUserTask extends AsyncTask<Void, Void, Void> {

		/**
		 * Calls appropriate CloudEndpoint to indicate that user checked into a
		 * place.
		 * 
		 * @param params
		 *            the place where the user is checking in.
		 */
		@Override
		protected Void doInBackground(Void... params) {

			User user = new User();

			// Set the ID of the store where the user is.
			// This would be replaced by the actual ID in the final version of
			// the code.

			user.setEmail(email.getText().toString());
			user.setPassword(password1.getText().toString());
			user.setExperience(0.0);
			user.setLvlStrong(0);
			user.setLvlPerceptive(0);
			user.setLvlDeadly(0);
			user.setLvlSneaky(0);

			Userendpoint.Builder builder = new Userendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			builder = CloudEndpointUtils.updateBuilder(builder);

			Userendpoint endpoint = builder.build();

			try {
				endpoint.insertUser(user).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

	private class ListOfPlayerLocationAsyncRetriever extends
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

			boolean itsRepeated = false;
			
			for (User u : users) {

				System.out.println(u.getEmail()+" and "+email.getText().toString());
				
				if (u.getEmail().equals(email.getText().toString())) {
				System.out.println("entered");
					itsRepeated = true;
				}

			}

			finishOperation(itsRepeated);

		}
	}

}
