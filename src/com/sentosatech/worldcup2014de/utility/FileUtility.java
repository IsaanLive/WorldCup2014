package com.sentosatech.worldcup2014de.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.MySharedPreferences;
import com.sentosatech.worldcup2014de.info.DatabaseConfig;
import com.sentosatech.worldcup2014de.object.ImageInfo;

public final class FileUtility extends Activity {

	public static void enableStrictMode() {
		try {
			@SuppressWarnings("rawtypes")
			Class strictModeClass = Class.forName("android.os.StrictMode");
			@SuppressWarnings("rawtypes")
			Class strictModeThreadPolicyClass = Class
					.forName("android.os.StrictMode$ThreadPolicy");
			Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(
					null);
			@SuppressWarnings("unchecked")
			Method method_setThreadPolicy = strictModeClass.getMethod(
					"setThreadPolicy", strictModeThreadPolicyClass);
			method_setThreadPolicy.invoke(null, laxPolicy);
		} catch (Exception e) {
		}
	}

	public Bitmap getBitmapFromAssets(Context context, String fileName)
			throws IOException {
		AssetManager assetManager = context.getAssets();
		InputStream istr = assetManager.open(fileName);
		Bitmap bitmap = BitmapFactory.decodeStream(istr);

		return bitmap;
	}

	public int getImageResourceIdByCountryCode(Context context,
			String countryCode) {
		int result = 0;

		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryCode().equalsIgnoreCase(countryCode)) {
				result = item.getImageId();
				break;

			}
		}
		return result;
	}

	public String getCountryNameByCode(Context context, String countryCode) {
		String result = "";
		for (ImageInfo item : GlobalValue.listImageInfo) {
			if (item.getCountryCode().equalsIgnoreCase(countryCode)) {
				result = item.getCountryName();
				break;
			}
		}
		return result;
	}

	/**
	 * filename need external file
	 * 
	 * @param Url
	 * @param filename
	 * @return
	 */
	public static boolean storeImage(String Url, String filename) {
		// get path to external storage (SD card)
		String musicStoragePath = Environment.getExternalStorageDirectory()
				+ "/music/";
		File StorageDir = new File(musicStoragePath);
		// create storage directories, if they don't exist
		if (!StorageDir.exists()) {
			StorageDir.mkdirs();
		}
		try {
			File file = new File(StorageDir.toString(), filename);

			if (file.exists()) {
				file.delete();
			}

			FileOutputStream out = new FileOutputStream(file);
			InputStream is = (InputStream) new URL(Url).getContent();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.flush();
			out.close();
			Log.d("asd", "asd: finish ");
		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving music file: " + e.getMessage());
			return false;
		} catch (IOException e) {
			Log.w("TAG", "Error saving music file: " + e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean checkNeedUpdate(Context context, String versionUrl) {
		InputStream newStreamVersion;
		float oldVersion = 0, newVersion = 0;
		try {
			URL urlfile = new URL(versionUrl);
			newStreamVersion = (InputStream) urlfile.getContent();
			String versionStr = readStringFromIS(newStreamVersion);
			Log.d("FileUtility", "Server database version : " + versionStr);
			newVersion = Float.parseFloat(versionStr);

			GlobalValue.prefs = new MySharedPreferences(context);
			oldVersion = GlobalValue.prefs.getFloatValue("DATA_VERSION");
			SmartLog.log("New data ", "Old version :" + oldVersion);
			SmartLog.log("New data ", "New version :" + newVersion);
			if (oldVersion < newVersion) {
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return true;
		}

	}

	public static String getFileContent(Context context, String versionUrl) {
		InputStream newStreamVersion;
		try {
			URL urlfile = new URL(versionUrl);
			newStreamVersion = (InputStream) urlfile.getContent();
			String versionStr = readStringFromIS(newStreamVersion);
			return versionStr;

		} catch (Exception ex) {
			ex.printStackTrace();
			return "1";
		}

	}

	public static float getServerDbVersion() {
		InputStream newStreamVersion;
		float newVersion = 0;
		try {
			URL urlfile = new URL(GlobalValue.URL_FILE_VERSION_HOST);
			newStreamVersion = (InputStream) urlfile.getContent();
			newVersion = Float.parseFloat(readStringFromIS(newStreamVersion));
			SmartLog.log("New data ", "New version :" + newVersion);
			return newVersion;

		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	}

	public static boolean storeFileDatabase(String url) {
		int count;
		try {
			URL urlfile = new URL(url);
			URLConnection ucon = urlfile.openConnection();
			InputStream input = ucon.getInputStream();

			File dataFolder = new File(DatabaseConfig.getInstance()
					.getDatabasepath());
			if (!dataFolder.exists()) {
				dataFolder.mkdir();
			}
			OutputStream output = new FileOutputStream(DatabaseConfig
					.getInstance().getDatabasefullpath());

			byte data[] = new byte[1024];
			while ((count = input.read(data)) != -1) {
				output.write(data, 0, count);
			}
			// flushing output
			output.flush();
			output.close();
			input.close();

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static String readStringFromIS(InputStream is) {
		String datax = "";
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader buffreader = new BufferedReader(isr);

			String readString = buffreader.readLine();
			while (readString != null) {
				datax = datax + readString;
				readString = buffreader.readLine();
			}

			isr.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return datax;
	}

}
