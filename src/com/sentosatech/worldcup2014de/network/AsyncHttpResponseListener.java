/*
 * Name: AsyncHttpResponseListener.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */

package com.sentosatech.worldcup2014de.network;

/**
 * Predefine some http listener methods
 * 
 * @author wolfgang
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
