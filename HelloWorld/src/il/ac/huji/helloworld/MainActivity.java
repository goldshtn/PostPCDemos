package il.ac.huji.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Resources resources = getResources();
        String str = resources.getString(R.string.hello_world);
        float dim = resources.getDimension(R.dimen.activity_vertical_margin);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setText("Hello, World");
        button.setTextColor(Color.RED);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				button.setText("Thanks!");
			}
		});
    }
    
}
