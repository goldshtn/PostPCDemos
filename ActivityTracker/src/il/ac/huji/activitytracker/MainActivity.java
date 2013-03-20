package il.ac.huji.activitytracker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private ListView lstActivities;
	private Spinner spnActivity;
	private SQLiteDatabase db;
	private Cursor cursor;

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
		
//		Cursor cursor = db.query("activities", new String[] { "activity", "whenDate" },
//				null, null, null, null, null);
//		if (cursor.moveToFirst()) {
//			do {
//				String activity = cursor.getString(0);
//				long when = cursor.getLong(1);
//				Date whenDate = new Date(when);
//				System.out.println("activity = " + activity +
//						", when = " + whenDate.toGMTString());
//			} while (cursor.moveToNext());
//		}
		
//		cursor = db.query("activities",
//				new String[] { "_id", "activity", "whenDate" },
//				null, null, null, null, null);
//		String[] from = { "activity", "whenDate" };
//		int[] to = { R.id.txtActivity, R.id.txtWhen };
//		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//				R.layout.row, cursor, from, to);
//		lstActivities.setAdapter(adapter);
		
		Parse.initialize(this, "xYs88SFUnxfQfRdUV0H1vaYG5jmnLRSwcUENy9ik", "LwSKYtMbM392gnJ1Wvf1MdpJ94MKOF9mLT2XJZpu");
		PushService.subscribe(this, "", MainActivity.class);
		PushService.setDefaultPushCallback(this, MainActivity.class);
		
		ArrayList<String> activities = new ArrayList<String>();
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, activities);
		lstActivities.setAdapter(adapter);
		
		ParseQuery query = new ParseQuery("Activity");
		//query.whereEqualTo("activity", "Walking");
		query.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> objects, ParseException exc) {
				if (exc != null) {
					exc.printStackTrace();
				} else {
					for (ParseObject object : objects) {
						String activityType = object.getString("activity");
						adapter.add(activityType);
					}
				}
			}
		});
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
			cursor.requery();
			
			ParseObject parseObject = new ParseObject("Activity");
			parseObject.put("activity", (String)spnActivity.getSelectedItem());
			parseObject.put("when", new Date().getTime());
			parseObject.saveInBackground();
			
			break;
		}
		return true;
	}
}
