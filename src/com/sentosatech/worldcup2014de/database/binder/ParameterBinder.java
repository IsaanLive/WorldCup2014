/*
 * Name: $RCSfile: ParameterBinder.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Nov 16, 2011 11:40:22 AM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de.database.binder;

import android.database.sqlite.SQLiteStatement;

/**
 * Interface class support binding data from object model to query string (using
 * for insert to local database)
 * 
 * @author Lemon
 */
public interface ParameterBinder {
	void bind(SQLiteStatement st, Object object);
}
