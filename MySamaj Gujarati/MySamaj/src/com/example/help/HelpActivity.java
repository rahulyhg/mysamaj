package com.example.help;

import android.os.Bundle;
import android.view.Menu;

import com.example.imagesqldata.R;
import com.example.navigationdrawer.BaseActivity;

public class HelpActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.help);
		getLayoutInflater().inflate(R.layout.help, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
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
