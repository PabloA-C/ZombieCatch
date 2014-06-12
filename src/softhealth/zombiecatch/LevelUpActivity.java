package softhealth.zombiecatch;

import java.io.IOException;
import java.util.List;

import softhealth.zombiecatch.userendpoint.Userendpoint;
import softhealth.zombiecatch.userendpoint.model.CollectionResponseUser;
import softhealth.zombiecatch.userendpoint.model.User;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
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

public class LevelUpActivity extends Activity {

	String userEmail;
	TextView hSP, zSP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levelup);

		Bundle extras = getIntent().getExtras();
		hSP = (TextView) findViewById(R.id.levelup_human_points);
		zSP = (TextView) findViewById(R.id.levelup_zombie_points);

		if (extras != null) {

			userEmail = extras.getString("theEmail");

		}

		new ListOfPlayerLocationAsyncRetriever().execute();
		
		

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

				} else {

				}

				return;
			}

			List<User> users = result.getItems();

			boolean itsCorrect = false;

			for (User u : users) {

				if (u.getEmail().equals(userEmail)) {

					double experience = u.getExperience();

					Integer skillpoints = 0;

					while (experience > 1000) {
						experience -= 1000;
						skillpoints++;
					}

					zSP.setText(skillpoints + " Points left.");
					hSP.setText(skillpoints + " Points left.");

				}

			}

		}
	}

}
