package com.sentosatech.worldcup2014de;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager; 
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;

import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.MySharedPreferences;

public class AlarmReceiver extends BroadcastReceiver {
	private MediaPlayer mp;
	private String TitleReminder;
	private boolean checkReminde = true;
	private String matchId = "", type = "";

	// AlertDialog alertDialog;
	@Override
	public void onReceive(Context context, Intent arg1) {
		Bundle b = arg1.getExtras();
		if (b != null) {
			TitleReminder = b.getString("titleReminder");
			matchId = b.getString("matchId");
			type = b.getString("type");
		}
		if (GlobalValue.prefs == null)
			GlobalValue.prefs = new MySharedPreferences(context);

		// If =0 : Reminder was cancel
		if (GlobalValue.prefs.getIntValue(type + matchId) > 0) {

			final Vibrator vibrator;
			vibrator = (Vibrator) context
					.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(1000 * GlobalValue.vibrateAlarm);
			mp = new MediaPlayer();
			if (!mp.isPlaying()) {
				try {
					AssetFileDescriptor descriptor = context.getAssets()
							.openFd(GlobalValue.alarmRing);
					mp.setDataSource(descriptor.getFileDescriptor(),
							descriptor.getStartOffset(), descriptor.getLength());
					descriptor.close();
					mp.setAudioStreamType(AudioManager.STREAM_ALARM);
					mp.setLooping(true);
					mp.prepare();
					mp.start();
					checkReminde = true;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			AlertDialog.Builder a = new AlertDialog.Builder(context)
					.setTitle("Reminder feature")
					.setMessage(TitleReminder)
					.setPositiveButton("Stop",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									if (checkReminde == true) {
										mp.stop();
										mp.release();
										mp = null;
										vibrator.cancel();
										System.runFinalizersOnExit(true);
										checkReminde = false;
									} else {
										mp.release();
										mp = null;
										vibrator.cancel();
										System.runFinalizersOnExit(true);
									}
								}
							});
			AlertDialog alert = a.create();
			alert.getWindow().setType(
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			alert.show();

			alert.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					if (checkReminde == true) {
						mp.stop();
						mp.release();
						mp = null;
						vibrator.cancel();
						System.runFinalizersOnExit(true);
						checkReminde = false;
					}
				}
			});
		}
	}

}
