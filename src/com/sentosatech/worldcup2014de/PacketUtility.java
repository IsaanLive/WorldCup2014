/*
 * Name: $RCSfile: PacketUtility.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PacketUtility {
	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public PacketUtility() {
	}

	/**
	 * Get package name
	 * 
	 * @return
	 */
	public String getPacketName() {
		return this.getClass().getPackage().getName();
	}
	
	/**
	 * Get imei of device
	 * 
	 * @param context
	 * @return imei
	 */
	public static String getImei(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	
	/**
	 * Get imsi of device
	 * 
	 * @param context
	 * @return imsi
	 */
	
	public static String getIMSI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSubscriberId();
	}
}
