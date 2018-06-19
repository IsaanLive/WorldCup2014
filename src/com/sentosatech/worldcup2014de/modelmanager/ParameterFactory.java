/*
 * Name: $RCSfile: ParameterFactory.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 2:45:36 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
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