package com.sentosatech.worldcup2014de.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 
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
		if (!i.isConnected()) {
			return false;
		}
		if (!i.isAvailable()) {
			return false;
		}
		return true;
	}
	
	public boolean checkNetworkStatus() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobile = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifi.isConnectedOrConnecting()) {
			return true;
		} else if (mobile.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}

	}

}
