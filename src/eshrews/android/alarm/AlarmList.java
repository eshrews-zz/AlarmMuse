package eshrews.android.alarm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.widget.Button;
import android.widget.TimePicker;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class AlarmList extends Activity {
	
	private Messenger mAlarmService = null;
	private boolean mIsBound = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button save = (Button)findViewById(R.id.saveBtn);
        save.setOnClickListener(saveListener);
        startService(new Intent(this, AlarmService.class));
        
        doBindService();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	doUnbindService();
    	Log.v("STATE", "Paused");
    	
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	doBindService();
    	Log.v("STATE", "Resumed");
    }
    
    private OnClickListener saveListener = new OnClickListener() {
    	public void onClick(View v) {
    		Log.v("DEBUG", v.toString());
    		
    		Log.v("DEBUG", v.getContext().toString());
    		
    		TimePicker time = (TimePicker)findViewById(R.id.timePicker);
    		int hour = time.getCurrentHour();
    		int minute = time.getCurrentMinute();
    		
    		Log.v("DEBUG", "" + hour);
    		    		
    		Message msg = Message.obtain(null, AlarmService.MSG_SET_VALUE);
    		Bundle data = new Bundle();
    		data.putInt("MINUTE", minute);
    		data.putInt("HOUR", hour);
    		msg.setData(data);
    		
			try {
				mAlarmService.send(msg);
			} catch (RemoteException e) {
				//NOTHING TO DO
			}
    	}
    };
    
    
    class IncomingHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case 1:
				//TODO
				break;
			default:
				super.handleMessage(msg);
				break;
			}
    	}
    }
    
    private ServiceConnection mConnection = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			mAlarmService = null;
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v("BIND", (service == null) ? "Is Null" : "Not Null");
			//Log.v("BIND", 
			mAlarmService = new Messenger(service);
			//try {
				//Can set a replyTo register here
			//}
			//catch {
			//}
			
		}
	};
    
    void doBindService() {
    	bindService(new Intent(this, AlarmService.class), mConnection, Context.BIND_AUTO_CREATE);
    	mIsBound = true;
    }
    
    void doUnbindService() {
    	if(mIsBound) {
    		if(mAlarmService != null) {
    			//try {
					//Unregister replyTo
    			//}
    			//catch {
    			//}
    		}
    		unbindService(mConnection);
    		mIsBound = false;
    	}
    }
}