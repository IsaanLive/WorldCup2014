/*
 * Name: WebServiceConfig.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.activity.config;

public final class WebServiceConfig {
	// Network time out: 60s
	public static int NETWORK_TIME_OUT = 100000;

	// result code for activity result
	public static int RESULT_OK = 1;

	public static String STRING_INVALID_TOKEN = "AccessToken invalid";

	public static String STRING_EXPIRE_TOKEN = "AccessToken expired";

	// ===================== DOMAIN =====================

	// TODO

	public static String PROTOCOL_HTTP = "http://";
	public static String PROTOCOL_HTTPS = "https:";
//	 public static String APP_DOMAIN = PROTOCOL_HTTP
//	 + "euroasia.company:8888/wc2014/backend/api/";

	public static String APP_DOMAIN = PROTOCOL_HTTP
			+ "wc2014.euroasia.compnay/backend/api/";

	// ===================== WEB SERVICE LINK =====================
//	public static String URL_CHECK_VERSION = "https://euroasia.company:8888/wc2014/version/version.txt";

	public static String URL_GET_ALL_SCHEDULE = APP_DOMAIN + "schedule?t=1";
	public static String URL_GET_GROUP_DETAIL = APP_DOMAIN + "group?g=";
	public static String URL_GET_MATCH_DETAIL = APP_DOMAIN + "match?id=";
	public static String URL_GET_MATCH_BY_GROUPD = APP_DOMAIN
			+ "schedule?t=6&g=";
	public static String URL_GET_TREE_MAP = APP_DOMAIN + "sitemap";
	public static String URL_GET_TOP_SCORE = APP_DOMAIN + "topscore";
	public static String URL_REGISTER_VERSION = APP_DOMAIN + "versionText?vs=";
//	public static String URL_REGISTER_DEVICE="http://euroasia.compamny/wc2014/api/store_device_id";

	// ===================== PARAMETER =====================

	//

}
