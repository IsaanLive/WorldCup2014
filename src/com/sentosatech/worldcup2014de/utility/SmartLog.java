/*
 * Name: SmartLog.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */

package com.sentosatech.worldcup2014de.utility;

import android.util.Log;

import com.sentosatech.worldcup2014de.activity.config.ConfigSmartLog;

public final class SmartLog {
	/**
	 * Call SmartLog.log
	 * 
	 * @param TAG
	 * @param msg
	 */
	public static void log(String TAG, String msg) {
		if (ConfigSmartLog.DEBUG_MODE) {
			Log.d(TAG, msg);
		}
	}

	public static void logWS(String TAG, String msg) {
		if (ConfigSmartLog.DEBUG_WS) {
			Log.w(TAG, msg);
		}
	}

	public static void logDB(String TAG, String msg) {
		if (ConfigSmartLog.DEBUG_DB) {
			Log.e(TAG, msg);
		}
	}

}
