package com.sbr.emicalculatorapp.service.loan.consumer;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Responsible for managing the asynchronous task life cycle.
 * 
 * <p>
 * Thanks to the blogger <a href=
 * "http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-android/"
 * > http://thiranjith.com/2012/10/15/introduction-to-working-with-ksoap2-in-
 * android/</a> who provides the tutorial to access the webservice from android.
 * @author sbharathraja
 * 
 */
public class AsyncTaskManager implements IProgressTracker {

	private ProgressDialog progressDialog;

	/**
	 * Creates the asynchronous task manager for given context.
	 * 
	 * @param context
	 */
	public AsyncTaskManager(Context context) {
		initProgressDialog(context);
	}

	/**
	 * Executes a task in the background thread, while displaying a busy dialog
	 * (non cancellable).
	 * 
	 * @param task
	 *            {@link AbstractProgressableAsyncTask}
	 * @param request
	 *            request for the background task
	 * @param progressLabel
	 *            label to be displayed when the progress dialog is being
	 *            displayed.
	 * @param onTaskCompletedListener
	 *            {@link OnAsyncTaskCompleteListener} to be notified once the
	 *            task is completed.
	 */
	public <T, P> void executeTask(AbstractProgressableAsyncTask<P, T> task,
			P request, CharSequence progressLabel,
			OnAsyncTaskCompleteListener<T> onTaskCompletedListener) {
		this.progressDialog.setMessage(progressLabel);

		task.setOnTaskCompletionListener(onTaskCompletedListener);
		task.setProgressTracker(this);
		task.execute(request);
	}

	/**
	 * Responsible for initialize the progress dialog.
	 * 
	 * @param context
	 */
	private void initProgressDialog(Context context) {
		this.progressDialog = new ProgressDialog(context);
		this.progressDialog.setCancelable(false);
		this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}

	@Override
	public void onStartProgress() {
		progressDialog.show();
	}

	@Override
	public void onStopProgress() {
		progressDialog.dismiss();
	}

}
