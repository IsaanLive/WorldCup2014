package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

import com.sentosatech.worldcup2014de.database.DBKeyConfig;
import com.sentosatech.worldcup2014de.object.Stadium;

public class StadiumMapper implements RowMapper<Stadium>{

	@Override
	public Stadium mapRow(Cursor row, int rowNum) {
		Stadium stadium = new Stadium();
		stadium.setIdStadium(CursorParseUtility.getInt(row, DBKeyConfig.KEY_STADIUM_ID));
		stadium.setCapacity(CursorParseUtility.getString(row, DBKeyConfig.KEY_STADIUM_CAPACITY));
		stadium.setFullNameStadium(CursorParseUtility.getString(row, DBKeyConfig.KEY_STADIUM_FULL_NAME));
		return stadium;
	}

}
