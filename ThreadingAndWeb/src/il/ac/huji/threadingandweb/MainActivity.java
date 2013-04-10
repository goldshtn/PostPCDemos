package il.ac.huji.threadingandweb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnCalculate).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText edtMaxPrime = (EditText)findViewById(R.id.edtMaxPrime);
				TextView txtResult = (TextView)findViewById(R.id.txtResult);
				
				int maxPrime = Integer.parseInt(edtMaxPrime.getText().toString());
//				PrimeCounter counter = new PrimeCounter(2, maxPrime);
//				counter.count();
//				txtResult.setText(String.format("There are %d primes in the range.", counter.getResult()));
				new PrimeCounterAsyncTask(MainActivity.this, txtResult).execute(2, maxPrime);
			}
		});
		
		findViewById(R.id.btnGeocode).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText edtAddress = (EditText)findViewById(R.id.edtAddress);
				TextView txtGeocodeResult = (TextView)findViewById(R.id.txtGeocodeResult);
				ImageView imageView = (ImageView) findViewById(R.id.imgMap);
				
				new GeocodingAsyncTask(MainActivity.this, txtGeocodeResult, imageView).execute(
						edtAddress.getText().toString());
			}
		});
	}
	
}
