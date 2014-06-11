package softhealth.zombiecatch;

import java.io.IOException;

import softhealth.zombiecatch.userendpoint.Userendpoint;
import softhealth.zombiecatch.userendpoint.model.JsonMap;
import softhealth.zombiecatch.userendpoint.model.User;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

public class RegisterActivity extends Activity {

	private EditText email, password1, password2;
	private Button confirm;

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

		boolean isDataOk = checkData();

		if (isDataOk) {
			new AddUserTask().execute();

		}

	}

	public boolean checkData() {
		boolean res = true;

		return res;
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

			JsonMap hSkills = new JsonMap();
			hSkills.set("Strong", 0.0);
			hSkills.set("Perceptive", 0.0);

			user.setHskills(hSkills);

			JsonMap zSkills = new JsonMap();
			zSkills.set("Deadly", 0.0);
			zSkills.set("Sneaky", 0.0);

			user.setZskills(zSkills);
			

			Userendpoint.Builder builder = new Userendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			builder = CloudEndpointUtils.updateBuilder(builder);

			Userendpoint endpoint = builder.build();

			try {
				System.out.println("addind user");
				endpoint.insertUser(user).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

}
