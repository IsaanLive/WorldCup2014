/*
 * Name: PrepareStatement.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.sentosatech.worldcup2014de.database.binder.ParameterBinder;
import com.sentosatech.worldcup2014de.database.mapper.RowMapper;
import com.sentosatech.worldcup2014de.info.DatabaseConfig;
import com.sentosatech.worldcup2014de.utility.SmartLog;
import com.sentosatech.worldcup2014de.utility.StringUtility;

public class PrepareStatement {
	private final static String TAG = "PrepareStatement";

	private final static String MESSAGE_ERROR_WHEN_IMPLEMENT_QUERY = "ERROR WHEN IMPLEMENT QUERY";

	private final static String MESSAGE_INSERT_AN_OBJECT_SUCCESSFULLY = "INSERTED AN OBJECT SUCCESSFULLY";
	private final static String MESSAGE_UPDATE_AN_OBJECT_SUCCESSFULLY = "UPDATED AN OBJECT SUCCESSFULLY";

	private final static String MESSAGE_INSERT_A_LIST_OBJECT_SUCCESSFULLY = "INSERTED A LIST OBJECT SUCCESSFULLY";

	private final static String MESSAGE_NO_OBJECT_INSERT = "NO OBJECT INSERT";
	private final static String MESSAGE_NO_OBJECT_UPDATED = "NO OBJECT UPDATED";

	private final static String MESSAGE_LINE = "============================";

	private static Context context = null;

	private SQLiteDatabase db = null;

	/**
	 * Constructor
	 */
	public PrepareStatement(Context context) {
		this.context = context;
	}

	// ==================== SQLITE CORE FUNCTION ====================

	/**
	 * Implement a query string using for delete, update command
	 * 
	 * @param sql
	 * @return
	 */
	public boolean query(String sql, Object[] params) {
		boolean success = false;
		db = openDatabaseConnection();
		if (db != null) {
			SQLiteStatement statement = null;
			try {
				statement = db.compileStatement(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						statement.bindString(i + 1, params[i].toString());
					}
				}
				statement.execute();
				success = true;
			} catch (Exception ex) {
				SmartLog.log(TAG, "Error when implement query: " + sql);
				ex.printStackTrace();
			} finally {
				statement.close();
				closeDatabaseConnection();
			}
		}

		return success;
	}

	/**
	 * Implement a select query string
	 * 
	 * @param sql
	 * @return
	 */
	public <T> ArrayList<T> select(String table, String columns, String where, RowMapper<T> rowMapper) {
		ArrayList<T> modelList = new ArrayList<T>();

		db = openDatabaseConnection();
		if (db != null) {
			Cursor cursor = execSelect(db, table, columns, where);
			cursor.moveToFirst();
			int rowNum = 0;
			while (cursor.isAfterLast() == false) {
				modelList.add(rowMapper.mapRow(cursor, rowNum));
				cursor.moveToNext();
				rowNum++;
			}
			cursor.close();
			closeDatabaseConnection();
		}

		return modelList;
	}

	/**
	 * Implement a select query string with limit row
	 * 
	 * @param sql
	 * @return
	 */

	public <T> ArrayList<T> select(String table, String columns, String where, int limit, RowMapper<T> rowMapper) {
		ArrayList<T> modelList = new ArrayList<T>();

		db = openDatabaseConnection();
		if (db != null) {
			Cursor cursor = execSelect(db, table, columns, where, limit);
			cursor.moveToFirst();
			int rowNum = 0;
			while (cursor.isAfterLast() == false) {
				modelList.add(rowMapper.mapRow(cursor, rowNum));
				cursor.moveToNext();
				rowNum++;
			}
			cursor.close();
			closeDatabaseConnection();
		}

		return modelList;
	}

	public <T> ArrayList<T> runRawQuery(String sql, RowMapper<T> rowMapper) {
		ArrayList<T> modelList = new ArrayList<T>();

		db = openDatabaseConnection();
		if (db != null) {
			Cursor cursor = execRawQuery(db, sql);
			cursor.moveToFirst();
			int rowNum = 0;
			while (cursor.isAfterLast() == false) {
				modelList.add(rowMapper.mapRow(cursor, rowNum));
				cursor.moveToNext();
				rowNum++;
			}
			cursor.close();
			closeDatabaseConnection();
		}

		return modelList;
	}

	/**
	 * Implement a insert a list object to local database
	 * 
	 * @param sql
	 * @return
	 */
	public boolean insertlist(String sql, ArrayList<Object> objectList, ParameterBinder parameterBinder) {
		if (objectList == null || objectList.size() == 0) {
			SmartLog.log(TAG, MESSAGE_NO_OBJECT_INSERT + MESSAGE_LINE);
			return false;
		}

		boolean success = false;
		db = openDatabaseConnection();
		if (db != null) {
			db.beginTransaction();
			SQLiteStatement statement = null;
			try {
				for (int i = 0; i < objectList.size(); i++) {
					statement = db.compileStatement(sql);
					Object object = objectList.get(i);
					parameterBinder.bind(statement, object);
					statement.execute();
				}

				success = true;
				db.setTransactionSuccessful();
				SmartLog.log(TAG, MESSAGE_INSERT_A_LIST_OBJECT_SUCCESSFULLY + MESSAGE_LINE);
			} catch (Exception ex) {
				SmartLog.log(TAG, MESSAGE_ERROR_WHEN_IMPLEMENT_QUERY + " : " + sql);
				ex.printStackTrace();
			} finally {
				db.endTransaction();
				statement.close();
				closeDatabaseConnection();
			}
		}

		return success;
	}

	/**
	 * Insert an object to local database
	 * 
	 * @param sql
	 * @return
	 */
	public boolean insert(String sql, Object object, ParameterBinder parameterBinder) {
		if (object == null) {
			SmartLog.log(TAG, MESSAGE_NO_OBJECT_INSERT + MESSAGE_LINE);
			return false;
		}

		boolean success = false;
		db = openDatabaseConnection();
		if (db != null) {
			SQLiteStatement statement = null;
			try {
				statement = db.compileStatement(sql);
				parameterBinder.bind(statement, object);
				statement.execute();
				success = true;
				SmartLog.log(TAG, MESSAGE_INSERT_AN_OBJECT_SUCCESSFULLY + MESSAGE_LINE);
			} catch (Exception ex) {
				SmartLog.log(TAG, MESSAGE_ERROR_WHEN_IMPLEMENT_QUERY + " : " + sql);
				ex.printStackTrace();
			} finally {
				statement.close();
				closeDatabaseConnection();
			}
		}

		return success;
	}

	/**
	 * Update a row in table
	 * 
	 * @param sql
	 * @return
	 */
	public boolean update(String tableName, String set, String where) {
		if (tableName == null || tableName.equalsIgnoreCase("") || where == null || where.equalsIgnoreCase("")) {
			SmartLog.log(TAG, MESSAGE_NO_OBJECT_UPDATED + MESSAGE_LINE);
			return false;
		}
		StringBuffer sql = null;
		boolean success = false;
		db = openDatabaseConnection();
		if (db != null) {
			SQLiteStatement statement = null;
			try {

				sql = new StringBuffer();
				sql.append("Update " + tableName);
				sql.append(" Set " + set);
				sql.append(" Where " + where);
				SmartLog.logDB(TAG, "Exec SQL : " + sql.toString());
				statement = db.compileStatement(sql.toString());
				statement.execute();
				success = true;
				SmartLog.log(TAG, MESSAGE_UPDATE_AN_OBJECT_SUCCESSFULLY + MESSAGE_LINE);
			} catch (Exception ex) {
				SmartLog.log(TAG, MESSAGE_ERROR_WHEN_IMPLEMENT_QUERY + sql.toString());
				ex.printStackTrace();
			} finally {
				statement.close();
				closeDatabaseConnection();
			}
		}

		return success;
	}

	// =================================================================

	/**
	 * Run SELECT command
	 * 
	 * @param table
	 * @param columns
	 * @param where
	 * @return
	 */
	protected Cursor execSelect(SQLiteDatabase db, String table, String columns, String where) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		if (columns == null || columns.equals("") || columns.trim().equalsIgnoreCase("*")) {
			sql.append("*");
		} else {
			String[] st = columns.split(" ");
			String[] fields = new String[st.length];
			int n = 0;
			for (String c : st) {
				if ("".equals(c) == false) {
					fields[n] = c;
					n++;
				}
			}
			sql.append(StringUtility.join(fields, ", "));
		}
		sql.append(" FROM ");
		sql.append(table);
		if (where != null && !where.equalsIgnoreCase("")) {
			sql.append(" WHERE ");
			sql.append(where);
		}
		String s = sql.toString();
		SmartLog.logDB(TAG, "Exect SQL :" + s);
		return db.rawQuery(s, null);
	}

	/**
	 * Run SELECT command with limit
	 * 
	 * @param table
	 * @param columns
	 * @param where
	 * @return
	 */
	protected Cursor execSelect(SQLiteDatabase db, String table, String columns, String where, int limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		if (columns == null || columns.equals("") || columns.trim().equalsIgnoreCase("*")) {
			sql.append("*");
		} else {
			String[] st = columns.split(" ");
			String[] fields = new String[st.length];
			int n = 0;
			for (String c : st) {
				if ("".equals(c) == false) {
					fields[n] = c;
					n++;
				}
			}
			sql.append(StringUtility.join(fields, ", "));
		}
		sql.append(" FROM ");
		sql.append(table);
		if (where != null && !where.equalsIgnoreCase("")) {
			sql.append(" WHERE ");
			sql.append(where);
		}
		if (!(limit == 0)) {
			sql.append(" Limit ");
			sql.append(limit);
		}
		String s = sql.toString();
		SmartLog.logDB(TAG, "Exect SQL :" + s);
		return db.rawQuery(s, null);
	}

	/**
	 * Run Raw query sql
	 * 
	 * @return
	 */
	public Cursor execRawQuery(SQLiteDatabase db, String sql) {
		SmartLog.logDB("Database ", "Exec SQL :" + sql);
		return db.rawQuery(sql, null);
	}

	// ==================== SQLITE UTILITY FUNCTION ====================

	/**
	 * Open database connection
	 */
	private SQLiteDatabase openDatabaseConnection() {
		// Open database connection
		return (new DatabaseOpenhelper(context, DatabaseConfig.getInstance())).getWritableDatabase();
	}

	/**
	 * Close database connection
	 */
	private void closeDatabaseConnection() {
		if (db != null) {
			db.close();
		}
	}
}
