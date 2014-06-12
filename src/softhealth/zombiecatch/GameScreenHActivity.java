package softhealth.zombiecatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import softhealth.zombiecatch.playerendpoint.Playerendpoint;
import softhealth.zombiecatch.playerendpoint.model.CollectionResponsePlayer;
import softhealth.zombiecatch.playerendpoint.model.Player;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GameScreenHActivity extends Activity {

	private String email, gameTitle;
	private ImageView radar, image2;
	private double simLat, simLon;
	private List<Double> lats;
	private List<Double> lons;
	private RelativeLayout rLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamescreenh);

		lats = new ArrayList<Double>();
		lons = new ArrayList<Double>();

		// Más a la iz menor longitud
		// Más abajo menor latitud

		// 51.774091, 19.448061
		// 51.774238, 19.448759 (50 metros a la derecha)
		// 51.773653, 19.448190 (50 metros abajo)

		// X -> 50 metros = 150
		// Y -> 50 metros = 150

		simLat = 51.774238;
		simLon = 19.448759;

		rLayout = (RelativeLayout) findViewById(R.id.gameScreenH_rLayout);
		radar = (ImageView) findViewById(R.id.gameScreenH_radar);

		radar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new ListOfPlayerAsyncRetriever().execute();

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
			gameTitle = extras.getString("theGame");
		}

		new ListOfPlayerAsyncRetriever().execute();

	}

	public void refreshRadar() {

		// <ImageView
		// android:id="@+id/imageView1"
		// android:layout_width="wrap_content"
		// android:layout_height="wrap_content"
		// android:layout_marginLeft="0dp"
		// android:layout_marginTop="0dp"
		// android:layout_alignParentTop="true"
		// android:src="@drawable/dot_blue" />

		ImageView dot = new ImageView(this);
		dot.setImageResource(R.drawable.dot_blue);
		
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(150, 150, 0, 0);
		dot.setLayoutParams(lp);
		
		rLayout.addView(dot, 1);
		
		

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

	private class ListOfPlayerAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponsePlayer> {

		@Override
		protected CollectionResponsePlayer doInBackground(Void... params) {

			Playerendpoint.Builder endpointBuilder = new Playerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			CollectionResponsePlayer result;

			Playerendpoint endpoint = endpointBuilder.build();

			try {
				result = endpoint.listPlayer().execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = null;
			}
			return result;
		}

		@Override
		@SuppressWarnings("null")
		protected void onPostExecute(CollectionResponsePlayer result) {

			if (result == null || result.getItems() == null
					|| result.getItems().size() < 1) {
				if (result == null) {

				} else {
				}

				return;
			}

			List<Player> players = result.getItems();

			lats.clear();
			lons.clear();

			for (Player p : players) {

				if (p.getGameTitle().equals(gameTitle)) {

					if (p.getIsHuman()) {

						lats.add(p.getUserLat());
						lons.add(p.getUserLon());

					}

				}

			}
			refreshRadar();

		}
	}

}
