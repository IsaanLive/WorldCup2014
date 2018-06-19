package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

import com.sentosatech.worldcup2014de.database.DBKeyConfig;
import com.sentosatech.worldcup2014de.object.Country;

public class CountryInfoMapper implements RowMapper<Country> {

	@Override
	public Country mapRow(Cursor row, int rowNum) {
		Country country = new Country();
		country.setCountryId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_ID));
		country.setCountryName(CursorParseUtility.getString(row,
				DBKeyConfig.KEY_COUNTRY_NAME));
		country.setCountryKey(CursorParseUtility.getString(row,
				DBKeyConfig.KEY_COUNTRY_KEY_WORD));
//		country.setCountryImage(CursorParseUtility.getString(row,
//				DBKeyConfig.KEY_COUNTRY_IMAGE));
		country.setGroupId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_GROUP_ID));
		country.setPoint(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_POINT));
		country.setAllGoalNumber(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_ALL_GOAL_NUMBER));
		country.setAllLoseNumber(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_ALL_LOSE_NUMBER));
		country.setPoisition(CursorParseUtility.getString(row, DBKeyConfig.KEY_COUNTRY_POISITION));
		country.setCountryInfo(CursorParseUtility.getString(row, DBKeyConfig.KEY_COUNTRY_INFO));
		country.setMp(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_MP));
		country.setW(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_W));
		country.setD(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_D));
		country.setL(CursorParseUtility.getInt(row, DBKeyConfig.KEY_COUNTRY_L));
		return country;
	}

}
