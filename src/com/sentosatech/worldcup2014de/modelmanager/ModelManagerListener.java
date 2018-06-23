/*
 * Name: ModelManagerListener.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.modelmanager;

public interface ModelManagerListener {

	public void onWSError();

	public void OnSuccess(String json);
}
