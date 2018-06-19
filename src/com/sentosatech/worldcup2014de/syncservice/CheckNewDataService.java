package com.sentosatech.worldcup2014de.syncservice;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.SplashScreenActivity;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.utility.FileUtility;
import com.sentosatech.worldcup2014de.utility.NetworkUtility;

/**
 * Sync Data Service : Check new version of database then notification for
 * 
 * @author Projectemplate
 * 
 * @created 20/03/2014
 * 
 */
public class CheckNewDataService extends Service {

	private static CheckNewDataService instance;

	private static final String TAG = "SyncService";
	private Context self;
	private static int SYNC_INTERVAL = GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 60 * 1000; // 1
																							// min
																							// for
																							// testing

	private Timer timer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public static boolean isRunning() {
		return instance != null;
	}

	@Override
	public void onCreate() {
		self = this;
		instance = this;
		Log.d(TAG, TAG + " onCreate");
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				checkNewVersion();
			}
		}, 60000, SYNC_INTERVAL);
	}

	private void checkNewVersion() {

		if (NetworkUtility.getInstance(self).isNetworkAvailable())
			if (FileUtility.checkNeedUpdate(self,
					GlobalValue.URL_FILE_VERSION_HOST)) {
				generateNotification(self);
			}
	}

	public static void generateNotification(Context context) {
		Log.d("UPDATE SERVICE", "Gen notification for new update");
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon,
				context.getString(R.string.update_message), when);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		Intent intent = new Intent(context, SplashScreenActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		notification.setLatestEventInfo(context,
				context.getString(R.string.update_message),
				context.getString(R.string.click_update), pendingIntent);
		nm.notify(1002, notification);
	}

	@Override
	public void onDestroy() {
		Log.d("UPDATE SERVICE", "Sync Service Stopped");
		instance = null;
		timer.cancel();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Log.d("UPDATE SERVICE", "Sync Service started");

	}

}
