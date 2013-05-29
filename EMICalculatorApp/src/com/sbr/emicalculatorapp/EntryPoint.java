package com.sbr.emicalculatorapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sbr.emicalculatorapp.service.loan.client.DurationPeriod;
import com.sbr.emicalculatorapp.service.loan.client.EmiData;
import com.sbr.emicalculatorapp.service.loan.client.LoanType;
import com.sbr.emicalculatorapp.service.loan.client.TimePeriod;
import com.sbr.emicalculatorapp.service.loan.consumer.AsyncTaskManager;
import com.sbr.emicalculatorapp.service.loan.consumer.EMICalculatorServiceConsumer;
import com.sbr.emicalculatorapp.service.loan.consumer.OnAsyncTaskCompleteListener;
import com.sbr.emicalculatorapp.utils.StringUtils;

/**
 * Starting activity.
 * 
 * @author sbharathraja
 * 
 */
public class EntryPoint extends Activity {

	private AsyncTaskManager taskManager;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_point);
		populateLoanType();
		populateDurationPeriods();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry_point, menu);
		return true;
	}

	/**
	 * Helps to populate values on loan type spinner.
	 */
	private void populateLoanType() {
		Spinner spinnerLoanType = (Spinner) findViewById(R.id.spinnerLoanType);
		ArrayAdapter<LoanType> typeAdapter = new ArrayAdapter<LoanType>(this,
				android.R.layout.simple_spinner_item, LoanType.values());
		spinnerLoanType.setAdapter(typeAdapter);
	}

	/**
	 * Helps to populate the values in time periods spinner.
	 */
	private void populateDurationPeriods() {
		Spinner spinnerTimePeriods = (Spinner) findViewById(R.id.spinnerTimePeriod);
		ArrayAdapter<TimePeriod> typeAdapter = new ArrayAdapter<TimePeriod>(
				this, android.R.layout.simple_spinner_item, TimePeriod.values());
		spinnerTimePeriods.setAdapter(typeAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (taskManager == null) {
			taskManager = new AsyncTaskManager(this);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissDialog();
	}

	/**
	 * Helps to perform the event when submit button is clicked.
	 * 
	 * @param view
	 */
	public void onSubmit(View view) {

		String loanAmount = ((EditText) findViewById(R.id.textLoanAmount))
				.getText().toString();
		String loanType = ((Spinner) findViewById(R.id.spinnerLoanType))
				.getSelectedItem().toString();
		String durationPeriodString = ((EditText) findViewById(R.id.textDurationPeriod))
				.getText().toString();
		String timePeriodString = ((Spinner) findViewById(R.id.spinnerTimePeriod))
				.getSelectedItem().toString();

		if (StringUtils.isNullOrEmpty(loanAmount)) {
			displayNewDialog(constructBuilderForAlertDialog("Error in Fields",
					"Please enter the loan amount."));
			return;
		}

		if (StringUtils.isNullOrEmpty(durationPeriodString)) {
			displayNewDialog(constructBuilderForAlertDialog("Error in Fields",
					"Please enter the duration period in month or year"));
			return;
		}
		// since the text box is designed to accepting only numbers, we can
		// straight away convert it.
		DurationPeriod durationPeriod = new DurationPeriod(
				Integer.valueOf(durationPeriodString),
				TimePeriod.fromString(timePeriodString));
		// invoking the webservice to calculate the emi.
		invokeWebService(this, Double.valueOf(loanAmount), durationPeriod,
				LoanType.fromString(loanType));
	}

	/**
	 * Helps to invoke the webservice and processing the emi request.
	 */
	private void invokeWebService(final Context context,
			final double loanAmount, final DurationPeriod durationPeriod,
			final LoanType loanType) {
		// initialize the task manager.
		this.taskManager = new AsyncTaskManager(this);
		// create the task
		EMICalculatorServiceConsumer task = new EMICalculatorServiceConsumer();
		// execute the task within task manager...
		taskManager.executeTask(task, EMICalculatorServiceConsumer
				.createRequest(loanAmount, loanType, durationPeriod),
				getString(R.string.emi_calculation_in_progress),
				new OnAsyncTaskCompleteListener<EmiData>() {

					@Override
					public void onTaskCompleteSuccess(EmiData result) {
						// start the activity which is being designed for
						// showing results
						Intent intent = new Intent(context,
								ResultsActivity.class);
						// add the result data to intent.
						intent.putExtra(EmiData.class.getName(), result);
						// start the activity
						startActivity(intent);
					}

					@Override
					public void onTaskFailed(Exception cause) {
						Log.e(this.getClass().getName(), cause.getMessage(),
								cause);
						showToastMessage(R.string.emi_calculation_service_failed);
					}

				});
	}

	/**
	 * Helps to display a new alert dialog using given builder.
	 * 
	 * @param builder
	 */
	private void displayNewDialog(Builder builder) {
		dismissDialog();
		dialog = builder.create();
		dialog.show();
	}

	/**
	 * Helps to dismiss the dialog(if necessary) associated with this activity.
	 */
	private void dismissDialog() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	/**
	 * Helps to construct the builder with given information.
	 * 
	 * @param title
	 * @param messageContent
	 * @return instance of {@link Builder}
	 */
	private Builder constructBuilderForAlertDialog(String title,
			String messageContent) {
		Builder builder = new Builder(this);
		builder.setTitle(title);
		builder.setMessage(messageContent);
		builder.setIcon(android.R.drawable.ic_dialog_alert);

		builder.setPositiveButton("OK", null);

		return builder;
	}

	private void showToastMessage(int messageId) {
		Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
	}

}
