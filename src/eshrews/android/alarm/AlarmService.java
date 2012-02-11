package eshrews.android.alarm;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class AlarmService extends Service {
	
	@Override
	public void onCreate() {
		this.registerReceiver(new AlarmReceiver(), new IntentFilter(Intent.ACTION_TIME_TICK));
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
