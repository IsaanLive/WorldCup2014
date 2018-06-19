package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

import com.sentosatech.worldcup2014de.utility.SmartLog;

public class CursorParseUtility {

	private static String TAG = "CursorParseUtility";

	public static String getString(Cursor row, String columName) {

		if (row == null || columName == null || columName.equalsIgnoreCase("")
				|| row.getColumnIndex(columName) == -1) {
			SmartLog.logDB(TAG, "No ColumnName found :" + columName);
			return "";
		} else {

			return row.getString(row.getColumnIndex(columName)) != null ? row
					.getString(row.getColumnIndex(columName)) : "";
		}

	}

	public static boolean getBoolean(Cursor row, String columName) {

		if (row == null || columName == null || columName.equalsIgnoreCase("")
				|| row.getColumnIndex(columName) == -1) {
			SmartLog.logDB(TAG, "No ColumnName found :" + columName);
			return false;
		} else {
			return row.getString(row.getColumnIndex(columName)) != null ? (row
					.getString(row.getColumnIndex(columName))
					.equalsIgnoreCase("1")) : false;
		}

	}

	public static double getDouble(Cursor row, String columName) {

		if (row == null || columName == null || columName.equalsIgnoreCase("")
				|| row.getColumnIndex(columName) == -1) {
			SmartLog.logDB(TAG, "No ColumnName found :" + columName);
			return 0.0;
		} else {

			return row.getColumnIndex(columName) != -1 ? row.getDouble(row
					.getColumnIndex(columName)) : 0.0;
		}

	}

	public static int getInt(Cursor row, String columName) {
		if (row == null || columName == null || columName.equalsIgnoreCase("")
				|| row.getColumnIndex(columName) == -1) {
			SmartLog.logDB(TAG, "No ColumnName found :" + columName);
			return 0;
		} else {

			return row.getColumnIndex(columName) != -1 ? row.getInt(row
					.getColumnIndex(columName)) : 0;
		}

	}
}
