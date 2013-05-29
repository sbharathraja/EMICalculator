package com.sbr.emicalculatorapp.service.loan.consumer;

import android.os.AsyncTask;

/**
 * Provides call back methods to track the progress on {@link AsyncTask}
 * <p>
 * Thanks to the blogger <a href=
 * "http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-android/"
 * > http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-
 * android/</a> who provides the tutorial to access the webservice from android.
 * 
 * @author sbharathraja
 * 
 */
public interface IProgressTracker {

	/**
	 * Call back method to track the progress when start from {@link AsyncTask}
	 * implementation class.
	 */
	public void onStartProgress();

	/**
	 * Call back method to track the progress when stop from {@link AsyncTask}
	 * implementation class.
	 */
	public void onStopProgress();

}
