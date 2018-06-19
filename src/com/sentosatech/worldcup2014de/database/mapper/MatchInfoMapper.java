package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

import com.sentosatech.worldcup2014de.database.DBKeyConfig;
import com.sentosatech.worldcup2014de.object.Match;

public class MatchInfoMapper implements RowMapper<Match> {

	@Override
	public Match mapRow(Cursor row, int rowNum) {
		Match matchInfo = new Match();
		matchInfo.setMatchId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_ID));
		matchInfo.setMatchStartTime(CursorParseUtility.getString(row, DBKeyConfig.KEY_MATCH_START_TIME));
		matchInfo.setMediumId(CursorParseUtility.getString(row, DBKeyConfig.KEY_MATCH_MEDIUM_ID));
		matchInfo.setGroupId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_GROUP_ID));
		matchInfo.setMatchDate(CursorParseUtility.getString(row, DBKeyConfig.KEY_MATCH_DATE));
		matchInfo.setMatchStatus(CursorParseUtility.getString(row, DBKeyConfig.KEY_MATCH_STATUS));
		matchInfo.setCountry1(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_COUNTRY1));
		matchInfo.setCountry2(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_COUNTRY2));
		matchInfo.setGoalCountry1(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_GOAL_COUNTRY1));
		matchInfo.setGoalCountry2(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_GOAL_COUNTRY2));
		matchInfo.setMatchInfo(CursorParseUtility.getString(row, DBKeyConfig.KEY_MATCH_INFO));
		matchInfo.setIdStadium(CursorParseUtility.getInt(row, DBKeyConfig.KEY_MATCH_ID_STADIUM));
		return matchInfo;
	}

}
