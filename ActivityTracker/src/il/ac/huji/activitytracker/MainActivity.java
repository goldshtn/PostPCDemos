package il.ac.huji.activitytracker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private ListView lstActivities;
	private Spinner spnActivity;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		try {
//			FileOutputStream output = openFileOutput("myfile.txt", MODE_PRIVATE);
//			DataOutputStream dataOutput = new DataOutputStream(output);
//			dataOutput.writeUTF("Hello, World");
//			dataOutput.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		FileInputStream input;
//		try {
//			input = openFileInput("myfile.txt");
//			DataInputStream dataInput = new DataInputStream(input);
//			String hello = dataInput.readUTF();
//			dataInput.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		lstActivities = (ListView)findViewById(R.id.lstActivities);
		spnActivity = (Spinner)findViewById(R.id.spnActivity);
		
		String[] activityTypes = getResources().getStringArray(R.array.activity_types);
		ArrayAdapter<String> activityTypeAdapter =
				new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityTypes);
		activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActivity.setAdapter(activityTypeAdapter);
		
		ActivityTrackerDatabaseHelper helper = new ActivityTrackerDatabaseHelper(this);
		db = helper.getWritableDatabase();
		
		Cursor cursor = db.query("activities", new String[] { "activity", "whenDate" },
				null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				String activity = cursor.getString(0);
				long when = cursor.getLong(1);
				Date whenDate = new Date(when);
				System.out.println("activity = " + activity +
						", when = " + whenDate.toGMTString());
			} while (cursor.moveToNext());
		}
		
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
			ContentValues values = new ContentValues();
			values.put("activity", (String)spnActivity.getSelectedItem());
			values.put("whenDate", new Date().getTime());
			db.insert("activities", null, values);
			break;
		}
		return true;
	}
}
