/*
 * Name: DebugLog.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem, Inc. All rights reserved.
 */
package com.sentosatech.worldcup2014de.utility;

import android.util.Log;

import com.sentosatech.worldcup2014de.activity.config.GlobalValue;

/**
 * Smart Log supports to show log on console base on application mode
 * 
 * @author Lemon
 */
public final class DebugLog {
	/**
	 * @param TAG
	 * @param msg
	 */
	public static void e(String TAG, String msg) {
		if (GlobalValue.DEBUG_MODE) {
			Log.e(TAG, msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (GlobalValue.DEBUG_MODE) {
			Log.i(TAG, msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (GlobalValue.DEBUG_MODE) {
			Log.d(TAG, msg);
		}
	}

}
