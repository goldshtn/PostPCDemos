package il.ac.huji.adaptersdemo;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseDisplayAdapter extends ArrayAdapter<Course> {
	public CourseDisplayAdapter(
			MainActivity activity, List<Course> courses) {
		super(activity, android.R.layout.simple_list_item_1, courses);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Course course = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row, null);
		TextView txtName = (TextView)view.findViewById(R.id.txtName);
		TextView txtNekudot = (TextView)view.findViewById(
				R.id.txtNekudot);
		txtName.setText(course.name);
		txtNekudot.setText(Integer.toString(course.nekudot));
		return view;
	}
}
