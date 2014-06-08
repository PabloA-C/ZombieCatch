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

public class ScoreBoardActivity extends Activity {

	String userEmail;
	Button menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoreboard);
			 
		 Bundle extras = getIntent().getExtras();
		 
		 menu = (Button) findViewById(R.id.scoreboard_back);
		 
		 menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				goToMenu();
				
			}
		});
		 
		 if(extras != null){
			 
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

	
	
	
	public void goToMenu(){
		
		// Finish con result back to menu, the data should be already on the server.
		
	}

}
