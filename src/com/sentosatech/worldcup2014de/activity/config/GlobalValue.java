/*
 * Name: GlobalValue.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.activity.config;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.ImageInfo;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.object.Stadium;

public final class GlobalValue {
	
	public GlobalValue(Context context)
	{
		listImage();
		listImageFullInfo(context);
		getImageListStadium();
	}

	public static String NameCountry;
	public static String Name;
	public static String deviceTimeZone="";
	public static int IdGroup;
	public static List<Country> listCountries;
	public static List<ArrayList<Country>> listCountriesByGroup;
	public static List<Integer> listFlags;
	public static List<ImageInfo> listImageInfo;
	public static List<Integer> listImageStadium;
	public static MySharedPreferences prefs;
	public static List<Stadium> listStadium;
	public static List<String> allDayofWeek;
	public static List<String> allDay;
	public static List<String> allMonth;
	public static List<String> allStartTimeMatch;
	public static ArrayList<Match> listAllMath;
	public static final String FRUITY_NEWS_PREFERENCES = "FRUITY_NEWS_PREFERENCES";
	public static final String FRUITY_DROID_PREFERENCES = "FRUITY_DROID_PREFERENCES";
	public static final String EVANDRO_DROID_PREFERENCES = "EVANDRO_DROID_PREFERENCES";

	// -------------------Value
	// Config------------------------------------------------------//

	public static String URL_FILE_VERSION_HOST = "https://euroasia.company/worldcup/version.txt";
	public static String URL_FILE_DATABASE_HOST = "https://euroasia.company/worldcup/Worldcup2014.txt";
	public static String ADMOB_ID = "ca-app-pub-0184587035488950/1195528894";
	public static String alarmRing = "alarm.mp3";
	public static int vibrateAlarm = 7;
	public static int vibrateOnRimider = 0;
	public static String timeCountDown = "12.06.2014,17:00";
	public static boolean DEBUG_MODE = true;
	public static int TIME_CHECK_UPDATE_INTERVAL = 1;// 3 Mins
	
	
	//get data
	public void listImage() {
		GlobalValue.listFlags = new ArrayList<Integer>();
		GlobalValue.listFlags.add(R.drawable.icon_noname);
		GlobalValue.listFlags.add(R.drawable.icon_brazil);
		GlobalValue.listFlags.add(R.drawable.icon_croatia);
		GlobalValue.listFlags.add(R.drawable.icon_mexico);
		GlobalValue.listFlags.add(R.drawable.icon_cameroon);
		GlobalValue.listFlags.add(R.drawable.icon_spain);
		GlobalValue.listFlags.add(R.drawable.icon_netherland);
		GlobalValue.listFlags.add(R.drawable.icon_chile);
		GlobalValue.listFlags.add(R.drawable.icon_australia);
		GlobalValue.listFlags.add(R.drawable.icon_colombia);
		GlobalValue.listFlags.add(R.drawable.icon_ivory_coast);
		GlobalValue.listFlags.add(R.drawable.icon_japan);
		GlobalValue.listFlags.add(R.drawable.icon_greece);
		GlobalValue.listFlags.add(R.drawable.icon_uruguay);
		GlobalValue.listFlags.add(R.drawable.icon_costa_rica);
		GlobalValue.listFlags.add(R.drawable.icon_england);
		GlobalValue.listFlags.add(R.drawable.icon_italy);
		GlobalValue.listFlags.add(R.drawable.icon_switzerland);
		GlobalValue.listFlags.add(R.drawable.icon_ecuador);
		GlobalValue.listFlags.add(R.drawable.icon_honduras);
		GlobalValue.listFlags.add(R.drawable.icon_france);
		GlobalValue.listFlags.add(R.drawable.icon_argentina);
		GlobalValue.listFlags.add(R.drawable.icon_bosnia);
		GlobalValue.listFlags.add(R.drawable.icon_iran);
		GlobalValue.listFlags.add(R.drawable.icon_nigeria);
		GlobalValue.listFlags.add(R.drawable.icon_germany);
		GlobalValue.listFlags.add(R.drawable.icon_portugal);
		GlobalValue.listFlags.add(R.drawable.icon_ghana);
		GlobalValue.listFlags.add(R.drawable.icon_usa);
		GlobalValue.listFlags.add(R.drawable.icon_belgium);
		GlobalValue.listFlags.add(R.drawable.icon_algeria);
		GlobalValue.listFlags.add(R.drawable.icon_russia);
		GlobalValue.listFlags.add(R.drawable.icon_korea);
		GlobalValue.listFlags.add(R.drawable.icon_noname);
	}

	public void listImageFullInfo(Context context) {

		GlobalValue.listImageInfo = new ArrayList<ImageInfo>();
		GlobalValue.listImageInfo.add(new ImageInfo("BRAZIL",context.getString(R.string.BRAZIL), "BRA",
				R.drawable.icon_brazil));
		GlobalValue.listImageInfo.add(new ImageInfo("CROATIA",context.getString(R.string.CROATIA), "CRO",
				R.drawable.icon_croatia));
		GlobalValue.listImageInfo.add(new ImageInfo("MEXICO",context.getString(R.string.MEXICO), "MEX",
				R.drawable.icon_mexico));
		GlobalValue.listImageInfo.add(new ImageInfo("CAMEROON",context.getString(R.string.CAMEROON), "CMR",
				R.drawable.icon_cameroon));
		GlobalValue.listImageInfo.add(new ImageInfo("SPAIN",context.getString(R.string.SPAIN), "ESP",
				R.drawable.icon_spain));
		GlobalValue.listImageInfo.add(new ImageInfo("NETHERLANDS",context.getString(R.string.NETHERLANDS), "NED",
				R.drawable.icon_netherland));
		GlobalValue.listImageInfo.add(new ImageInfo("CHILE",context.getString(R.string.CHILE), "CHI",
				R.drawable.icon_chile));
		GlobalValue.listImageInfo.add(new ImageInfo("AUSTRALIA",context.getString(R.string.AUSTRALIA), "AUS",
				R.drawable.icon_australia));
		GlobalValue.listImageInfo.add(new ImageInfo("COLOMBIA",context.getString(R.string.COLOMBIA), "COL",
				R.drawable.icon_colombia));
		GlobalValue.listImageInfo.add(new ImageInfo("IVORY COAST",context.getString(R.string.IVORY_COAST), "CIV",
				R.drawable.icon_ivory_coast));
		GlobalValue.listImageInfo.add(new ImageInfo("JAPAN",context.getString(R.string.JAPAN), "JPN",
				R.drawable.icon_japan));
		GlobalValue.listImageInfo.add(new ImageInfo("GREECE",context.getString(R.string.GREECE), "GRE",
				R.drawable.icon_greece));
		GlobalValue.listImageInfo.add(new ImageInfo("URUGUAY",context.getString(R.string.URUGUAY), "URU",
				R.drawable.icon_uruguay));
		GlobalValue.listImageInfo.add(new ImageInfo("COSTA RICA",context.getString(R.string.COSTA_RICA), "CRC",
				R.drawable.icon_costa_rica));
		GlobalValue.listImageInfo.add(new ImageInfo("ENGLAND",context.getString(R.string.ENGLAND), "ENG",
				R.drawable.icon_england));
		GlobalValue.listImageInfo.add(new ImageInfo("ITALY",context.getString(R.string.ITALY), "ITA",
				R.drawable.icon_italy));
		GlobalValue.listImageInfo.add(new ImageInfo("SWITZERLAND",context.getString(R.string.SWITZERLAND), "CHE",
				R.drawable.icon_switzerland));
		GlobalValue.listImageInfo.add(new ImageInfo("ECUADOR",context.getString(R.string.ECUADOR), "ECU",
				R.drawable.icon_ecuador));
		GlobalValue.listImageInfo.add(new ImageInfo("HONDURAS",context.getString(R.string.HONDURAS), "HON",
				R.drawable.icon_honduras));
		GlobalValue.listImageInfo.add(new ImageInfo("FRANCE",context.getString(R.string.FRANCE), "FRA",
				R.drawable.icon_france));
		GlobalValue.listImageInfo.add(new ImageInfo("ARGENTINA",context.getString(R.string.ARGENTINA), "ARG",
				R.drawable.icon_argentina));
		GlobalValue.listImageInfo.add(new ImageInfo("BOSNIA-HERZEGOVINA",context.getString(R.string.BOSNIA_HERZEGOVINA),
				"BIH", R.drawable.icon_bosnia));
		GlobalValue.listImageInfo.add(new ImageInfo("IRAN",context.getString(R.string.IRAN), "IRN",
				R.drawable.icon_iran));
		GlobalValue.listImageInfo.add(new ImageInfo("NIGERIA",context.getString(R.string.NIGERIA), "NGA",
				R.drawable.icon_nigeria));
		GlobalValue.listImageInfo.add(new ImageInfo("GERMANY",context.getString(R.string.GERMANY), "GER",
				R.drawable.icon_germany));
		GlobalValue.listImageInfo.add(new ImageInfo("PORTUGAL",context.getString(R.string.PORTUGAL), "POR",
				R.drawable.icon_portugal));
		GlobalValue.listImageInfo.add(new ImageInfo("GHANA",context.getString(R.string.GHANA), "GHA",
				R.drawable.icon_ghana));
		GlobalValue.listImageInfo.add(new ImageInfo("USA",context.getString(R.string.USA), "USA",
				R.drawable.icon_usa));
		GlobalValue.listImageInfo.add(new ImageInfo("BELGIUM",context.getString(R.string.BELGIUM), "BEL",
				R.drawable.icon_belgium));
		GlobalValue.listImageInfo.add(new ImageInfo("ALGERIA",context.getString(R.string.ALGERIA), "ALG",
				R.drawable.icon_algeria));
		GlobalValue.listImageInfo.add(new ImageInfo("RUSSIA",context.getString(R.string.RUSSIA), "RUS",
				R.drawable.icon_russia));
		GlobalValue.listImageInfo.add(new ImageInfo("SOUTH KOREA",context.getString(R.string.SOUTH_KOREA), "KOR",
				R.drawable.icon_korea));
		GlobalValue.listImageInfo.add(new ImageInfo("UNKNOWN","UNKNOWN", "UKN",
				R.drawable.icon_noname));
	}

	public void getImageListStadium() {
		GlobalValue.listImageStadium = new ArrayList<Integer>();
		GlobalValue.listImageStadium.add(R.drawable.stadium1);
		GlobalValue.listImageStadium.add(R.drawable.stadium2);
		GlobalValue.listImageStadium.add(R.drawable.stadium3);
		GlobalValue.listImageStadium.add(R.drawable.stadium4);
		GlobalValue.listImageStadium.add(R.drawable.stadium5);
		GlobalValue.listImageStadium.add(R.drawable.stadium6);
		GlobalValue.listImageStadium.add(R.drawable.stadium7);
		GlobalValue.listImageStadium.add(R.drawable.stadium8);
		GlobalValue.listImageStadium.add(R.drawable.stadium9);
		GlobalValue.listImageStadium.add(R.drawable.stadium10);
		GlobalValue.listImageStadium.add(R.drawable.stadium11);
		GlobalValue.listImageStadium.add(R.drawable.stadium12);
	}
	
}
