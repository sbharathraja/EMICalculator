package com.sbr.emicalculatorapp.service.loan.consumer;

/**
 * Type which act as the observer for executing the logic asynchronously.
 * <p>
 * Thanks to the blogger <a href=
 * "http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-android/"
 * > http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-
 * android/</a> who provides the tutorial to access the webservice from android.
 * 
 * @author sbharathraja
 * @param <T>
 * 
 */
public interface OnAsyncTaskCompleteListener<T> {

	/**
	 * Callback method that get invoked when the web service task is
	 * successfully completed.
	 * 
	 * @param result
	 */
	public void onTaskCompleteSuccess(T result);

	/**
	 * Callback method that get invoked if the task failed.
	 * 
	 * @param cause
	 *            {@link Exception} describing the cause for the failure
	 */
	public void onTaskFailed(Exception cause);

}
