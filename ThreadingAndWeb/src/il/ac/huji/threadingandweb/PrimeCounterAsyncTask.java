package il.ac.huji.threadingandweb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class PrimeCounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {
//													input	  progress	output
	private Context context;
	private ProgressDialog progressDialog;
	private TextView outputTextView;
	
	public PrimeCounterAsyncTask(Context context, TextView outputTextView) {
		this.context = context;
		this.outputTextView = outputTextView;
		progressDialog = new ProgressDialog(this.context);
		progressDialog.setTitle("Counting Primes");
		progressDialog.setMessage("Please wait while I count your primes...");
		progressDialog.setCancelable(false);
	}
	
	@Override
	protected void onPreExecute() {
		progressDialog.show();
		super.onPreExecute();
	}
	
	@Override
	protected Integer doInBackground(Integer... params) {
		int from = params[0];
		int to = params[1];
		int count = 0;
		for (int i = from; i <= to; ++i) {
			if (PrimeCounter.isPrime(i)) {
				++count;
			}
			if (i % 1000 == 0) {
				publishProgress(i);
			}
		}
		return count;
	}
	
	//This function receives the progress information published using publishProgress
	@Override
	protected void onProgressUpdate(Integer... values) {
		progressDialog.setMessage("Crunched through " + values[0]);
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		progressDialog.dismiss();
		outputTextView.setText(String.format("There are %d primes in the range.", result));
		super.onPostExecute(result);
	}
	
}
