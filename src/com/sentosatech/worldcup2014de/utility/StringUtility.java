package com.sentosatech.worldcup2014de.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.widget.EditText;

import com.sentosatech.worldcup2014de.activity.config.GlobalValue;

@SuppressLint("SimpleDateFormat")
public final class StringUtility {

	public static String formatDate(String dateFormat, String dateInput,
			String outputFormat) {
//
//		Log.d("DateFormat", "Input : " + dateInput + " : Input format : "
//				+ dateFormat);
		String aDateFormat = dateFormat.equalsIgnoreCase("") ? "yyyy-MM-dd"
				: dateFormat;
//		Log.d("", "aDateFormat: " + aDateFormat);
		SimpleDateFormat inputFormat = new SimpleDateFormat(aDateFormat);
		inputFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));

		SimpleDateFormat outputFormatDate = new SimpleDateFormat(outputFormat);
		outputFormatDate.setTimeZone(TimeZone
				.getTimeZone(GlobalValue.deviceTimeZone));
		Date startDate = null;

		try {
			startDate = inputFormat.parse(dateInput);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			startDate = new Date();
		}
//		Log.d("", "startDate" + startDate.toString());

		return outputFormatDate.format(startDate);

	}

	public static boolean isDateSoonerNow(String dateFormat, String dateInput) {

		SimpleDateFormat inputFormat = new SimpleDateFormat(dateFormat);
		inputFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
		Date startDate = null;
		Date dateNow = new Date();
		try {
			startDate = inputFormat.parse(dateInput);

		} catch (ParseException e) {
			e.printStackTrace();
			startDate = new Date();
		}
		return dateNow.compareTo(startDate) < 0;
	}
	
	public static boolean isDateSoonerNowWithTime(String dateFormat, String dateInput,int minutes) {

		SimpleDateFormat inputFormat = new SimpleDateFormat(dateFormat);
		inputFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
		Date startDate = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, minutes);
		Date dateAftertime=cal.getTime();
		try {
			startDate = inputFormat.parse(dateInput);

		} catch (ParseException e) {
			e.printStackTrace();
			startDate = new Date();
		}
		
		
		return dateAftertime.before(startDate);
	}

	public static boolean isDateEqualToday(String dateFormat, String dateInput) {

		SimpleDateFormat inputFormat = new SimpleDateFormat(dateFormat);
		inputFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yy");
		dayFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault()
				.getDisplayName(false, TimeZone.SHORT)));
		Date startDate = null;
		Date dateNow = new Date();
		try {
			startDate = inputFormat.parse(dateInput);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			startDate = new Date();
		}
		
		String dateToday = dayFormat.format(dateNow);
		String dateInputStr = dayFormat.format(startDate);
		return dateToday.equalsIgnoreCase(dateInputStr);

	}

	/**
	 * Check Edit Text input string
	 * 
	 * @param editText
	 * @return
	 */
	public static boolean isEmpty(EditText editText) {
		if (editText == null
				|| editText.getEditableText() == null
				|| editText.getEditableText().toString().trim()
						.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	/**
	 * Check input string
	 * 
	 * @param editText
	 * @return
	 */
	public static boolean isEmpty(String editText) {
		if (editText == null || editText.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	public static String getSubString(String input, int maxLength) {
		String temp = input;
		if (input.length() < maxLength)
			return temp;
		else
			return input.substring(0, maxLength - 1) + "...";
	}

	/**
	 * Merge all elements of a string array into a string
	 * 
	 * @param strings
	 * @param separator
	 * @return
	 */
	public static String join(String[] strings, String separator) {
		StringBuffer sb = new StringBuffer();
		int max = strings.length;
		for (int i = 0; i < max; i++) {
			if (i != 0)
				sb.append(separator);
			sb.append(strings[i]);
		}
		return sb.toString();
	}

	/**
	 * Convert current date time to string
	 * 
	 * @param updateTime
	 * @return
	 */
	public static String convertNowToFullDateString() {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		// dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));

		// Calendar calendar =
		// Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		// return dateformat.format(calendar.getTime());
		return dateformat.format(new Date());
	}

	public static String convertNowToDateString(String format) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		// dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));

		// Calendar calendar =
		// Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		// return dateformat.format(calendar.getTime());
		return dateformat.format(new Date());
	}

	/**
	 * Initial sync date string
	 * 
	 * @return
	 */
	public static String initDateString() {
		return "1900-01-01 09:00:00";
	}

	/**
	 * Convert a string divided by ";" to multiple xmpp users
	 * 
	 * @param userString
	 * @return
	 */
	public static String[] convertStringToXmppUsers(String userString) {
		return userString.split(";");
	}

	/**
	 * get Unique Random String
	 * 
	 * @return
	 */

	public static String getUniqueRandomString() {

		// return String.valueOf(System.currentTimeMillis());
		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}
}
