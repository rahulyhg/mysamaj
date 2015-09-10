package com.example.loginpanel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.navigationdrawer.BaseActivity;

public class LogoutActivity extends BaseActivity {

	private SharedPreferences SpFamilyId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		logout();
	}

	private void logout() {
		// TODO Auto-generated method stub
		Intent intnt = new Intent(this, CheckLoginActivity.class);
		intnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		SpFamilyId = getApplicationContext().getSharedPreferences("SpFamily",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = SpFamilyId.edit();
		editor.remove("logged");
		editor.commit();
		BaseActivity.position = 0;
		startActivity(intnt);
		finish();
		//getApplicationContext().deleteDatabase("spinnervillage.db"); //for database delete
	}

}
