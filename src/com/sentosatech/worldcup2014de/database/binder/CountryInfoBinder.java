package com.sentosatech.worldcup2014de.database.binder;

import android.database.sqlite.SQLiteStatement;

import com.sentosatech.worldcup2014de.object.Country;

public class CountryInfoBinder implements ParameterBinder {

	@Override
	public void bind(SQLiteStatement st, Object object) {
		Country country = (Country) object;
		st.bindLong(1, country.getCountryId());
		st.bindString(2, country.getCountryName());
		st.bindString(3, country.getCountryKey());
//		st.bindString(4, country.getCountryImage());
		st.bindLong(5, country.getGroupId());
		st.bindLong(6, country.getPoint());
		st.bindLong(7, country.getAllGoalNumber());
		st.bindLong(8, country.getAllLoseNumber());
		st.bindString(9, country.getPoisition());
		st.bindString(10, country.getCountryInfo());
		st.bindLong(11, country.getMp());
		st.bindLong(12, country.getW());
		st.bindLong(13, country.getD());
		st.bindLong(14, country.getL());
	}
}
