/*
 * Name: ParserUitility.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.modelmanager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.EventInfo;
import com.sentosatech.worldcup2014de.object.ImageInfo;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.object.Stadium;
import com.sentosatech.worldcup2014de.object.TopScoreInfo;

public class ParserUitility {

	public static ArrayList<Match> parserListMatch(JSONObject jsonObject) {
		ArrayList<Match> listPromotion = new ArrayList<Match>();
		try {
			JSONArray array = jsonObject.getJSONArray("data");
			JSONObject obj;
			Match item;
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					obj = array.getJSONObject(i);
					item = new Match();
					item.setMatchId(getIntValue(obj, "match_id"));
					item.setFullNameTeam1(getCountryNameByEngName(getStringValue(obj,
							"match_host_full_name")));
					item.setFullNameTeam2(getCountryNameByEngName(getStringValue(obj,
							"match_guest_full_name")));
					item.setCodeTeam1(getStringValue(obj, "match_host_name"));
					item.setCodeTeam2(getStringValue(obj, "match_guest_name"));
					item.setMatchStartTime(getStringValue(obj, "match_date"));
					item.setMatchDate(getStringValue(obj, "match_date"));
					item.setScore1(getStringValue(obj, "match_host_score"));
					item.setScore2(getStringValue(obj, "match_guest_score"));
					item.setMediumId(getStringValue(obj, "location"));
					item.setImageId1(getImageIdByName(item.getCodeTeam1()));
					item.setImageId2(getImageIdByName(item.getCodeTeam2()));

					item.setStadiumName(getStadiumName(item.getStadiumId()));

					listPromotion.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("JSOnUtil", "List match : " + listPromotion.size());
		return listPromotion;
	}

	public static ArrayList<Match> parserListMatch(String json, String key) {
		ArrayList<Match> listPromotion = new ArrayList<Match>();
		try {
			JSONArray array = new JSONObject(json).getJSONObject("data")
					.getJSONArray(key);
			JSONObject obj;
			Match item;
			ImageInfo country1, country2;
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					obj = array.getJSONObject(i);
					item = new Match();
					country1 = getCountry(getCountryNameByEngName(getStringValue(obj, "host_name_full")));
					country2 = getCountry(getCountryNameByEngName(getStringValue(obj,
							"guests_name_full")));
					item.setMatchId(getIntValue(obj, "id"));
					item.setFullNameTeam1(country1.getCountryCode());
					item.setWinTeam(getCountryNameByEngName(getCountry(
							getStringValue(obj, "win_name_full"))
							.getCountryCode()));
					item.setImageWin(getImageIdByName(getStringValue(obj,
							"win_name_full")));
					item.setFullNameTeam2(country2.getCountryCode());
					item.setImageId1(country1.getImageId());
					item.setImageId2(country2.getImageId());

					listPromotion.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("JSOnUtil", "List match with key : " + key + " :  "
				+ listPromotion.size());
		return listPromotion;
	}

	public static ArrayList<TopScoreInfo> parserListTopScore(String json) {

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(json);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<TopScoreInfo> listTopInfo = new ArrayList<TopScoreInfo>();
		try {
			if (jsonObject != null) {
				JSONArray array = jsonObject.getJSONArray("data");
				JSONObject obj;
				TopScoreInfo item;
				if (array != null && array.length() > 0) {
					for (int i = 0; i < array.length(); i++) {
						obj = array.getJSONObject(i);
						item = new TopScoreInfo();
						item.setCountryCode(getStringValue(obj,
								"player_team_name"));
						item.setPlayerNamwe(getStringValue(obj, "player_name"));
						item.setCountryName(getStringValue(obj,
								"player_team_name"));
						item.setScore(getIntValue(obj, "player_score"));
						item.setImageId(getImageIdByName(item.getCountryName()));
						listTopInfo.add(item);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("JSOnUtil", "List top info : " + listTopInfo.size());
		return listTopInfo;
	}

	public static ArrayList<Country> parserListCountryPoint(
			JSONObject jsonObject) {
		ArrayList<Country> listCountry = new ArrayList<Country>();
		try {
			JSONArray array = jsonObject.getJSONArray("data");
			JSONObject obj;
			Country item;
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					obj = array.getJSONObject(i);
					item = new Country();
					item.setCountryId(getIntValue(obj, "id"));
					item.setCountryKey(getStringValue(obj, "team_name"));
					item.setCountryName(getCountryNameByEngName(getStringValue(obj, "team_full_name")));
					item.setCountryImage(getImageIdByName(item.getCountryKey()));
					item.setPoint(getIntValue(obj, "points"));
					item.setW(getIntValue(obj, "won"));
					item.setD(getIntValue(obj, "drawn"));
					item.setL(getIntValue(obj, "lost"));
					item.setMp(getIntValue(obj, "played"));
					item.setAllGoalNumber(getIntValue(obj, "scored"));
					item.setAllLoseNumber(getIntValue(obj, "conceeded"));
					item.setPoisition(getStringValue(obj, "team_position"));
					listCountry.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("JSOnUtil", "List country : " + listCountry.size());
		return listCountry;
	}

	public static ArrayList<EventInfo> parserListEvent(JSONObject jsonObject) {
		ArrayList<EventInfo> listCountry = new ArrayList<EventInfo>();
		try {
			JSONArray array = jsonObject.getJSONObject("data").getJSONArray(
					"events");
			JSONObject obj;
			EventInfo item;
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					obj = array.getJSONObject(i);
					item = new EventInfo();
					item.setEventPlayer(getStringValue(obj, "event_player"));
					item.setEventScore(getStringValue(obj, "event_score"));
					item.setEventTime(getStringValue(obj, "event_minute"));
					item.setTeamName(getStringValue(obj, "team_name"));
					item.setScoreType(getStringValue(obj, "score_type"));
					item.setEventType(getStringValue(obj, "event_type"));
					item.setEventTeamId(getStringValue(obj, "event_team_id"));
					if (item.getEventType().equalsIgnoreCase("Penalty"))
						item.setEventPlayer(item.getEventPlayer() + " (pen)");
					if (item.getScoreType().equalsIgnoreCase("o.g"))
						item.setEventPlayer(item.getEventPlayer() + " (o.g)");
					listCountry.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("JSOnUtil", "List country : " + listCountry.size());
		return listCountry;
	}

	private static String getStadiumName(String id) {
		if (GlobalValue.listStadium != null)
			for (Stadium item : GlobalValue.listStadium) {
				if ((item.getIdStadium() + "").equalsIgnoreCase(id)) {
					return item.getFullNameStadium();
				}
			}
		return "";
	}

	private static String getCountryNameByEngName(String countryName) {

		String result = "UNKNOWN";

		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryName().equalsIgnoreCase(countryName)) {
				result = item.getCountryName2();
				break;
			}
		}

		return result;
	}

	private static int getImageId(String countryCode) {
		int result = R.drawable.icon_noname;

		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryCode().equalsIgnoreCase(countryCode)) {
				result = item.getImageId();
				break;
			}
		}

		return result;
	}

	private static ImageInfo getCountry(String countryName) {
		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryName().equalsIgnoreCase(countryName)) {
				return item;
			}
		}

		return GlobalValue.listImageInfo
				.get(GlobalValue.listImageInfo.size() - 1);
	}

	private static int getImageIdByName(String countryName) {

		int result = R.drawable.icon_noname;

		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryName().equalsIgnoreCase(countryName)) {
				result = item.getImageId();
				break;
			}
		}

		return result;
	}

	private static int getImageIdByCode(String countryCode) {

		int result = R.drawable.icon_noname;

		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryCode().equalsIgnoreCase(countryCode)) {
				result = item.getImageId();
				break;
			}
		}

		return result;
	}

	// ========================= CORE FUNCTIONS ===========================

	/**
	 * Extract user information
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static String getStringValue(JSONObject obj, String key) {
		try {
			return obj.isNull(key) ? "" : obj.getString(key);
		} catch (JSONException e) {
			return "";
		}
	}

	/**
	 * Get long value
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static long getLongValue(JSONObject obj, String key) {
		try {
			return obj.isNull(key) ? 0L : obj.getLong(key);
		} catch (JSONException e) {
			return 0L;
		}
	}

	/**
	 * Get int value
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static int getIntValue(JSONObject obj, String key) {
		try {
			return obj.isNull(key) ? 0 : obj.getInt(key);
		} catch (JSONException e) {
			return 0;
		}
	}

	/**
	 * Get Double
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static Double getDoubleValue(JSONObject obj, String key) {
		double d = 0.0;
		try {
			return obj.isNull(key) ? d : obj.getDouble(key);
		} catch (JSONException e) {
			return d;
		}
	}

	/**
	 * Get boolean
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(JSONObject obj, String key) {
		try {
			return obj.isNull(key) ? false : obj.getBoolean(key);
		} catch (JSONException e) {
			return false;
		}
	}
}
