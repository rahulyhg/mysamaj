package com.example.addressdetail;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imagesqldata.R;
import com.example.navigationdrawer.BaseActivity;

public class AddressActivity extends BaseActivity {

	private TextView addressNotAval;
	private SharedPreferences prefs;
	private String result, addressId, strAddress1EN, strAddress2EN,
			strAddress3EN, strPincode, strVillageID, wardSpinerDataA,
			addressIdUpdate, address1, address2, address3, pinCodeEdt,
			updateID, address1GJ, address2GJ, address3GJ;
	private String strAddress1GJ, strAddress2GJ, strAddress3GJ;
	private ProgressDialog mProgressDialog;
	private MenuItem refreshMenuItem;
	EditText addressEdt1, addressEdt2, addressEdt3, pinCode;
	Button btnAddUpdate;
	private List<String> wardListDataA;
	private Spinner wardDataSpinner;
	private Typeface myFont;
	private EditText strAddressGJ1, strAddressGJ2, strAddressGJ3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String intAddressID = "";
		// setContentView(R.layout.address_detail);

		getLayoutInflater().inflate(R.layout.address_detail, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);

		prefs = getApplicationContext().getSharedPreferences("spa",
				Context.MODE_PRIVATE);
		addressId = prefs.getString("intAddressID", intAddressID);

		wardListDataA = new ArrayList<String>();
		wardDataSpinner = (Spinner) findViewById(R.id.wardDataSpinner);
		btnAddUpdate = (Button) findViewById(R.id.add_save_btn);
		addressNotAval = (TextView) findViewById(R.id.addressNotAvalTxt);
		addressEdt1 = (EditText) findViewById(R.id.address1);
		addressEdt2 = (EditText) findViewById(R.id.address2);
		addressEdt3 = (EditText) findViewById(R.id.address3);
		pinCode = (EditText) findViewById(R.id.pinCode);
		myFont = Typeface.createFromAsset(getAssets(), "Shruti.ttf");
		strAddressGJ1 = (EditText) findViewById(R.id.strAddressGJ1);
		strAddressGJ2 = (EditText) findViewById(R.id.strAddressGJ2);
		strAddressGJ3 = (EditText) findViewById(R.id.strAddressGJ3);
		strAddressGJ1.setTypeface(myFont);
		strAddressGJ2.setTypeface(myFont);
		strAddressGJ3.setTypeface(myFont);

		btnAddUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				address1 = addressEdt1.getText().toString();
				address2 = addressEdt2.getText().toString();
				address3 = addressEdt3.getText().toString();
				pinCodeEdt = pinCode.getText().toString();
				address1GJ = strAddressGJ1.getText().toString();
				address2GJ = strAddressGJ2.getText().toString();
				address3GJ = strAddressGJ3.getText().toString();

				Toast.makeText(getApplicationContext(),
						address1GJ + " " + address2GJ + " " + address3GJ, 2000)
						.show();
				new addressUpdate().execute();

			}
		});

		new GetAddress().execute();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Toast.makeText(getApplicationContext(), "on resume", 2000).show();

		// new GetAddress().execute();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// Toast.makeText(getApplicationContext(), "on pause", 2000).show();
	}

	public class addressUpdate extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				updateID = WebServiceA.invokeAddressUpdateWS(addressId,
						address1GJ, address2GJ, address3GJ, pinCodeEdt,
						strVillageID, "m_sptbAddressMaster_Update");
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("AddressupdError", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Toast.makeText(getApplicationContext(), "Address Updated", 2000)
					.show();

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

	}

	public class GetAddress extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			mProgressDialog = new ProgressDialog(AddressActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Display Details");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false); // Show progressdialog
			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			try {
				result = WebServiceA.invokeAddressWS(addressId,
						"m_sptbAddressMaster_GetByAddressId");
				// wardSpinerDataA = WebServiceSpiner
				// .invokeGenderMaster("m_sptbWardMaster_GetAllData");
				gettingAddressDetails();
			} catch (Exception e) {
				// TODO: handle exception
				Log.v("AddressError", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void results) {
			// TODO Auto-generated method stub
			// super.onPostExecute(results);
			mProgressDialog.dismiss();
			settingAddressDetails();
			// Toast.makeText(getApplicationContext(), result, 2000).show();

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

	public void settingAddressDetails() {
		// TODO Auto-generated method stub
		/*
		 * ArrayAdapter<String> myAdapter = new CustomeSpinerAdapter(
		 * getApplicationContext(), R.layout.custom_spiner_row, 1,
		 * wardListDataA);
		 * 
		 * wardDataSpinner.setAdapter(myAdapter);
		 */
		if (result != null) {
			addressEdt1.setText(strAddress1EN);
			addressEdt2.setText(strAddress2EN);
			addressEdt3.setText(strAddress3EN);
			pinCode.setText(strPincode);
			strAddressGJ1.setText(strAddress1GJ);
			strAddressGJ2.setText(strAddress2GJ);
			strAddressGJ3.setText(strAddress3GJ);

		} else {
			addressNotAval.setText("Address Not Found");
		}

	}

	public void gettingAddressDetails() {
		// TODO Auto-generated method stub
		try {
			JSONObject obj = new JSONObject(result);

			JSONArray jArray = obj.getJSONArray("Table");
			for (int i = 0; i < jArray.length(); i++) {

				JSONObject c = jArray.optJSONObject(i);

				strAddress1EN = c.getString("strAddress1EN");
				strAddress2EN = c.getString("strAddress2EN");
				strAddress3EN = c.getString("strAddress3EN");
				strPincode = c.getString("strPincode");
				strVillageID = c.getString("intVillageID");
				strAddress1GJ = c.getString("strAddress1GJ");
				strAddress2GJ = c.getString("strAddress2GJ");
				strAddress3GJ = c.getString("strAddress3GJ");
				/*
				 * + c.getString("intVillageID") + " " +
				 * c.getString("intTalukaID") + " " +
				 * c.getString("intDistrictID");
				 */
			}
		} catch (JSONException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("Error", e.toString());

		}
		try {
			JSONObject objW = new JSONObject(wardSpinerDataA);

			JSONArray jArrayW = objW.getJSONArray("Table");
			for (int i = 0; i < jArrayW.length(); i++) {
				JSONObject c = jArrayW.optJSONObject(i);

				String strWardEN = c.getString("strWardEN")
						+ c.getString("intWardID");

				wardListDataA.add(strWardEN);

			}

		} catch (JSONException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.refresh:
			// refresh
			refreshMenuItem = item;
			Toast.makeText(getApplicationContext(), "Refreshing...",
					Toast.LENGTH_SHORT).show();
			// load the data from server
			new GetAddress().execute();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
