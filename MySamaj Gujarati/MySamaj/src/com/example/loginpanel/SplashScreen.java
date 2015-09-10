package com.example.loginpanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.connectiondetector.ConnectionDetector;
import com.example.databasehandler.DBHelper;
import com.example.imagesqldata.R;
import com.example.updatedetails.WebServiceSpiner;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 500;
	private Boolean isInternetPresent = false;
	private DBHelper dbHelperVillage, dbHelperRelation, dbHelperMarital,
			dbHelperEducation, dbHelperJob;
	private String shakhvillageSpinerData, relationSpinerData,
			maritalSpinerData, educationSpinerData, jobSpinerData;
	ProgressBar progressBar;
	ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash_screen);
		cd = new ConnectionDetector(getApplicationContext());
		progressBar = (ProgressBar) findViewById(R.id.progressBarSplashScreen);

		dbHelperVillage = new DBHelper(getApplicationContext());
		dbHelperRelation = new DBHelper(getApplicationContext());
		dbHelperEducation = new DBHelper(getApplicationContext());
		dbHelperMarital = new DBHelper(getApplicationContext());
		dbHelperJob = new DBHelper(getApplicationContext());
		// clear Database value
		isInternetPresent = cd.isConnectingToInternet();

		// check for Internet status
		if (isInternetPresent) {

			/*dbHelperVillage.clearAll();
			dbHelperRelation.clearAll();
			dbHelperEducation.clearAll();
			dbHelperMarital.clearAll();
			dbHelperJob.clearAll();*/
		} else {
			// Internet connection is not present
			// Ask user to connect to Internet
			Toast.makeText(getApplicationContext(), "No Internet Connection",
					Toast.LENGTH_LONG).show();
		}
		SharedPreferences prefre = this.getSharedPreferences("firstt",
				Context.MODE_PRIVATE);
		boolean firsttimes = prefre.getBoolean("firstt", true);
		if (firsttimes) {
			new loadDataforSpinner().execute();
			SharedPreferences.Editor editere = prefre.edit();
			editere.putBoolean("firstt", false);
			editere.commit();
		}
		else {
			Intent i = new Intent(SplashScreen.this, CheckLoginActivity.class);
			startActivity(i);
			finish();
		}

	}

	public class loadDataforSpinner extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressBar.setIndeterminate(true);
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			// villageMaster
			shakhvillageSpinerData = WebServiceSpiner
					.invokeShakhVillageMaster("m_sptbShakhVillage_GetAllDataWithOther");
			try {
				JSONObject objS = new JSONObject(shakhvillageSpinerData);
				JSONArray jArrayS = objS.getJSONArray("Table");
				for (int i = 0; i < jArrayS.length(); i++) {
					JSONObject c = jArrayS.optJSONObject(i);
					String strVillageEN = c.getString("strVillageEN");
					String intVillageID = c.getString("intVillageID");
					dbHelperVillage.insertVillage(intVillageID, strVillageEN); // Inserting
				}
			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			// relationMaster
			relationSpinerData = WebServiceSpiner
					.invokeGenderMaster("m_sptbRelationMaster_GetAllData");
			try {
				JSONObject objR = new JSONObject(relationSpinerData);
				JSONArray jArrayR = objR.getJSONArray("Table");
				for (int i = 0; i < jArrayR.length(); i++) {
					JSONObject c = jArrayR.optJSONObject(i);

					String intRelationID = c.getString("intRelationID");
					String strRelationNameEN = c.getString("strRelationNameGJ");
					dbHelperRelation.insertRelation(intRelationID,
							strRelationNameEN); // Inserting
				}

			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();

			}

			// maritalMaster
			maritalSpinerData = WebServiceSpiner
					.invokeGenderMaster("m_sptbMaritalStatus_GetAllData");
			try {
				JSONObject objM = new JSONObject(maritalSpinerData);

				JSONArray jArrayM = objM.getJSONArray("Table");
				for (int i = 0; i < jArrayM.length(); i++) {
					JSONObject c = jArrayM.optJSONObject(i);

					String strMaritalStatusEN = c
							.getString("strMaritalStatusGJ");
					String intMaritalStatusID = c
							.getString("intMaritalStatusID");
					dbHelperMarital.insertMaritalStatus(intMaritalStatusID,
							strMaritalStatusEN); //

				}

			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();

			}
			// educationMaster
			educationSpinerData = WebServiceSpiner
					.invokeGenderMaster("m_sptbEducationMaster_GetAllData");
			try {
				JSONObject objE = new JSONObject(educationSpinerData);
				JSONArray jArrayE = objE.getJSONArray("Table");
				for (int i = 0; i < jArrayE.length(); i++) {
					JSONObject c = jArrayE.optJSONObject(i);
					String strEduENGJ = c.getString("strEduENGJ");
					String intEducationID = c.getString("intEduID");
					dbHelperEducation.insertEducation(intEducationID,
							strEduENGJ); // Inserting
				}
			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			// jobMaster
			jobSpinerData = WebServiceSpiner
					.invokeGenderMaster("m_sptbJobMaster_GetAllData");
			try {
				JSONObject objJ = new JSONObject(jobSpinerData);
				JSONArray jArrayJ = objJ.getJSONArray("Table");
				for (int i = 0; i < jArrayJ.length(); i++) {
					JSONObject c = jArrayJ.optJSONObject(i);
					String strJobEN = c.getString("strJobEN");
					String intJobID = c.getString("intJobID");
					dbHelperJob.insertJob(intJobID, strJobEN); // Inserting
				}
			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressBar.setIndeterminate(false);
			Intent i = new Intent(SplashScreen.this, CheckLoginActivity.class);
			startActivity(i);
			finish();

		}
	}
}
