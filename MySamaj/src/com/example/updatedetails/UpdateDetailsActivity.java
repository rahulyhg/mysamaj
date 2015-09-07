package com.example.updatedetails;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customespineradapter.CustomeSpinerAdapter;
import com.example.databasehandler.DBHelper;
import com.example.deletedetails.WebServiceDeleteDetails;
import com.example.imagesqldata.R;
import com.example.imagload.ImageLoader;
import com.example.model.PersonDetailsItem;
import com.example.model.SpinerItem;
import com.example.mysamajmain.MainActivity;

public class UpdateDetailsActivity extends Activity {

	private DBHelper dbHelperVillage, dbHelperRelation, dbHelperMarital,
			dbHelperEducation, dbHelperJob;
	private String personId, relationSpinerData, maritalSpinerData,
			educationSpinerData, shakhvillageSpinerData, jobSpinerData, wardId,
			shakhId, resultUpdate, IntFamilyNo, IntVillageIdImage;
	private ArrayList<SpinerItem> dbrelation, dbmarital, dbeducation, dbjob,
			dbVillage;
	private String surName, name, fatherName, genderSelectedId, mobileNo1,
			mobileNo2, jobDetails, jobId, emailId, fbUrl, Dob, relationId,
			mosalId, mosalId2, maritalStatusId, jobTYpeId, educationId,
			educationId2, mosalOther = "", LoginPersonId = "0",
			familyHead = "0";
	private String intRelationID, intMaritalStatusID, intEducationID,
			intVillageID, intJobID, strEducationEN, newFamilyHeadId = "",
			deleterReason = "", intStatus = "3";
	private String intMaritalStatus;
	private String strJobDetailEN;
	private String resonSpinnerValue = "";
	EditText edtupdSurname, edtupdName, edtupdFatherName, edtupdDoB,
			edtipdJobDetails, edtupdMobileNo, edtupdOtherMobile, edtupdEmailId,
			edtupdFacebookUrl, edtupdtMosalOther;
	RadioGroup radioSexGroup;
	RadioButton radioSexButton;
	Spinner spRalation, spMosal, spMaritalStatus, spEducation, spJobType;
	Button btnUpdate, btnDelete;
	TextView tvPID;
	private ProgressDialog mProgressDialog;
	private ImageButton imgProfileBtn;
	int REQUEST_CAMERA = 0, SELECT_FILE = 1;
	private PersonDetailsItem personDetialItem;
	private SharedPreferences spLoginPersonId;
	private Calendar myCalendar;
	private String image64String;
	private MenuItem refreshMenuItem;
	private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_details);

		spLoginPersonId = UpdateDetailsActivity.this.getSharedPreferences(
				"SpFamily", Context.MODE_PRIVATE);
		LoginPersonId = spLoginPersonId.getString("LoginPersonID",
				LoginPersonId);

		imgProfileBtn = (ImageButton) findViewById(R.id.profileImageUpdate);
		new ArrayList<SpinerItem>();

		personDetialItem = (PersonDetailsItem) this.getIntent()
				.getSerializableExtra("personObject");

		edtupdSurname = (EditText) findViewById(R.id.updSurname);
		edtupdName = (EditText) findViewById(R.id.updName);
		edtupdName.addTextChangedListener(new TextWatcher() {

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
				name = Is_Valid_Sign_Name_Validation(edtupdName);
			}
		});
		edtupdFatherName = (EditText) findViewById(R.id.updFatherName);
		edtupdFatherName.addTextChangedListener(new TextWatcher() {

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
				fatherName = Is_Valid_Sign_Name_Validation(edtupdFatherName);
			}
		});

		edtupdDoB = (EditText) findViewById(R.id.updDoB);

		edtipdJobDetails = (EditText) findViewById(R.id.updJobDetailsEdt);
		edtupdMobileNo = (EditText) findViewById(R.id.updMobileNoEdt);
		edtupdOtherMobile = (EditText) findViewById(R.id.udpOtherMobileNo);
		edtupdEmailId = (EditText) findViewById(R.id.updEmailIdEdt);
		edtupdEmailId.addTextChangedListener(new TextWatcher() {

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
				Is_Valid_Sign_Email_Validation(30, 30, edtupdEmailId);
			}
		});
		edtupdFacebookUrl = (EditText) findViewById(R.id.updFaceBookUrlEdt);
		edtupdtMosalOther = (EditText) findViewById(R.id.otherMosal);
		radioSexGroup = (RadioGroup) findViewById(R.id.radioSexGroup);
		spRalation = (Spinner) findViewById(R.id.updRalationSpin);
		spMosal = (Spinner) findViewById(R.id.updMosalSpin);
		spMaritalStatus = (Spinner) findViewById(R.id.updMaritalstatusSpin);
		spEducation = (Spinner) findViewById(R.id.updEducationSpin);
		spJobType = (Spinner) findViewById(R.id.udpJobTypeSpin);

		tvPID = (TextView) findViewById(R.id.txtPersonIdUpdate);
		tvPID.setText(personId);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete = (Button) findViewById(R.id.btnDelete);

		myCalendar = Calendar.getInstance();
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}

		};

		edtupdDoB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(UpdateDetailsActivity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		imgProfileBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Select Image", 2000)
						.show();
				selectImage();
			}
		});

		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// new GetPersonDetails().execute();
				// String pID = tvPID.getText().toString();
				Drawable drawable = imgProfileBtn.getDrawable();

				BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
				Bitmap bitmap = bitmapDrawable.getBitmap();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				byte[] imageInByte = stream.toByteArray();
				image64String = Base64.encodeToString(imageInByte,
						Base64.DEFAULT);

				surName = edtupdSurname.getText().toString().trim();
				// name = edtupdName.getText().toString();
				// fatherName = edtupdFatherName.getText().toString(); // age
				String genderId = ((RadioButton) findViewById(radioSexGroup
						.getCheckedRadioButtonId())).getText().toString();
				if (genderId.endsWith("Male")) {
					genderSelectedId = "1";
				} else if (genderId.equals("Female")) {
					genderSelectedId = "2";
				}
				mobileNo1 = edtupdMobileNo.getText().toString();
				mobileNo2 = edtupdOtherMobile.getText().toString();
				jobDetails = edtipdJobDetails.getText().toString();
				emailId = edtupdEmailId.getText().toString();
				fbUrl = edtupdFacebookUrl.getText().toString();
				Dob = edtupdDoB.getText().toString();
				mosalOther = edtupdtMosalOther.getText().toString();
				Toast.makeText(
						getApplicationContext(),
						Dob + "-" + personId + "-" + IntVillageIdImage + "-"
								+ IntFamilyNo, 2000).show();

				if (edtupdSurname.getText().toString().length() == 0) {
					edtupdSurname.setError("Surname is required!");
					Toast.makeText(UpdateDetailsActivity.this,
							"Surname is required!", Toast.LENGTH_SHORT).show();
				} else if (edtupdName.getText().toString().length() == 0) {
					edtupdName.setError("Name is required!");
					Toast.makeText(UpdateDetailsActivity.this,
							"Name is required!", Toast.LENGTH_SHORT).show();
				} else if (edtupdFatherName.getText().toString().length() == 0) {
					edtupdFatherName.setError("Father Name is required!");
					Toast.makeText(UpdateDetailsActivity.this,
							"Father name is required!", Toast.LENGTH_SHORT)
							.show();
				} else if (edtupdMobileNo.getText().toString().length() == 0) {
					edtupdMobileNo.setError("Mobile Number is required!");
					Toast.makeText(UpdateDetailsActivity.this,
							"Mobile Number is required!", Toast.LENGTH_SHORT)
							.show();
				} else {

					new SetUpdatedPersonDetails().execute();
				}

			}
		});
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openDialog();
			}

		});
		// Toast.makeText(getApplicationContext(), "Long Press", 2000).show();

		dbHelperVillage = new DBHelper(getApplicationContext());
		dbHelperRelation = new DBHelper(getApplicationContext());
		dbHelperEducation = new DBHelper(getApplicationContext());
		dbHelperMarital = new DBHelper(getApplicationContext());
		dbHelperJob = new DBHelper(getApplicationContext());
		new GetPersonDetails().execute();
	}

	private void openDialog() {

		String[] resons = { "reason1", "reason2", "reason3", "Other" };
		// TODO Auto-generated method stub

		AlertDialog.Builder adb = new AlertDialog.Builder(
				UpdateDetailsActivity.this);
		adb.setTitle("Confirmation for Delete Details?");
		adb.setMessage("Enter Reason");
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = inflater.inflate(R.layout.dialogbox_spinner, null);
		final EditText edtReason = (EditText) dialogView
				.findViewById(R.id.deleteReason);
		edtReason.setText("");
		adb.setView(dialogView);

		// final String user_id = personDetialItem.getPersonId().toString();
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// MyDataObject.remove(positionToRemove);
				new personDeleteDataById().execute();
				deleterReason = resonSpinnerValue
						+ edtReason.getEditableText().toString();
				Toast.makeText(getApplicationContext(),
						personId + " " + deleterReason + " " + newFamilyHeadId,
						2000).show();
			}
		});
		adb.show();

		TextView txtDeleteReson = (TextView) dialogView
				.findViewById(R.id.selectFamilyHeadTxt);
		Typeface face1 = Typeface.createFromAsset(getAssets(), "Shruti.ttf");
		txtDeleteReson.setTypeface(face1);
		final Spinner resonSpinner = (Spinner) dialogView
				.findViewById(R.id.resonSpinner);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, resons);
		resonSpinner.setAdapter(spinnerArrayAdapter);
		resonSpinnerValue = resonSpinner.getSelectedItem().toString();

		resonSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 3) {
					edtReason.setVisibility(View.VISIBLE);
				} else {
					edtReason.setVisibility(View.GONE);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		// edt.setText(familyHead);
		if (Integer.parseInt(familyHead) == 0) {
			txtDeleteReson.setVisibility(View.GONE);
		} else {
			txtDeleteReson.setVisibility(View.VISIBLE);
			Spinner dialogSpinner = (Spinner) dialogView
					.findViewById(R.id.dialogBoxSpinner);
			final List<SpinerItem> getlist = MainActivity.getPersonSpiner();

			CustomeSpinerAdapter educationAdapter = new CustomeSpinerAdapter(
					getApplicationContext(), R.layout.custom_spiner_row,
					getlist);

			dialogSpinner.setAdapter(educationAdapter);
			dialogSpinner
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							SpinerItem spnewFamilyItemId = getlist
									.get(position);

							if (Integer.parseInt(personId) == Integer
									.parseInt(spnewFamilyItemId.getSpinerId())) {
								Toast.makeText(getApplicationContext(),
										"You can't select Deleting Person",
										Toast.LENGTH_LONG).show();
							} else {
								newFamilyHeadId = spnewFamilyItemId
										.getSpinerId();
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub

						}

					});

		}

	}

	public class personDeleteDataById extends AsyncTask<String, Void, Void> {
		String statusRecordDelete;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			if (newFamilyHeadId == "") {
				statusRecordDelete = WebServiceDeleteDetails
						.invokeDeleteDetailsWS(personId, intStatus,
								deleterReason,
								"m_sptbPersonDetailMaster_UpdateDeleteRecordByID");
			} else {

				statusRecordDelete = WebServiceDeleteDetails
						.invokeDeleteDetailsSetFamilyHead(personId, intStatus,
								deleterReason, newFamilyHeadId,
								"m_sptbPersonDetailMaster_UpdateDeleteRecordByID_SetNewFamilyHead");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), statusRecordDelete,
					Toast.LENGTH_SHORT).show();
			Intent deleteBackIntent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(deleteBackIntent);
		}
	}

	public String Is_Valid_Sign_Name_Validation(EditText edt)
			throws NumberFormatException {
		String edtValue = "";
		if (edt.getText().toString().length() >= 3) {
			// edt.setError("Enter Detail");
			edtValue = edt.getEditableText().toString().trim();
		} else {
			edt.setError("Enter Detail");
			edtValue = null;

		}

		return edtValue;
	} // END OF Edittext validation

	public void Is_Valid_Sign_Email_Validation(int MinLen, int MaxLen,
			EditText edt) throws NumberFormatException {
		if (edt.getText().toString().trim().matches(emailPattern)) {
			emailId = edt.getText().toString().trim();
		} else {
			edt.setError("Enter Valid Email Id !");
			emailId = null;

		}
	} // END OF Edittext validation

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		edtupdDoB.setText(sdf.format(myCalendar.getTime()));
	}

	private class SetUpdatedPersonDetails extends AsyncTask<String, Void, Void> {
		String imagedata;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			mProgressDialog = new ProgressDialog(UpdateDetailsActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Updating Details");
			// Set progressdialog message
			mProgressDialog.setMessage("Please Wait...");
			mProgressDialog.setIndeterminate(false); // Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			resultUpdate = WebServiceUpdateDetails.invokeUpdateDetailsWS(
					personId, surName, name, fatherName, genderSelectedId,
					mobileNo1, mobileNo2, jobDetails, emailId, fbUrl, Dob,
					relationId, mosalId, maritalStatusId, jobTYpeId,
					educationId, strEducationEN, shakhId, wardId, mosalOther,
					LoginPersonId, "m_sptbPersonalDetailMaster_Update");
			imagedata = WebServiceUpdateDetails.invokeUpdateImagesWS(personId,
					IntVillageIdImage, IntFamilyNo, image64String,
					"m_sptbPersonDetailMaster_UpdateProfileImagePath");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			/*
			 * if (resultUpdate == "0") {
			 * Toast.makeText(UpdateDetailsActivity.this,
			 * "Details Updated Successfully", Toast.LENGTH_LONG) .show(); }
			 * else {
			 */

			if (imagedata == "true") {

				Toast.makeText(getApplicationContext(),
						"Image Uploaded Successfully ! ", 2000).show();
			}
			Toast.makeText(UpdateDetailsActivity.this, "Data Updated !",
					Toast.LENGTH_SHORT).show();
			Intent updateBackIntent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(updateBackIntent);
		}
	}

	private class GetPersonDetails extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			mProgressDialog = new ProgressDialog(UpdateDetailsActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Display Details");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false); // Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			// resultData = WebServiceUP.invokeHelloWorldWS(personId);

			getSpinnerData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			/*
			 * Toast.makeText(getApplicationContext(), relationSpinerData, 2000)
			 * .show();
			 */

			personId = personDetialItem.getPersonId();
			String strSurnameEN = personDetialItem.getStrSurnameEN();
			String strNameEN = personDetialItem.getStrNameEN();
			String strFatherNameEN = personDetialItem.getStrFatherNameEN();
			String intAge = personDetialItem.getIntAge();
			String intGender = personDetialItem.getIntGender();
			intRelationID = personDetialItem.getIntRelationID();
			personDetialItem.getIntMosalEN();
			intMaritalStatusID = personDetialItem.getIntMaritalStatus();
			educationId2 = personDetialItem.getIntEducationEN();
			String intSem = personDetialItem.getIntSem();
			jobId = personDetialItem.getIntJobEN();
			strJobDetailEN = personDetialItem.getStrJobDetailEN();
			String strMobile = personDetialItem.getStrMobile();
			String strMobile2 = personDetialItem.getStrMobile2();
			String strEmailid = personDetialItem.getStrEmailid();
			String dtBirthDate = personDetialItem.getDtBirthDate();
			mosalId2 = personDetialItem.getIntMosalEN();
			shakhId = personDetialItem.getIntShakhID();
			wardId = personDetialItem.getIntWardID();
			IntFamilyNo = personDetialItem.getIntFamilyNo();
			// IntVillageIdImage = personDetialItem.getIntShakhID();
			IntVillageIdImage = personDetialItem.getIntVillageID();
			familyHead = personDetialItem.getIntIsFamilyHead();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss", Locale.US);
			try {
				myCalendar.setTime(format.parse(dtBirthDate));
				updateLabel();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String strProfileImage = personDetialItem.getStrProfileImage();
			String intAddressID = personDetialItem.getIntAddressID();
			String strFbLink = personDetialItem.getStrFbLink();
			switch (Integer.parseInt(intGender)) {
			case 1:
				radioSexGroup.check(R.id.radioMale);
				break;
			case 2:
				radioSexGroup.check(R.id.radioFemale);
				break;

			default:
				break;
			}
			edtupdSurname.setText(strSurnameEN);
			edtupdName.setText(strNameEN);
			edtupdFatherName.setText(strFatherNameEN);
			edtupdMobileNo.setText(strMobile);
			edtupdOtherMobile.setText(strMobile2);
			edtipdJobDetails.setText(strJobDetailEN);
			edtupdEmailId.setText(strEmailid);
			edtupdFacebookUrl.setText(strFbLink);

			int loader = R.drawable.no_image;
			String imageURL = "http://mysamaj.co.in/" + strProfileImage;
			ImageLoader imgLoader = new ImageLoader(UpdateDetailsActivity.this);
			imgLoader.DisplayImage(imageURL, loader, imgProfileBtn);

			setSpinnderData();
		}

	}

	public void getSpinnerData() {

		// TODO Auto-generated method stub
		dbrelation = dbHelperRelation.listSelectAllRelation();
		dbVillage = dbHelperVillage.listSelectAllVillage();
		dbjob = dbHelperJob.listSelectAllJob();
		dbeducation = dbHelperEducation.listSelectAllEducation();
		dbmarital = dbHelperMarital.listSelectAllMarital();

	}

	public void setSpinnderData() {
		// TODO Auto-generated method stub
		// dbRelation

		CustomeSpinerAdapter myAdapter = new CustomeSpinerAdapter(
				getApplicationContext(), R.layout.custom_spiner_row, dbrelation);
		spRalation.setAdapter(myAdapter);
		for (int i = 0; i < myAdapter.getCount(); i++) {
			SpinerItem spItem = dbrelation.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(intRelationID)) {
				spRalation.setSelection(i);

				break;
			}
		}
		spRalation.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				SpinerItem selected = dbrelation.get(position);
				relationId = selected.getSpinerId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		// maritalMaster
		CustomeSpinerAdapter maritalAdapter = new CustomeSpinerAdapter(
				UpdateDetailsActivity.this, R.layout.custom_spiner_row,
				dbmarital);

		spMaritalStatus.setAdapter(maritalAdapter);
		for (int i = 0; i < maritalAdapter.getCount(); i++) {
			SpinerItem spItem = dbmarital.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(intMaritalStatusID)) {
				spMaritalStatus.setSelection(i);

				break;
			}
		}
		spMaritalStatus.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				SpinerItem selected = dbmarital.get(position);
				maritalStatusId = selected.getSpinerId();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		// dbEducation
		CustomeSpinerAdapter educationAdapter = new CustomeSpinerAdapter(
				getApplicationContext(), R.layout.custom_spiner_row,
				dbeducation);

		spEducation.setAdapter(educationAdapter);
		for (int i = 0; i < educationAdapter.getCount(); i++) {
			SpinerItem spItem = dbeducation.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(educationId2)) {
				spEducation.setSelection(i);

				break;
			}
		}
		spEducation.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				SpinerItem selected = dbeducation.get(position);
				educationId = selected.getSpinerId();
				strEducationEN = selected.getSpinerName();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		// dbJob
		CustomeSpinerAdapter jobAdapter = new CustomeSpinerAdapter(
				getApplicationContext(), R.layout.custom_spiner_row, dbjob);

		spJobType.setAdapter(jobAdapter);
		for (int i = 0; i < jobAdapter.getCount(); i++) {
			SpinerItem spItem = dbjob.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(jobId)) {
				spJobType.setSelection(i);

				break;
			}
		}
		spJobType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				SpinerItem selected = dbjob.get(position);
				jobTYpeId = selected.getSpinerId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		// dbVillage

		CustomeSpinerAdapter shakhAdapter = new CustomeSpinerAdapter(
				getApplicationContext(), R.layout.custom_spiner_row, dbVillage);

		spMosal.setAdapter(shakhAdapter);
		for (int i = 0; i < shakhAdapter.getCount(); i++) {
			SpinerItem spItem = dbVillage.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(mosalId2)) {
				spMosal.setSelection(i);

				break;
			}
		}
		spMosal.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				SpinerItem selected = dbVillage.get(position);
				mosalId = selected.getSpinerId();
				if (Integer.parseInt(mosalId) == 68) {
					edtupdtMosalOther.setVisibility(View.VISIBLE);
				} else {
					edtupdtMosalOther.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				UpdateDetailsActivity.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, REQUEST_CAMERA);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							SELECT_FILE);
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_FILE)
				onSelectFromGalleryResult(data);
			else if (requestCode == REQUEST_CAMERA)
				onCaptureImageResult(data);
		}
	}

	private void onCaptureImageResult(Intent data) {
		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

		File destination = new File(Environment.getExternalStorageDirectory(),
				System.currentTimeMillis() + ".jpg");

		FileOutputStream fo;
		try {
			destination.createNewFile();
			fo = new FileOutputStream(destination);
			fo.write(bytes.toByteArray());
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		imgProfileBtn.setImageBitmap(thumbnail);
	}

	@SuppressWarnings("deprecation")
	private void onSelectFromGalleryResult(Intent data) {
		Uri selectedImageUri = data.getData();
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
				null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();

		String selectedImagePath = cursor.getString(column_index);

		Bitmap bm;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(selectedImagePath, options);
		final int REQUIRED_SIZE = 200;
		int scale = 1;
		while (options.outWidth / scale / 2 >= REQUIRED_SIZE
				&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
			scale *= 2;
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(selectedImagePath, options);

		imgProfileBtn.setImageBitmap(bm);
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
			refreshMenuItem = item;
			new GetPersonDetails().execute();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
