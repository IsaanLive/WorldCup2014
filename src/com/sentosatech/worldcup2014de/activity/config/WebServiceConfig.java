package com.sentosatech.worldcup2014de.activity.config;

/**
 * WebServiceConfig class contains web service settings
 * 
 * @author Lemon
 */
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
//	 + "fruitysolution.vn:8888/wc2014/backend/api/";

	public static String APP_DOMAIN = PROTOCOL_HTTP
			+ "worldcup2014.guiltyplanet.com/backend/api/";

	// ===================== WEB SERVICE LINK =====================
//	public static String URL_CHECK_VERSION = "http://fruitysolution.vn:8888/wc2014/version/han_version.txt";

	public static String URL_GET_ALL_SCHEDULE = APP_DOMAIN + "schedule?t=1";
	public static String URL_GET_GROUP_DETAIL = APP_DOMAIN + "group?g=";
	public static String URL_GET_MATCH_DETAIL = APP_DOMAIN + "match?id=";
	public static String URL_GET_MATCH_BY_GROUPD = APP_DOMAIN
			+ "schedule?t=6&g=";
	public static String URL_GET_TREE_MAP = APP_DOMAIN + "sitemap";
	public static String URL_GET_TOP_SCORE = APP_DOMAIN + "topscore";
	public static String URL_REGISTER_VERSION = APP_DOMAIN + "versionText?vs=";
//	public static String URL_REGISTER_DEVICE="http://jayadata.co.id/bola/api/store_device_id";

	// ===================== PARAMETER =====================

	//

}
