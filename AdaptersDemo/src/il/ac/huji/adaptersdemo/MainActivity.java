package il.ac.huji.adaptersdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity {

    private ArrayAdapter<Course> adapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course( 
        		"Introduction to Computer Science", 6));
        courses.add(new Course(
        		"Object Oriented Programming", 4));
        courses.add(new Course(
        		"Human Centric Mobile Computing", 3));
        ListView listCourses = 
        		(ListView)findViewById(R.id.listCourses);
        adapter = //        	new ArrayAdapter<Course>(
//        			this,
//        			android.R.layout.simple_list_item_1,
//        			courses
//        			);
		new CourseDisplayAdapter(this, courses);
        listCourses.setAdapter(adapter);
        registerForContextMenu(listCourses);
        
        findViewById(R.id.buttonAdd).setOnClickListener(
        		new OnClickListener() {

					@Override
					public void onClick(View v) {
						adapter.add(new Course(
								"iPhone Application Development", 3));
						//adapter.getItem(12);
					}
        			
        		});
    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.contextmenu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)
				item.getMenuInfo();
		int selectedItemIndex = info.position;
		switch (item.getItemId()){
		case R.id.contextMenuItemDelete:
			adapter.remove(adapter.getItem(selectedItemIndex));
			break;
		}
		return true;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 1337 && resultCode == RESULT_OK) {
    		String courseName = data.getStringExtra("courseName");
    		adapter.add(new Course(courseName, 2));
    	}
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.itemAdd:
//    		adapter.add(new Course("First Aid", 2));
    		Intent intent = new Intent(this, GetCourseNameActivity.class);
    		startActivityForResult(intent, 1337);
    		break;
    	}
    	return true;
    }
}
