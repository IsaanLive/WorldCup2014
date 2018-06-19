package com.sentosatech.worldcup2014de.network;

import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Log;

import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.utility.DebugLog;

/**
 * AsyncHttpGet makes http get request based on AsyncTask
 * 
 * @author Lemon
 */
public class AsyncHttpGet extends AsyncHttpBase {
	private static final String TAG = "AsyncHttpGet";

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpGet(Context context, AsyncHttpResponseListener listener,
			List<NameValuePair> parameters, boolean isShowWaitingDialog) {
		super(context, listener, parameters, isShowWaitingDialog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fgsecure.meyclub.app.network.AsyncHttpBase#request(java.lang.String)
	 */
	@Override
	protected String request(String url) {
		try {
			this.url = url;
			HttpParams params = new BasicHttpParams();
			

			String combinedParams = "";
			if (!parameters.isEmpty()) {
				combinedParams += "?";
				for (NameValuePair p : parameters) {
					
					Log.e("param", "param :" + p.getName() +"==="+p.getValue());
					String paramString = p.getName() + "="
							+ URLEncoder.encode(p.getValue(), "UTF-8");
					if (combinedParams.length() > 1) {
						combinedParams += "&" + paramString;
					} else {
						combinedParams += paramString;
					}
				}
				Log.i(TAG, "CombineParams : " + combinedParams);
			}
			HttpConnectionParams.setConnectionTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpClient httpclient = createHttpClient(url, params);
			
			Log.e(TAG, "GET URL executed  : " + url + combinedParams);
			HttpGet httpget1 = new HttpGet(url + combinedParams);
			response = EntityUtils.toString(httpclient.execute(httpget1)
					.getEntity(), HTTP.UTF_8);
			// Lemon added
			// httpclient.getConnectionManager().shutdown();
			statusCode = NETWORK_STATUS_OK;
		} catch (Exception e) {
			statusCode = NETWORK_STATUS_ERROR;
			e.printStackTrace();
		}
		return null;
	}
}
