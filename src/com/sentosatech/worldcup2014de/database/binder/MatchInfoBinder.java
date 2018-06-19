package com.sentosatech.worldcup2014de.database.binder;

import android.database.sqlite.SQLiteStatement;

import com.sentosatech.worldcup2014de.object.Match;

public class MatchInfoBinder implements ParameterBinder{

	@Override
	public void bind(SQLiteStatement st, Object object) {
		Match matchInfo = (Match) object;
		st.bindLong(1, matchInfo.getMatchId());
		st.bindString(2, matchInfo.getMatchStartTime());
		st.bindString(3, matchInfo.getStadiumId());
		st.bindLong(4, matchInfo.getGroupId());
		st.bindString(5, matchInfo.getMatchDate());
		st.bindString(6, matchInfo.getMatchStatus());
		st.bindLong(7, matchInfo.getCountry1());
		st.bindLong(8, matchInfo.getCountry2());
		st.bindLong(9, matchInfo.getGoalCountry1());
		st.bindLong(10, matchInfo.getGoalCountry2());
		st.bindString(11, matchInfo.getMatchInfo());
		st.bindLong(12, matchInfo.getIdStadium());
	}

}
