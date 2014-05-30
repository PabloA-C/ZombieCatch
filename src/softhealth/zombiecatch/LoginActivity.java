package softhealth.zombiecatch;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	Button buttonConfirm;
	TextView viewEmail;
	TextView viewPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		viewEmail  = (TextView) findViewById(R.id.activity_login_textView_email);
		viewPassword  = (TextView) findViewById(R.id.activity_login_textView_password);
		buttonConfirm = (Button) findViewById(R.id.activity_login_button_go);
		buttonConfirm.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			goToMenu(v);
	    		}
	    	});
		 
		 Bundle extras = getIntent().getExtras();
		 
		 if(extras != null){
			 
			 viewEmail.setText(extras.getString("theEmail"));
			 viewPassword.setText(extras.getString("thePassword"));
			 
		 }

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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


	
	/** Called when the user clicks the Send button */    
    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
    
        String emailMessage = viewEmail.getText().toString();
        String passwordMessage = viewPassword.getText().toString();
        
        intent.putExtra("theEmail", emailMessage);
        intent.putExtra("thePassword",passwordMessage );
        
        startActivity(intent);
        
    }

}
