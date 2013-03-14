package il.ac.huji.adaptersdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class GetCourseNameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getcoursename_activity);
		
		findViewById(R.id.buttonOkay).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText edtCourseName = (EditText)findViewById(R.id.edtCourseName);
				String courseName = edtCourseName.getText().toString();
				if (courseName == null || "".equals(courseName)) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra("courseName", courseName);
					setResult(RESULT_OK, resultIntent);
					finish();
				}
			}
		});
	}
	
}
