package com.sentosatech.worldcup2014de.database;
public final class DBKeyConfig {

	// TABLE :
	// ****************************************************************
	
	public static String TABLE_COUNTRY = "Country";
	public static String TABLE_MATCH = "Match";
	public static String TABLE_GROUP = "GroupInfo";
	public static String TABLE_STADIUM = "Stadium";

	// ********** KEY FOR TABLE MATCH *******************************

	public static String KEY_MATCH_ID = "matchId";
	public static String KEY_MATCH_START_TIME = "matchStartTime";
	public static String KEY_MATCH_MEDIUM_ID = "stadium";
	public static String KEY_MATCH_GROUP_ID = "groupId";
	public static String KEY_MATCH_DATE = "matchDate";
	public static String KEY_MATCH_STATUS = "matchStatus";
	public static String KEY_MATCH_COUNTRY1 = "country1";
	public static String KEY_MATCH_COUNTRY2 = "country2";
	public static String KEY_MATCH_GOAL_COUNTRY1 = "goalCountry1";
	public static String KEY_MATCH_GOAL_COUNTRY2 = "goalCountry2";
	public static String KEY_MATCH_INFO = "infoMatch";
	public static String KEY_MATCH_ID_STADIUM = "idStadium";
	
	

	// ********** KEY FOR TABLE GROUP *******************************

	public static String KEY_GROUP_ID = "groupId";
	public static String KEY_GROUP_NAME = "groupName";

	// ********** KEY FOR TABLE COUNTRY *******************************

	public static String KEY_COUNTRY_ID = "countryId";
	public static String KEY_COUNTRY_KEY_WORD = "countryKey";
	public static String KEY_COUNTRY_NAME = "countryName";
	public static String KEY_COUNTRY_IMAGE = "countryImage";
	public static String KEY_COUNTRY_GROUP_ID = "groupId";
	public static String KEY_COUNTRY_POINT = "point";
	public static String KEY_COUNTRY_ALL_GOAL_NUMBER = "allGoalNumber";
	public static String KEY_COUNTRY_ALL_LOSE_NUMBER = "allLoseNumber";
	public static String KEY_COUNTRY_POISITION = "poisition";
	public static String KEY_COUNTRY_INFO = "countryInfo";
	public static String KEY_COUNTRY_MP = "mp";
	public static String KEY_COUNTRY_W = "w";
	public static String KEY_COUNTRY_D = "d";
	public static String KEY_COUNTRY_L = "l";

	// ********** KEY FOR TABLE STADIUM *******************************
	
	public static String KEY_STADIUM_ID = "idStadium";
	public static String KEY_STADIUM_CAPACITY = "capacity";
	public static String KEY_STADIUM_FULL_NAME = "fullName";

} 
