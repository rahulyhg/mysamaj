package com.example.tabview;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.example.addressdetail.AddressActivity;
import com.example.imagesqldata.R;
import com.example.mysamajmain.AboutUs;
import com.example.mysamajmain.MainActivity;
import com.example.searchdetails.SearchDetailsActivity;

public class HomeTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_tab_view);

		Resources ressources = getResources();
		TabHost tabHost = getTabHost();

		// Android tab
		Intent intentAndroid = new Intent().setClass(this, MainActivity.class);
		Intent i = getIntent();
		String familyId = i.getStringExtra("familyId");

		intentAndroid.putExtra("familyId", familyId);
		TabSpec tabSpecAndroid = tabHost
				.newTabSpec("Family Details")
				.setIndicator("Family Details",
						ressources.getDrawable(R.drawable.logo))
				.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, AddressActivity.class);
		TabSpec tabSpecApple = tabHost
				.newTabSpec("Address")
				.setIndicator("Address",
						ressources.getDrawable(R.drawable.logo))
				.setContent(intentApple);
		// serach Tab
		Intent intentSearch = new Intent().setClass(this,
				SearchDetailsActivity.class);
		TabSpec tabSpecSearch = tabHost
				.newTabSpec("Directory")
				.setIndicator("Directory",
						ressources.getDrawable(R.drawable.logo))
				.setContent(intentSearch);

		// add all tabs
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecSearch);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.refresh:
			Toast.makeText(getApplicationContext(), "About US",
					Toast.LENGTH_LONG).show();
			Intent intObj = new Intent(HomeTabActivity.this, AboutUs.class);
			startActivity(intObj);

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
