package com.example.mysamajmain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databasehandler.DatabaseHandler;
import com.example.imagesqldata.R;
import com.example.model.PersonDetailsItem;
import com.example.model.SpinerItem;
import com.example.navigationdrawer.BaseActivity;
import com.example.updatedetails.UpdateDetailsActivity;

public class MainActivity extends BaseActivity {

	private Button btn;
	private String results, noFamilyDetailsStr;
	private ListView lv;
	private ProgressDialog mProgressDialog;
	private CustomAdapter adapter;
	private String familyNo = "", personId;
	private TextView tvNo;
	boolean doubleBackToExitPressedOnce = false;
	private ArrayList<PersonDetailsItem> listData = null;
	public static List<SpinerItem> spinerPersonData = null;
	DatabaseHandler handler;
	// Refresh menu item
	private MenuItem refreshMenuItem;
	private SharedPreferences SpfamilyId;

	// private String familyId = "1570";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);

		SpfamilyId = getApplicationContext().getSharedPreferences("SpFamily",
				Context.MODE_PRIVATE);
		familyNo = SpfamilyId.getString("familyId", familyNo);

		// familyNo = "864";
		tvNo = (TextView) findViewById(R.id.userNameTxt);

		tvNo.setText(familyNo);
		// database handler
		//handler = new DatabaseHandler(getApplicationContext());
		//handler.clearAll();

		lv = (ListView) findViewById(R.id.listView1);
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				String item = ((TextView) view.findViewById(R.id.hidden_value))
						.getText().toString();

				/*
				 * Toast.makeText(getApplicationContext(), "Click" + position +
				 * item, Toast.LENGTH_LONG).show();
				 */

			}

		});
		// Toast.makeText(getApplicationContext(), familyNo, 2000).show();
		new GetImageData().execute();

	}

	private class GetImageData extends AsyncTask<String, Void, Void> {

		// List<String> listData = new ArrayList<String>();

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			if (familyNo != null && familyNo != "0") {

				results = WebServicesD.invokeHelloWorldWS(familyNo);
				String rs = MainActivity.this.results;
				parseJson(rs);
			} else {
				noFamilyDetails();
			}
			return null;
		}

		private void parseJson(String rs) {
			// TODO Auto-generated method stub
			listData = new ArrayList<PersonDetailsItem>();
			spinerPersonData = new ArrayList<SpinerItem>();
			try {
				JSONObject obj = new JSONObject(rs);

				JSONArray jArray = obj.getJSONArray("Table");
				for (int i = 0; i < jArray.length(); i++) {

					JSONObject c = jArray.optJSONObject(i);
					personId = c.getString("intPersonID");
					String intFamilyNo = c.getString("intFamilyNo");
					String intIsFamilyHead = c.getString("intIsFamilyHead");
					String strSurnameEN = c.getString("strSurnameEN");
					String strNameEN = c.getString("strNameEN");
					String strFatherNameEN = c.getString("strFatherNameEN");
					/*
					 * Gujarati Details
					 */
					String strSurnameGJ = c.getString("strSurnameGJ");
					String strNameGJ = c.getString("strNameGJ");
					String strFatherNameGJ = c.getString("strFatherNameGJ");
					String strJobDetailGJ = c.getString("strJobDetailGJ");

					String intAge = c.getString("intAge");
					String intGender = c.getString("intGender");
					String intAddressID = c.getString("intAddressID");
					String intRelationID = c.getString("intRelationID");
					String intMaritalStatus = c.getString("intMaritalStatus");
					String intShakhID = c.getString("intShakhID");
					String intWardID = c.getString("intWardID");
					String intMosalEN = c.getString("intMosalEN");
					String strMosalOtherGJ = c.getString("strMosalOtherGJ");
					String strEducationEN = c.getString("strEducationEN");
					String intEducationEN = c.getString("intEducationEN");
					String intSem = c.getString("intSem");
					String intJobEN = c.getString("intJobEN");
					String strJobDetailEN = c.getString("strJobDetailEN");
					String strMobile = c.getString("strMobile");
					String strMobile2 = c.getString("strMobile2");
					String strFbLink = c.getString("strFbLink");
					String dtBirthDate = c.getString("dtBirthDate");
					String strEmailid = c.getString("strEmailid");
					String strProfileImage = c.getString("strProfileImage");
					String intVillageID = c.getString("intVillageID");
					// String strAddress1EN = c.getString("strAddress1EN");
					// String intMVillageID = c.getString("intMVillageID");

					PersonDetailsItem personItem = new PersonDetailsItem();
					personItem.setIntFamilyNo(intFamilyNo);
					personItem.setPersonId(personId);
					personItem.setIntIsFamilyHead(intIsFamilyHead);
					personItem.setStrSurnameEN(strSurnameEN);
					personItem.setStrNameEN(strNameEN);
					personItem.setStrFatherNameEN(strFatherNameEN);
					/*
					 * Gujarati Details
					 */
					personItem.setStrSurnameGJ(strSurnameGJ);
					personItem.setStrNameGJ(strNameGJ);
					personItem.setStrFatherNameGJ(strFatherNameGJ);
					personItem.setStrJobDetailGJ(strJobDetailGJ);

					personItem.setIntAge(intAge);
					personItem.setIntGender(intGender);
					personItem.setIntAddressID(intAddressID);
					personItem.setIntRelationID(intRelationID);
					personItem.setIntMaritalStatus(intMaritalStatus);
					personItem.setIntShakhID(intShakhID);
					personItem.setIntWardID(intWardID);
					personItem.setIntMosalEN(intMosalEN);
					personItem.setstrMosalOtherGJ(strMosalOtherGJ);
					personItem.setStrEducationEN(strEducationEN);
					personItem.setIntEducationEN(intEducationEN);
					personItem.setIntSem(intSem);
					personItem.setIntJobEN(intJobEN);
					personItem.setStrJobDetailEN(strJobDetailEN);
					personItem.setStrMobile(strMobile);
					personItem.setStrMobile2(strMobile2);
					personItem.setStrFbLink(strFbLink);
					personItem.setDtBirthDate(dtBirthDate);
					personItem.setStrEmailid(strEmailid);
					personItem.setStrProfileImage(strProfileImage);
					personItem.setIntVillageID(intVillageID);
					// personItem.setStrAddress1EN(strAddress1EN);
					// personItem.setIntMVillageID(intMVillageID);

					listData.add(personItem);
					if (Integer.parseInt(intIsFamilyHead) != 1) {
						spinerPersonData.add(new SpinerItem(personId,
								strSurnameGJ + " " + strNameGJ + " " + " "
										+ strFatherNameGJ));
					}
					/*handler.Add_Contact(new PersonDetailsItem(intFamilyNo,
							intIsFamilyHead, strSurnameEN, strNameEN,
							strFatherNameEN, strSurnameGJ, strNameGJ,
							strFatherNameGJ, strJobDetailGJ, intAge, intGender,
							intAddressID, intRelationID, intMaritalStatus,
							intShakhID, intWardID, intMosalEN, strMosalOtherGJ,
							strEducationEN, intEducationEN, intSem, intJobEN,
							strJobDetailEN, strMobile, strMobile2, strFbLink,
							dtBirthDate, strEmailid, strProfileImage,
							intVillageID));*/

				}

			} catch (JSONException e) { // TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("perosnJson Error", e.toString());

			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Display Details");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false); // Show progressdialog
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.show();

			// pb.setVisibility(View.INVISIBLE);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// pb.setVisibility(View.VISIBLE);
			mProgressDialog.dismiss();

			if (null != listData) {
				updateList();
			} else {
				Toast.makeText(getApplicationContext(), noFamilyDetailsStr,
						Toast.LENGTH_LONG).show();
			}

		}
	}

	public static List<SpinerItem> getPersonSpiner() {
		return spinerPersonData;
	}

	public void updateList() {
		// TODO Auto-generated method stub
		/*
		 * ArrayList<PersonDetailsItem> contactlist = new
		 * ArrayList<PersonDetailsItem>(); contactlist = handler.Get_Contacts();
		 */
		adapter = new CustomAdapter(MainActivity.this, R.id.listView1, listData);// contact
																					// list
																					// form
																					// database
																					// table
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Object personObject = lv.getItemAtPosition(position);
				PersonDetailsItem personItemData = (PersonDetailsItem) personObject;
				Intent personIdIntent = new Intent(getApplicationContext(),
						UpdateDetailsActivity.class);
				personIdIntent.putExtra("personObject", personItemData);
				startActivity(personIdIntent);
			}
		});
		if (refreshMenuItem != null && refreshMenuItem.getActionView() != null) {
			refreshMenuItem.getActionView().clearAnimation();
			refreshMenuItem.setActionView(null);
		}

	}

	public void noFamilyDetails() {
		// TODO Auto-generated method stub
		noFamilyDetailsStr = "No Family Details !";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
		/*
		 * if (menu != null) {
		 * menu.findItem(R.id.action_search).setVisible(false); }
		 */
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setQueryHint("Person Search");
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		// searchView.setIconifiedByDefault(false);

		SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String newText) {
				// this is your adapter that will be filtered

				adapter.getFilter().filter(newText);
				return true;

			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// this is your adapter that will be filtered

				adapter.getFilter().filter(query);
				return true;

			}
		};
		searchView.setOnQueryTextListener(textChangeListener);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.refresh:
			// refresh
			refreshMenuItem = item;
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ImageView iv = (ImageView) inflater.inflate(R.layout.iv_refresh,
					null);

			// ISSUE IS AT .loadAnimation not able to be implemented.
			Animation rotation = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.rotate_refresh);
			rotation.setRepeatCount(Animation.INFINITE);
			iv.startAnimation(rotation);
			item.setActionView(iv);
			// load the data from server
			new GetImageData().execute();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
