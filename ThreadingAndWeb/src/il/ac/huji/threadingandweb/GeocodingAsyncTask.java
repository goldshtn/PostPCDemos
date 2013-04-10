package il.ac.huji.threadingandweb;

import java.io.IOException;
import java.net.URL;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

public class GeocodingAsyncTask extends AsyncTask<String, Void, double[]> {

	private Context context;
	private TextView resultTextView;
	private ImageView imageMap;
	private Bitmap map;
	private ProgressDialog progressDialog;
	
	public GeocodingAsyncTask(Context context, TextView resultTextView, ImageView imageMap) {
		this.context = context;
		this.resultTextView = resultTextView;
		this.imageMap = imageMap;
	}
	
	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Geocoding");
		progressDialog.setMessage("Retrieving results from Google Maps API...");
		progressDialog.setCancelable(false);
		progressDialog.show();
		super.onPreExecute();
	}
	
	@Override
	protected void onPostExecute(double[] result) {
		progressDialog.dismiss();
		resultTextView.setText(String.format("Coordinates: %2.2f, %2.2f",
				result[0], result[1]));
		imageMap.setImageBitmap(map);
		imageMap.requestLayout();
		super.onPostExecute(result);
	}
	
	@Override
	protected double[] doInBackground(String... params) {
		Geocoding geocoding = new Geocoding(params[0]);
		try {
			geocoding.geocode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		map = loadBitmap(String.format(
				"http://maps.googleapis.com/maps/api/staticmap?center=%2.2f,%2.2f&zoom=15&size=600x300&sensor=false",
				geocoding.getLatitude(), geocoding.getLongitude()));
		return new double[] { geocoding.getLatitude(), geocoding.getLongitude() };
	}

	private static Bitmap loadBitmap(String url) {
		try {
			URL newurl = new URL(url);
			Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
