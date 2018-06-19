package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

/**
 * Interface class support mapping from data row to object model
 * 
 * @author Lemon
 */
public interface RowMapper<E> {
	E mapRow(Cursor row, int rowNum);
}