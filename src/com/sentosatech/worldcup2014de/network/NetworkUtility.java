/*
 * Name: $RCSfile: NetworkUtility.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 3:57:18 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;

/**
 * NetworkUtility checks available network
 * 
 * @author Lemon
 */
public final class NetworkUtility {
	private Context context = null;

	private static NetworkUtility instance = null;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	private NetworkUtility(Context context) {
		this.context = context;
	}

	/**
	 * Get class instance
	 * 
	 * @param context
	 * @return
	 */
	public static NetworkUtility getInstance(Context context) {
		if (instance == null) {
			instance = new NetworkUtility(context);
		}
		return instance;
	}

	/**
	 * Check network connection
	 * 
	 * @return
	 */
	public boolean isNetworkAvailable() {
		ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null) {
			return false;
		}
		if (!i.isConnectedOrConnecting()) {
			return false;
		}
		if (!i.isConnectedOrConnecting()) {
			return false;
		}
		return true;
	}

	// turn on use network for location
	public void turnNetWorkLocationOn(Activity activity) {
		String provider = Settings.Secure.getString(
				activity.getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (!provider.contains("gps")) { // if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("1"));
			activity.sendBroadcast(poke);
		}
		// String provider = Settings.Secure.getString(
		// activity.getContentResolver(),
		// Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		// Log.e("provider", "ok " + provider);
		// final Intent poke = new Intent();
		// poke.setClassName("com.android.settings",
		// "com.android.settings.widget.SettingsAppWidgetProvider");
		// poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		// poke.setData(Uri.parse("3"));
		// activity.sendBroadcast(poke);
//		Settings.Secure.setLocationProviderEnabled(
//				activity.getContentResolver(),
//				LocationManager.NETWORK_PROVIDER, true);
	}

	// turn on GPS
//	public void turnGPSOn(Activity activity) {
//
//		String provider = Settings.Secure.getString(
//				activity.getContentResolver(),
//				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//		if (!provider.contains("gps")) { // if gps is disabled
//			final Intent poke = new Intent();
//			poke.setClassName("com.android.settings",
//					"com.android.settings.widget.SettingsAppWidgetProvider");
//			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//			poke.setData(Uri.parse("3"));
//			activity.sendBroadcast(poke);
//		}
//
//	}
}
