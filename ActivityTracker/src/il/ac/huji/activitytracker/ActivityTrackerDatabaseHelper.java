package il.ac.huji.activitytracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActivityTrackerDatabaseHelper extends SQLiteOpenHelper {

	public ActivityTrackerDatabaseHelper(Context context) {
		super(context, "activities_db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table activities ( _id integer primary key autoincrement,"
				+  " activity text, whenDate integer );");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Nothing to do.
	}

}
