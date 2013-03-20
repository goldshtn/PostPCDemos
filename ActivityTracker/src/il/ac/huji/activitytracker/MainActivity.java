package il.ac.huji.activitytracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private ListView lstActivities;
	private Spinner spnActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lstActivities = (ListView)findViewById(R.id.lstActivities);
		spnActivity = (Spinner)findViewById(R.id.spnActivity);
		
		String[] activityTypes = getResources().getStringArray(R.array.activity_types);
		ArrayAdapter<String> activityTypeAdapter =
				new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityTypes);
		activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActivity.setAdapter(activityTypeAdapter);
		
		//TODO: initialize database
		
		//TODO: bind cursor to list of activities
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			//TODO: add activity to database
			break;
		}
		return true;
	}
}
