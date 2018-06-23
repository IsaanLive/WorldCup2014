/*
 * Name: ParameterFactory.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */

package com.sentosatech.worldcup2014de.modelmanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.sentosatech.worldcup2014de.PacketUtility;

/**
 * ParameterFactory class builds parameters for web service apis
 * 
 */
public final class ParameterFactory {
	
	
	public static List<NameValuePair> createRegistrationIdParams(Context context, String registrationId) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("device_id", registrationId));
		parameters.add(new BasicNameValuePair("device_type", PacketUtility.getImei(context)));
		return parameters;
	}

}
