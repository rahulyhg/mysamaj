package com.example.loginpanel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connectiondetector.ConnectionDetector;
import com.example.forgotpassword.ForgotPasswordActivity;
import com.example.imagesqldata.R;
import com.example.mysamajmain.MainActivity;
import com.example.registration.RegistrationActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class CheckLoginActivity extends Activity {
	// Set Error Status
	static boolean errored = false;
	Button btnLogin;
	private AdView mAdView;
	EditText userNameET, passWordET;
	ProgressBar webservicePG;
	String editTextUsername;
	private String loginStatus;
	String editTextPassword;
	TextView txtForgotPwd, registrationbtn;
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	private SharedPreferences SpFamilyId;

	// Connection detector class
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		cd = new ConnectionDetector(getApplicationContext());
		// Name Text control
		/*
		 * SpFamilyId = getApplicationContext().getSharedPreferences("SpFamily",
		 * Context.MODE_PRIVATE); if (SpFamilyId.getString("logged",
		 * "").toString().equals("logged")) { Intent intent = new
		 * Intent(CheckLoginActivity.this, MainActivity.class);
		 * startActivity(intent); }
		 */
		// values/strings.xml.
/*		mAdView = (AdView) findViewById(R.id.ad_view);
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(
				AdRequest.DEVICE_ID_EMULATOR).build();
		mAdView.loadAd(adRequest);
*/
		userNameET = (EditText) findViewById(R.id.etUserName);
		passWordET = (EditText) findViewById(R.id.etPass);
		txtForgotPwd = (TextView) findViewById(R.id.forgotPwdBtn);
		userNameET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				/*
				 * if (s.length() != 10) {
				 * txtPasswardLenth.setTextColor(Color.BLACK);
				 * txtPasswardLenth.setText("Enter 10 Digit Mobile No."); }
				 */
				Is_Valid_Sign_Number_Validation(10, 10, userNameET);
			}
		});

		userNameET.setText("");

		txtForgotPwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*
				 * Toast.makeText(CheckLoginActivity.this, "on forgot click",
				 * Toast.LENGTH_LONG).show();
				 */
				Intent intentForgptPwd = new Intent(getApplicationContext(),
						ForgotPasswordActivity.class);
				intentForgptPwd.putExtra("mobileNo", userNameET.getText()
						.toString());
				startActivity(intentForgptPwd);
			}
		});
		// Button to trigger web service invocation
		btnLogin = (Button) findViewById(R.id.button1);
		registrationbtn = (TextView) findViewById(R.id.registrationBtn);

		// Display progress bar until web service invocation completes
		webservicePG = (ProgressBar) findViewById(R.id.progressBar1);

		isInternetPresent = cd.isConnectingToInternet();

		// check for Internet status
		if (isInternetPresent) {
			Toast.makeText(getApplicationContext(), "Connected",
					Toast.LENGTH_SHORT).show();
		} else {
			// Internet connection is not present
			// Ask user to connect to Internet
			Toast.makeText(getApplicationContext(), "No Internet Connection",
					Toast.LENGTH_LONG).show();
			showAlertDialog(CheckLoginActivity.this, "No Internet Connection",
					"You don't have internet connection.", false);
		}
		registrationbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ireg = new Intent(getApplicationContext(),
						RegistrationActivity.class);
				// startActivity(ireg);
				Toast.makeText(getApplicationContext(), "Not yet", 2000).show();
			}
		});

		// Button Click Listener
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check if text controls are not empty
				if (userNameET.getText().length() != 0
						&& userNameET.getText().toString() != ""
						&& userNameET.getText().toString().length() == 10) {
					if (passWordET.getText().length() != 0
							&& passWordET.getText().toString() != "") {
						editTextUsername = userNameET.getText().toString();
						editTextPassword = passWordET.getText().toString();
						InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

						inputManager.hideSoftInputFromWindow(getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
						// Create instance for AsyncCallWS
						AsyncCallWS task = new AsyncCallWS();
						// Call execute
						task.execute();
					}
					// If Password text control is empty
					else {
						Toast.makeText(getApplicationContext(),
								"Please enter Password", Toast.LENGTH_LONG)
								.show();

					}
					// If Username text control is empty
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter Mobile No.", Toast.LENGTH_LONG)
							.show();

				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	/** Called when returning to the activity */
	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

	/** Called before the activity is destroyed */
	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}

	public void Is_Valid_Sign_Number_Validation(int MinLen, int MaxLen,
			EditText edt) throws NumberFormatException {
		if (edt.getText().toString().length() <= 0) {
			edt.setError("Number Only");
			editTextUsername = null;
		} else if (edt.getText().toString().length() < MinLen) {
			edt.setError("Minimum length " + MinLen);
			editTextUsername = null;

		} else if (edt.getText().toString().length() > MaxLen) {
			edt.setError("Maximum length " + MaxLen);
			editTextUsername = null;

		} else {
			InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			editTextUsername = edt.getText().toString();

		}

	} // END OF Edittext validation

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success
				: R.drawable.icon_error);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	private class AsyncCallWS extends AsyncTask<String, Void, Void> {

		@Override
		// Make Progress Bar visible
		protected void onPreExecute() {
			webservicePG.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			// Call Web Method
			loginStatus = WebServiceL.invokeLoginWS(editTextUsername,
					editTextPassword);
			return null;
		}

		@Override
		// Once WebService returns response
		protected void onPostExecute(Void result) {
			// Make Progress Bar invisible

			// Toast.makeText(getApplicationContext(), loginStatus,
			// 2000).show();

			webservicePG.setVisibility(View.INVISIBLE);

			Intent intObj = new Intent(CheckLoginActivity.this,
					MainActivity.class);
			// Error status is false
			if (!errored) {
				String[] fpIDArray = loginStatus.split(","); // [0] familyid [1]
																// personID

				int fID = Integer.parseInt(fpIDArray[0]);
				// Based on Boolean value returned from WebService
				if (loginStatus != null && fID != 0) {
					// Navigate to Home Screen
					// intObj.putExtra("familyId",fID);
					SpFamilyId = getApplicationContext().getSharedPreferences(
							"SpFamily", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = SpFamilyId.edit();
					editor.putString("logged", "logged");
					editor.putString("familyId", String.valueOf(fID));
					editor.putString("LoginPersonID", fpIDArray[1]);
					editor.commit();

					startActivity(intObj);
					userNameET.setText("");
					Toast.makeText(getApplicationContext(),
							"Welcome " + fpIDArray[2], Toast.LENGTH_LONG)
							.show();

				} else {
					// Set Error message
					Toast.makeText(getApplicationContext(),
							"Please Enter Correct Password", Toast.LENGTH_LONG)
							.show();
				}
				// Error status is true
			} else {
				Toast.makeText(getApplicationContext(),
						"Error occured in invoking webservice",
						Toast.LENGTH_LONG).show();
			}
			// Re-initialize Error Status to False
			errored = false;
			// userNameET.setText("");
			passWordET.setText("");
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
		if (menu != null) {
			menu.findItem(R.id.action_search).setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "Refreshing...",
					Toast.LENGTH_SHORT).show();
			if (editTextPassword != null && editTextUsername != null) {
				AsyncCallWS task = new AsyncCallWS();
				// Call execute
				task.execute();
			} else {
				userNameET.setText("");
				passWordET.setText("");
			}

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}
}
