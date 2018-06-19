/*
 * Name: $RCSfile: DatabaseUtility.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 2:55:54 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.sentosatech.worldcup2014de.database.binder.CountryInfoBinder;
import com.sentosatech.worldcup2014de.database.binder.MatchInfoBinder;
import com.sentosatech.worldcup2014de.database.mapper.CountryInfoMapper;
import com.sentosatech.worldcup2014de.database.mapper.GroupInfoMapper;
import com.sentosatech.worldcup2014de.database.mapper.MatchInfoMapper;
import com.sentosatech.worldcup2014de.database.mapper.StadiumMapper;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.Group;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.object.Stadium;

public final class DatabaseUtility {

	private static String STRING_SQL_INSERT_INTO_COUNTRY = "INSERT OR IGNORE INTO Country("
			+ DBKeyConfig.KEY_COUNTRY_NAME
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_KEY_WORD
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_IMAGE
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_GROUP_ID
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_POINT
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_ALL_GOAL_NUMBER
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_ALL_LOSE_NUMBER
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_POISITION
			+ ","
			+ DBKeyConfig.KEY_COUNTRY_INFO
			+ ")VALUES(null,null,null,null,?,?,?,?,?,?,?,?,?)";

	private static String STRING_SQL_INSERT_INTO_MATCH = "INSERT OR IGNORE INTO Match("
			+ DBKeyConfig.KEY_MATCH_START_TIME
			+ ","
			+ DBKeyConfig.KEY_MATCH_MEDIUM_ID
			+ ","
			+ DBKeyConfig.KEY_MATCH_GROUP_ID
			+ ","
			+ DBKeyConfig.KEY_MATCH_DATE
			+ ","
			+ DBKeyConfig.KEY_MATCH_STATUS
			+ ","
			+ DBKeyConfig.KEY_MATCH_COUNTRY1
			+ ","
			+ DBKeyConfig.KEY_MATCH_COUNTRY2
			+ ","
			+ DBKeyConfig.KEY_MATCH_GOAL_COUNTRY1
			+ ","
			+ DBKeyConfig.KEY_MATCH_GOAL_COUNTRY2
			+ ")VALUES(?,?,?,?,?,?,?,?,?,?)";

	// ************************ TABLE MATCH
	// ************************************************

	// add
	public static boolean insertMatch(Context context,
			ArrayList<Object> matchInfo) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.insertlist(STRING_SQL_INSERT_INTO_MATCH, matchInfo,
				new MatchInfoBinder());
	}

	// search

	public static ArrayList<Match> getAllMatchInfo(Context context) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*", "",
				new MatchInfoMapper());
	}

	public static ArrayList<Match> getlistMatchByIdGroup(Context context,
			int idGroup) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*",
				DBKeyConfig.KEY_MATCH_GROUP_ID + "='" + idGroup + "'",
				new MatchInfoMapper());

	}

	public static Stadium getStadium(Context context, int idStadium) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_STADIUM, "*",
				DBKeyConfig.KEY_STADIUM_ID + "='" + idStadium + "'",
				new StadiumMapper()).get(0);

	}

	public static ArrayList<Match> getlistMatchToday(Context context,
			String matchDate) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*",
				DBKeyConfig.KEY_MATCH_DATE + "='" + matchDate + "'",
				new MatchInfoMapper());

	}

	public static ArrayList<Match> getlistMatchByStatus(Context context,
			String idStatus) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*",
				DBKeyConfig.KEY_MATCH_STATUS + "='" + idStatus + "'",
				new MatchInfoMapper());

	}

	public static ArrayList<Match> getlistMatchByIdMatch(Context context,
			int idMatch) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*",
				DBKeyConfig.KEY_MATCH_ID + "='" + idMatch + "'",
				new MatchInfoMapper());

	}

	public static ArrayList<Match> getlistMatchByIdCountry(Context context,
			int idCountry) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_MATCH, "*",
				DBKeyConfig.KEY_MATCH_COUNTRY1 + "='" + idCountry + "'"
						+ " or " + DBKeyConfig.KEY_MATCH_COUNTRY2 + "='"
						+ idCountry + "'", new MatchInfoMapper());

	}

	// update

	// delete

	// ************************ TABLE COUNTRY ****************************

	// add

	public static boolean insertCountry(Context context,
			ArrayList<Object> countryInfo) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.insertlist(STRING_SQL_INSERT_INTO_COUNTRY,
				countryInfo, new CountryInfoBinder());
	}

	// search

	public static Country getCountryById(Context context, int idCountry) {
		PrepareStatement statement = new PrepareStatement(context);
		ArrayList<Country> list = statement.select(DBKeyConfig.TABLE_COUNTRY,
				"*", DBKeyConfig.KEY_COUNTRY_ID + "='" + idCountry + "'",
				new CountryInfoMapper());
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public static Country getCountryByPoisition(Context context,
			String poisition) {
		PrepareStatement statement = new PrepareStatement(context);
		ArrayList<Country> list = statement.select(DBKeyConfig.TABLE_COUNTRY,
				"*",
				DBKeyConfig.KEY_COUNTRY_POISITION + "='" + poisition + "'",
				new CountryInfoMapper());
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public static ArrayList<Country> getlistCountryByIdGroup(Context context,
			int idGroup) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_COUNTRY, "*",
				DBKeyConfig.KEY_GROUP_ID + "=" + idGroup,
				new CountryInfoMapper());
	}

	public static List<Country> getAllCountry(Context context) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_COUNTRY, "*", "",
				new CountryInfoMapper());

	}

	// update
	public static void updatePoint(Context context, int point, int countryId) {
		PrepareStatement statement = new PrepareStatement(context);
		statement.update(DBKeyConfig.TABLE_COUNTRY, "point=" + point,
				"countryId='" + countryId + "'");
	}

	public static void updateAllGoalNumber(Context context, int allGoalNumber,
			int countryId) {
		PrepareStatement statement = new PrepareStatement(context);
		statement.update(DBKeyConfig.TABLE_COUNTRY, "allGoalNumber="
				+ allGoalNumber, "countryId='" + countryId + "'");
	}

	public static void updateAllLoseNumber(Context context, int allLoseNumber,
			int countryId) {
		PrepareStatement statement = new PrepareStatement(context);
		statement.update(DBKeyConfig.TABLE_COUNTRY, "allLoseNumber="
				+ allLoseNumber, "countryId='" + countryId + "'");
	}

	public static void updatePoisition(Context context, String poisition,
			int countryId) {
		PrepareStatement statement = new PrepareStatement(context);
		statement.update(DBKeyConfig.TABLE_COUNTRY, "poisition='" + poisition
				+ "'", "countryId='" + countryId + "'");
	}

	// delete

	// ************************ TABLE GROUP ***************************

	// add

	// search
	public static ArrayList<Group> getAllGroupInfo(Context context) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_GROUP, "*", "",
				new GroupInfoMapper());
	}

	public static Group getGroupById(Context context, int idGroup) {
		PrepareStatement statement = new PrepareStatement(context);
		ArrayList<Group> list = statement
				.select(DBKeyConfig.TABLE_GROUP, "*", DBKeyConfig.KEY_GROUP_ID
						+ "=" + idGroup, new GroupInfoMapper());
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	// update

	// delete

	// ************************ TABLE STADIUM ******************************

	// add

	// search

	public static ArrayList<Stadium> getAllStadium(Context context) {
		PrepareStatement statement = new PrepareStatement(context);
		return statement.select(DBKeyConfig.TABLE_STADIUM, "*", "'1'='1'",
				new StadiumMapper());
	}

	// update

	// delete

	// ************************ TABLE MATCH COUNTRY ****************************

	// add

	// search
	// update

	// delete

}
