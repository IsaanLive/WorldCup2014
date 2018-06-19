/*
 * Name: $RCSfile: AsyncHttpPost.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Apr 21, 2011 2:43:05 PM $
 *
 * Copyright (C) 2011 COMPANY NAME, Inc. All rights reserved.
 */

package com.sentosatech.worldcup2014de.network;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.utility.DebugLog;

/**
 * AsyncHttpGet makes http post request based on AsyncTask
 * 
 * @author Lemon
 */
public class AsyncHttpPost extends AsyncHttpBase {
	private static final String TAG = "AsyncHttpPost";

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpPost(Context context, AsyncHttpResponseListener listener,
			List<NameValuePair> parameters, boolean isShowDilog) {
		super(context, listener, parameters, isShowDilog);
	}

	public AsyncHttpPost(Context context, AsyncHttpResponseListener listener,
			String json, boolean isShowDilog) {
		super(context, listener, json, isShowDilog);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param process
	 * @param parameters
	 */
	public AsyncHttpPost(Context context, AsyncHttpResponseProcess process,
			List<NameValuePair> parameters, boolean isShowDilag) {
		super(context, process, parameters, isShowDilag);
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
			HttpConnectionParams.setConnectionTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpClient httpclient = createHttpClient(url, params);

			HttpPost httppost = new HttpPost(url);
			DebugLog.d(TAG, "URL POST: " + url);
			DebugLog.d(TAG, "DATA POST: " + parameters.toString());

			if (!isExternalParam)

				httppost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			else
			// Post json
			{
				StringEntity se;
				se = new StringEntity(jsonString, HTTP.UTF_8);
				DebugLog.d("AsyncPost", "Webservice POST INPUT : " + jsonString);
				httppost.setEntity(se);

				httppost.setHeader("Accept", "application/json");
				httppost.setHeader("Content-type",
						"application/json;charset=UTF-8");

			}

			// httppost.setHeader("Content-Type", "multipart/form-data");
			response = EntityUtils.toString(httpclient.execute(httppost)
					.getEntity(), HTTP.UTF_8);
			statusCode = NETWORK_STATUS_OK;
		} catch (Exception e) {
			statusCode = NETWORK_STATUS_ERROR;
			e.printStackTrace();
		}
		return null;
	}
}
