package eshrews.android.alarm;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class AlarmService extends Service {
	
	static final int MSG_REGISTER_CLIENT = 1;
	static final int MSG_UNREGISTER_CLIENT = 2;
	static final int MSG_SET_VALUE = 3;
	
	
	private final Messenger mMessenger = new Messenger(new IncomingHandler());
	private Calendar mCalendar = null;
	
	@Override
	public void onCreate() {
		this.registerReceiver(new AlarmReceiver(), new IntentFilter(Intent.ACTION_TIME_TICK));
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mMessenger.getBinder();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleCommand(intent);
		return START_STICKY;
	}
	
	public void handleCommand(Intent intent) {
		Calendar currTime = Calendar.getInstance();
		currTime.setTimeInMillis(System.currentTimeMillis());
		int minute = currTime.get(Calendar.MINUTE);
		Log.v("DEBUG", "" + System.currentTimeMillis());
		
		if (intent.getAction() == null || mCalendar == null) {
			return;
		}
		
		int hour = currTime.get(Calendar.HOUR_OF_DAY);
		
		int compareMinute = mCalendar.get(Calendar.MINUTE);
		int compareHour = mCalendar.get(Calendar.HOUR_OF_DAY);
		
		Log.v("DEBUG", "" + compareHour + ":" + compareMinute + " vs " + hour + ":" + minute);
		
		if(minute == compareMinute && hour == compareHour) {
			Log.v("DEBUG", "Play Dat Funky Music");
		}
		
		
	}

	public class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SET_VALUE:
				Log.v("DEBUG", "Setting value");
				Bundle data = msg.getData();
				int hour = data.getInt("HOUR");
				int minute = data.getInt("MINUTE");
				
				Calendar calendar = Calendar.getInstance();
	    		calendar.set(Calendar.HOUR_OF_DAY, hour);
	    		calendar.set(Calendar.MINUTE, minute);
				mCalendar = calendar;
				break;

			default:
				super.handleMessage(msg);
				break;
			}
		}
	}
	
	public void setTime(Calendar calendar) {
		mCalendar = calendar;
	}
}
