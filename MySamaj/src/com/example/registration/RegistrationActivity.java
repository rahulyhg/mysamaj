package com.example.registration;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.imagesqldata.R;

public class RegistrationActivity extends Activity {
	EditText edtFirstName, edtMiddelName, edtSurname, edtMobileNo, edtEmailId;
	Spinner sprVillage, sprMosal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.registration_detail);
		edtFirstName = (EditText) findViewById(R.id.regFirstName);
		edtMiddelName = (EditText) findViewById(R.id.regMiddleName);
		edtSurname = (EditText) findViewById(R.id.regSurname);
		edtMobileNo = (EditText) findViewById(R.id.regMobileNo);
		edtEmailId = (EditText) findViewById(R.id.regMobileNo);
		sprVillage = (Spinner) findViewById(R.id.regVillageSpin);
		sprMosal = (Spinner) findViewById(R.id.regMosalSpin);
	}
}
