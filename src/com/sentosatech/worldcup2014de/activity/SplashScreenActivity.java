/*
 * Name: SplashScreenActivity.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.sentosatech.worldcup2014de.PacketUtility;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.MySharedPreferences;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.database.DatabaseOpenhelper;
import com.sentosatech.worldcup2014de.database.DatabaseUtility;
import com.sentosatech.worldcup2014de.info.DatabaseConfig;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.network.NetworkUtility;
import com.sentosatech.worldcup2014de.utility.DebugLog;
import com.sentosatech.worldcup2014de.utility.DialogUtility;
import com.sentosatech.worldcup2014de.utility.FileUtility;

@SuppressLint("NewApi")
public class SplashScreenActivity extends BaseActivity {

	int appVersion = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		FileUtility.enableStrictMode();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (NetworkUtility.getInstance(self).isNetworkAvailable()) {
			checkVersion();
		} else
			new loadGlobalData().execute();
	}

	private void checkVersion() {

		String packagename = (new PacketUtility()).getPacketName();

		PackageManager manager = this.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(this.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (info != null) {
			DebugLog.d("Version", " Version code " + info.versionCode);
			appVersion = info.versionCode;
		}

		ModelManager.getData(self, true, WebServiceConfig.URL_REGISTER_VERSION
				+ appVersion + "&pk=" + packagename,
				new ModelManagerListener() {

					@Override
					public void onWSError() {
						new loadGlobalData().execute();
					}

					@Override
					public void OnSuccess(String json) {
						// TODO Auto-generated method stub
						int currentServerVersion = 0;
						try {
							currentServerVersion = new JSONObject(json)
									.getInt("version");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (appVersion < currentServerVersion) {
							DialogUtility.alert(SplashScreenActivity.this,
									getString(R.string.msg_update),
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											final String appPackageName = getPackageName(); // getPackageName()
																							// from
																							// Context
											DebugLog.d("CheckUpdate",
													"App Package :  "
															+ appPackageName); // Activity
											// object
											try {
												startActivity(new Intent(
														Intent.ACTION_VIEW,
														Uri.parse("market://details?id="
																+ appPackageName)));
											} catch (android.content.ActivityNotFoundException anfe) {
												startActivity(new Intent(
														Intent.ACTION_VIEW,
														Uri.parse("http://play.google.com/store/apps/details?id="
																+ appPackageName)));
											}

										}
									});
						} else
							new loadGlobalData().execute();
					}
				});

	}

	OnClickListener onEnableNetwork = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Intent settings = new Intent(
					android.provider.Settings.ACTION_WIFI_SETTINGS);
			startActivity(settings);

		}
	};
	OnClickListener onCancelclick = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			finish();

		}
	};

	private class loadGlobalData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			if (GlobalValue.prefs == null)
				GlobalValue.prefs = new MySharedPreferences(
						SplashScreenActivity.this);

			new DatabaseOpenhelper(SplashScreenActivity.this,
					DatabaseConfig.getInstance());
			new GlobalValue(self);
			if (GlobalValue.listStadium == null
					|| GlobalValue.listStadium.size() == 0) {
				GlobalValue.listStadium = DatabaseUtility
						.getAllStadium(SplashScreenActivity.this);

			}
			GlobalValue.deviceTimeZone = timeZone();
			Log.d("DeviceTime", "Time zone :" + GlobalValue.deviceTimeZone);

			Log.d("List stadium",
					"Size stadium :" + GlobalValue.listStadium.size());

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			goNext();
		}

	}

	public static String timeZone() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
				Locale.getDefault());
		String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());

		return "GMT" + timeZone.substring(0, 3) + ":"
				+ timeZone.substring(3, 5);
	}

	private void goNext() {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				finish();
				Intent intent = new Intent(SplashScreenActivity.this,
						MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.push_left_out);
			}

		}, 1000);

	}

}
