package com.example.forgotpassword;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imagesqldata.R;

public class ForgotPasswordActivity extends Activity {

	private EditText edtMobileNo;
	private Button forgotPwdBtn;
	private String MobileNo, editTextUsername, randomNumber, loginDetails,
			intPersonID = "", strPassword = "", passwordFinal = "";
	private String[] loginDetailsArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);
		edtMobileNo = (EditText) findViewById(R.id.etUserNameForogt);

		Intent iget = getIntent();
		MobileNo = iget.getStringExtra("mobileNo");
		edtMobileNo.setText(MobileNo);

		edtMobileNo.addTextChangedListener(new TextWatcher() {

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
				Is_Valid_Sign_Number_Validation(10, 10, edtMobileNo);
			}
		});
		forgotPwdBtn = (Button) findViewById(R.id.forgotPwdBtn);
		forgotPwdBtn.setEnabled(true);

		forgotPwdBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (edtMobileNo.getText().length() != 0
						&& edtMobileNo.getText().toString() != ""
						&& edtMobileNo.getText().toString().length() == 10) {

					editTextUsername = edtMobileNo.getText().toString();
					Timer buttonTimer = new Timer();
					buttonTimer.schedule(new TimerTask() {

						@Override
						public void run() {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									forgotPwdBtn.setEnabled(false);
									forgotPwdBtn.setText("Watting...");
								}
							});
						}
					}, 300);
					// Create instance for AsyncCallWS
					new SentPasswordUpadted().execute();

				}

				// If Username text control is empty
				else {
					Toast.makeText(getApplicationContext(),
							"Please enter Mobile No.", Toast.LENGTH_LONG)
							.show();

				}
			}
		});
	}

	public class SentPasswordUpadted extends AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			Random random = new Random();
			int num = random.nextInt(900000) + 100000;
			randomNumber = String.valueOf(num);
			Toast.makeText(getApplicationContext(), "Generating... Password",
					Toast.LENGTH_LONG).show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			loginDetails = WebServiceFPassword.invokeWebMethod(
					editTextUsername, "m_sptbClientLogin_GetUserByUserMobile");

			loginDetailsArray = loginDetails.split(",");
			intPersonID = loginDetailsArray[0];
			strPassword = loginDetailsArray[1];
			if (strPassword.equalsIgnoreCase("0")) {
				WebServiceFPassword.invokeGeneratePassword(intPersonID,
						randomNumber, "m_sptbClientLogin_UpdatePassword");
			}

			requestingPassword();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			/*
			 * Toast.makeText( getApplicationContext(), intPersonID + " " +
			 * strPassword + " " + passwordFinal + " " + strpasswordLength + " "
			 * + loginDetails, 2000) .show();
			 */

			if (intPersonID.equalsIgnoreCase("0")) {
				Toast.makeText(getApplicationContext(),
						"Enter Correct Mobile No!", Toast.LENGTH_LONG).show();
				edtMobileNo.setText("");
				forgotPwdBtn.setEnabled(true);
				forgotPwdBtn.setText("Generate Password");
			} else {

				Toast.makeText(getApplicationContext(),
						"Password Sent to:" + editTextUsername,
						Toast.LENGTH_LONG).show();
				super.onPostExecute(result);

				Timer buttonTimer = new Timer();
				buttonTimer.schedule(new TimerTask() {

					@Override
					public void run() {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								edtMobileNo.setText("");
								forgotPwdBtn.setEnabled(true);
								forgotPwdBtn.setText("Generate Password");
							}
						});
					}
				}, 300);
			}

		}

	}

	public void requestingPassword() {
		// TODO Auto-generated method stub
		if (strPassword.equalsIgnoreCase("0")) {
			passwordFinal = randomNumber;
		} else {
			passwordFinal = strPassword;
		}
		String msg = "Dear User Your Password for www.mysamaj.co.in is - "
				+ passwordFinal;
		String myurl = "http://panel.msgclub.net/api/sendhttp.php";
		URL url;
		HttpClient httpClient = new DefaultHttpClient();
		// replace with your url
		HttpPost httpPost = new HttpPost(myurl);

		// Post Data
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("authkey",
				"783A1kyRnUkMy5U54d30fc0"));
		nameValuePair.add(new BasicNameValuePair("mobiles", editTextUsername));
		nameValuePair.add(new BasicNameValuePair("message", msg));
		nameValuePair.add(new BasicNameValuePair("sender", "MYSMAJ"));
		nameValuePair.add(new BasicNameValuePair("route", "4"));

		// Encoding POST data
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch (UnsupportedEncodingException e) {
			// log exception
			e.printStackTrace();
		}

		// making POST request.
		try {
			HttpResponse response = httpClient.execute(httpPost);
			// write response to log
			Log.d("Http Post Response:", response.toString());
		} catch (ClientProtocolException e) {
			// Log exception
			e.printStackTrace();
		} catch (IOException e) {
			// Log exception
			e.printStackTrace();
		}

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
}
