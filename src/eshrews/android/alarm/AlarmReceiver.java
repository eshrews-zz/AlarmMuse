package eshrews.android.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
			Log.v("T", "Minute passed");
			Toast.makeText(context, "MINUTE PASSED", 10);
		}
	}

}
