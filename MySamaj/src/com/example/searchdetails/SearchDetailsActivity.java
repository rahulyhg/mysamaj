package com.example.searchdetails;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customespineradapter.CustomeSpinerAdapter;
import com.example.databasehandler.DBHelper;
import com.example.imagesqldata.R;
import com.example.model.PeopleDetailsItem;
import com.example.model.SpinerItem;
import com.example.navigationdrawer.BaseActivity;

public class SearchDetailsActivity extends BaseActivity {

	static private Context appContext;
	private DBHelper dbHelper;
	boolean doubleBackToExitPressedOnce = false;
	Button btnSearch;
	private ListView lvPeople;
	private String result, shakhvillageSpinerData, intVillageID,
			villageId = "";
	private Spinner searchVillageSpiner;
	private List<SpinerItem> dbVillage = null;
	private String digits, resultVillageData;
	ProgressDialog mProgressDialog;
	// Flag for current page
	int current_page = 1;
	private ArrayList<PeopleDetailsItem> peoplelistData = null;
	private MenuItem refreshMenuItem;
	CustomSearchListAdapter adapter;
	boolean disableButtonFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.search_details);
		getLayoutInflater().inflate(R.layout.search_details, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		/*
		 * ActionBar actionBar = getActionBar();
		 * actionBar.setDisplayHomeAsUpEnabled(true);
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		lvPeople = (ListView) findViewById(R.id.peopleListView);
		lvPeople.setTextFilterEnabled(true);

		searchVillageSpiner = (Spinner) findViewById(R.id.SearchSpiner);

		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// new GetVillageShakh().execute();

				Toast.makeText(getApplicationContext(), villageId, 2000).show();
				new personDetailsByVillageId().execute();

			}
		});

		dbHelper = new DBHelper(getApplicationContext());

		// handleIntent(getIntent());

		new GetVillageShakh().execute();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		// handleIntent(getIntent());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dbHelper.close();
	}

	public class personDetailsByVillageId extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(SearchDetailsActivity.this);
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
			try {
				if (villageId != null && villageId != "") {
					resultVillageData = WebServiceSPVillage
							.invokeHelloWorldWS(villageId);
					peopleJson(resultVillageData);

				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("PeppleError", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			mProgressDialog.dismiss();

			if (null != peoplelistData) {
				updatePeopleList();
			}
		}
	}

	private class GetVillageShakh extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				dbVillage = dbHelper.listSelectAllVillage();

			} catch (Exception e) {
				// TODO: handle exception
				Log.v("dbVillageError", e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			CustomeSpinerAdapter shakhAdapter = new CustomeSpinerAdapter(
					getApplicationContext(), R.layout.custom_spiner_row,
					dbVillage);
			shakhAdapter.notifyDataSetChanged();
			searchVillageSpiner.setAdapter(shakhAdapter);
			searchVillageSpiner
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int position, long arg3) {
							// TODO Auto-generated method stub
							/*
							 * SpinerItem selected = shakhVillageListData
							 * .get(position); villageId =
							 * selected.getSpinerId();
							 */
							SpinerItem selected = dbVillage.get(position);
							villageId = selected.getSpinerId();
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}

					});
		}
	}

	public void updatePeopleList() {
		// TODO Auto-generated method stub
		adapter = new CustomSearchListAdapter(SearchDetailsActivity.this,
				R.layout.people_list_row, peoplelistData);
		lvPeople.setAdapter(adapter);

		adapter.notifyDataSetChanged();

	}

	public void peopleJson(String resultVillageData) {
		// TODO Auto-generated method stub
		peoplelistData = new ArrayList<PeopleDetailsItem>();
		try {
			JSONObject obj = new JSONObject(resultVillageData);

			JSONArray jArray = obj.getJSONArray("Table");
			for (int i = 0; i < jArray.length(); i++) {

				JSONObject c = jArray.optJSONObject(i);
				String intIsFamilyHead = c.getString("intIsFamilyHead");
				String personId = c.getString("intPersonID");
				String intFamilyNo = c.getString("intFamilyNo");
				String NameEN = c.getString("NameEN");
				String AGE = c.getString("AGE");
				String Gender = c.getString("Gender");
				String AddressEN = c.getString("AddressEN");
				String RelationEN = c.getString("RelationEN");
				String MaritalStatusEN = c.getString("MaritalStatusEN");
				String ShakhEN = c.getString("ShakhEN");
				String MosalEN = c.getString("MosalEN");
				String strEducationEN = c.getString("strEducationEN");
				String JobDetailEN = c.getString("JobDetailEN");
				String Mobile = c.getString("Mobile");
				String Mobile2 = c.getString("Mobile2");
				String strEmialID = c.getString("strEmialID");
				String Member = c.getString("Member");

				PeopleDetailsItem peopleItem = new PeopleDetailsItem();
				peopleItem.setIntIsFamilyHead(intIsFamilyHead);
				peopleItem.setIntFamilyNo(intFamilyNo);
				peopleItem.setPersonId(personId);
				peopleItem.setIntFamilyNo(intFamilyNo);
				peopleItem.setNameEN(NameEN);
				peopleItem.setAGE(AGE);
				peopleItem.setGender(Gender);
				peopleItem.setAddressEN(AddressEN);
				peopleItem.setRelationEN(RelationEN);
				peopleItem.setMaritalStatusEN(MaritalStatusEN);
				peopleItem.setShakhEN(ShakhEN);
				peopleItem.setMosalEN(MosalEN);
				peopleItem.setStrEducationEN(strEducationEN);
				peopleItem.setJobDetailEN(JobDetailEN);
				peopleItem.setMobile(Mobile);
				peopleItem.setMobile2(Mobile2);
				peopleItem.setStrEmialID(strEmialID);
				peopleItem.setMember(Member);

				peoplelistData.add(peopleItem);
			}

		} catch (JSONException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
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

				/*
				 * if (villageId != null && villageId != "") {
				 * adapter.getFilter().filter(newText);
				 * System.out.println("on text chnge text: " + newText); return
				 * true; } else {
				 * 
				 * Toast.makeText(getBaseContext(), "No Data", 2000).show();
				 * return false; }
				 */
				if (disableButtonFlag) {
					menu.findItem(R.id.action_search).setEnabled(true);
					adapter.getFilter().filter(newText);
					return true;
				} else {
					menu.findItem(R.id.action_search).setEnabled(false);
					return false;
				}
				// adapter.getFilter().filter(newText);
				// return true;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// this is your adapter that will be filtered
				if (dbVillage != null) {
					adapter.getFilter().filter(query);
					System.out.println("on query submit: " + query);
					return true;
				} else {
					Toast.makeText(getBaseContext(), "No Data", 2000).show();
					return false;
				}

			}
		};
		searchView.setOnQueryTextListener(textChangeListener);

		// return super.onCreateOptionsMenu(menu);
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
			new personDetailsByVillageId().execute();

			return true;
		case R.id.action_search:
			if (villageId == "" && villageId == null) {
				disableButtonFlag = false;
				Toast.makeText(getApplicationContext(), "Village Not Selected",
						Toast.LENGTH_SHORT).show();
			} else {
				disableButtonFlag = true;
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
