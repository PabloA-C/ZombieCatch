package softhealth.zombiecatch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	Button buttonConfirm;
	EditText email;
	EditText password;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonConfirm = (Button) findViewById(R.id.main_button_confirm);
        buttonConfirm.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			//Maybe call the activity "goToLoging" or something more relevant 
    			goToLogin(v);
    		}
    	});
        
        email  = (EditText) findViewById(R.id.main_editText_email);
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
        
        startActivity(intent);
        
    }
 
}
