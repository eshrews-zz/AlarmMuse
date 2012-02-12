package eshrews.android.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
			context.startService(new Intent(Intent.ACTION_TIME_TICK, Uri.EMPTY, context, AlarmService.class));
		}
	}

}
 