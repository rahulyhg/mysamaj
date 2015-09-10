package com.example.feedback;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagesqldata.R;
import com.example.navigationdrawer.BaseActivity;

public class FeedBackActivity extends BaseActivity {

	EditText edtFullName, edtMobileNo, edtEmailID, edtFeedback;
	Button feedbackSubmitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.feedback_report);
		getLayoutInflater().inflate(R.layout.feedback_report, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		edtFullName = (EditText) findViewById(R.id.fbFullNameedt);
		edtMobileNo = (EditText) findViewById(R.id.fbMobileNoedt);
		edtEmailID = (EditText) findViewById(R.id.fbEmailIdedt);
		edtFeedback = (EditText) findViewById(R.id.fbFeedbackedt);
		feedbackSubmitBtn = (Button) findViewById(R.id.feedbackSubmitBtn);
		feedbackSubmitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new SaveFeedback().execute();
			}
		});
	}

	public class SaveFeedback extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			String fullname = edtFullName.getText().toString();
			String mobileNo = edtMobileNo.getText().toString();
			String emailID = edtEmailID.getText().toString();
			String feedback = edtFeedback.getText().toString();
			WebServiceF.invokeHelloWorldWS("m_sptbFeedbackMaster_Insert",
					fullname, mobileNo, emailID, feedback);
			return null;
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
