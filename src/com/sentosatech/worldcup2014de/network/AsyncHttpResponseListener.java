/*
 * Name: $RCSfile: AsyncHttpResponseListener.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 4:24:06 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de.network;

/**
 * Predefine some http listener methods
 * 
 * @author Lemon
 */
public interface AsyncHttpResponseListener {
	/**
	 * Before get http response
	 */
	public void before();

	/**
	 * After get http response
	 * 
	 * @param statusCode
	 * @param response
	 */
	public void after(int statusCode, String response);

}
