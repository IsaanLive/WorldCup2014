package com.sentosatech.worldcup2014de;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.InfoMatchActivity;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.utility.StringUtility;

/**
 * {@link IntentService} responsible for handling GCM messages.
 */
public class GCMIntentService extends GCMBaseIntentService {
	private final String TAG = "GCMIntentService";

	private final int NOTIFICATION_ID = 1;

	public GCMIntentService() {
		super(GcmManager.SENDER_ID);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		Log.e(TAG, "onError: " + arg1);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		Log.e(TAG, "onMessage: " + data);
		sendNotification(context, data);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 1 seconds
		v.vibrate(1000);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.e(TAG, "onRegistered: " + registrationId);
		new GcmManager(context).setRegistrationId(context, registrationId);
		if (!StringUtility.isEmpty(registrationId)) {
//			ModelManager.postRegistratrionId(context, registrationId,false);
		}
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.e(TAG, "onUnregistered:  " + arg1);
	}

	// Put the GCM message into a notification and post it.
	private void sendNotification(Context context, Intent data) {
		String msg = data.getStringExtra("message");
		String description = data.getStringExtra("description");
		
		Boolean check = true;
		//Log.e(TAG,"url: " + url);
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent(context, InfoMatchActivity.class);
		intent.putExtra("description", description);
		intent.putExtra("CHECK_NOTI", check);
		intent.putExtra("MSG", msg);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(context.getString(R.string.app_name))
				.setStyle(new NotificationCompat.BigTextStyle().bigText(description))
				.setContentText(description).setAutoCancel(true);
		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
}
