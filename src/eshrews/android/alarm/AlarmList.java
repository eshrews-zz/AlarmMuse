package eshrews.android.alarm;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;

public class AlarmList extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button save = (Button)findViewById(R.id.saveBtn);
        save.setOnClickListener(saveListener);
        startService(new Intent(this, AlarmService.class));
    }
    
    
    
    private OnClickListener saveListener = new OnClickListener() {
    	public void onClick(View v) {
    		Log.v("T", v.toString());
    	}
    };
    
    
}