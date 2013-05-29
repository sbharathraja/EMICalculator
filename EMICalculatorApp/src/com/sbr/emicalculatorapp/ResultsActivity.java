package com.sbr.emicalculatorapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sbr.emicalculatorapp.service.loan.client.EmiData;

public class ResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		// Show the Up button in the action bar.
		setupActionBar();
		populateResultFields();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Helps to fill up the result ui fields
	 * 
	 */
	private void populateResultFields() {
		EmiData result = getResult();
		TextView textLoanAmount = (TextView) findViewById(R.id.textResultLoanAmount);
		textLoanAmount.setText(result.getLoanAmount());

		TextView textTotalInterestAmount = (TextView) findViewById(R.id.textResultInterestAmount);
		textTotalInterestAmount.setText(result.getTotalInterestAmount());

		TextView textInterestRate = (TextView) findViewById(R.id.textResultInterestRate);
		textInterestRate.setText(result.getInterestRate());

		TextView textEmi = (TextView) findViewById(R.id.textResultEmi);
		textEmi.setText(result.getEmi());
	}

	/**
	 * Helps to get the result attached to the intent.
	 * 
	 * @return instance of {@link EmiData}
	 */
	private EmiData getResult() {
		return (EmiData) getIntent().getSerializableExtra(
				EmiData.class.getName());
	}

}
