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
import android.widget.Toast;

public class LoginActivity extends Activity {

	String email;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {

			email = extras.getString("theEmail");
			password = extras.getString("thePassword");

		}
		validate();

	}

	public boolean checkData() {
		boolean result = false;
	
		
		
		if (email.equals(password)) {
			result = true;
		}

		return result;
	}

	public void validate() {
		
		Intent intent = new Intent();
		String error=email+" is not the same as "+password;
		intent.putExtra("errorMessage", error);
		if (checkData()) {
			setResult(RESULT_OK, intent);
		} else {

			setResult(RESULT_CANCELED,intent);

		}
		finish();
	}

}
