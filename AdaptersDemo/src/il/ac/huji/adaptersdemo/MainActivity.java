package il.ac.huji.adaptersdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.itemAdd:
    		adapter.add(new Course("First Aid", 2));
    		break;
    	}
    	return true;
    }
}
