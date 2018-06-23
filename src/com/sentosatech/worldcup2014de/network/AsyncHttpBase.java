/*
 * Name: AsyncHttpBase.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * AsyncHttpBase is base class for AsyncHttpGet and AsyncHttpPost class
 * 
 * 
 */
public class AsyncHttpBase extends AsyncTask<String, Integer, String> {
	// Network status

	public static final int NETWORK_STATUS_OK = 0;
	public static final int NETWORK_STATUS_OFF = 1;
	public static final int NETWORK_STATUS_ERROR = 2;

	protected Context context;
	protected AsyncHttpResponseListener listener;
	protected List<NameValuePair> parameters;
	protected String response;
	protected boolean isShowWaitingDialog;
	protected int statusCode;

	protected boolean isExternalParam = false;
	protected String jsonString = "";
	protected String url = "";

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpBase(Context context, AsyncHttpResponseListener listener,
			List<NameValuePair> parameters, boolean isShowWatingDilog) {
		this.context = context;
		this.listener = listener;
		this.parameters = parameters;
		this.isShowWaitingDialog = isShowWatingDilog;
	}

	public AsyncHttpBase(Context context, AsyncHttpResponseListener listener,
			String json, boolean isShowWatingDilog) {
		this.context = context;
		this.listener = listener;
		this.isExternalParam = true;
		this.jsonString = json;
		this.isShowWaitingDialog = isShowWatingDilog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (isShowWaitingDialog)
			listener.before();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... args) {
		if (NetworkUtility.getInstance(context).isNetworkAvailable()) {
			// Request to server if network is available
			Log.e("da vao day", "da vao day net work ok");
			return request(args[0]);
		} else {
			// Return status if network is not available
			statusCode = NETWORK_STATUS_OFF;
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// Call method to process http status code and response
		listener.after(statusCode, response);
	}

	/**
	 * Send request to server
	 * 
	 * @param url
	 * @return
	 */
	protected String request(String url) {
		// This function will be implemented in AsyncHttpGet and AsyncHttpPost
		// class
		return null;
	}

	// ============================================================================

	/**
	 * Create HttpClient based on HTTP or HTTPS protocol that is parsed from url
	 * parameter. With HTTPS protocol, we accept all SSL certificates.
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	protected HttpClient createHttpClient(String url, HttpParams params) {
		if ((url.toLowerCase().startsWith("https"))) {
			// HTTPS process
			try {

				KeyStore trustStore = KeyStore.getInstance(KeyStore
						.getDefaultType());
				trustStore.load(null, null);

				SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory
						.getSocketFactory(), 80));
				registry.register(new Scheme("https", sf, 443));

				ClientConnectionManager ccm = new ThreadSafeClientConnManager(
						params, registry);

				return new DefaultHttpClient(ccm, params);
			} catch (Exception e) {
				return new DefaultHttpClient(params);
			}
		} else {
			// HTTP process
			return new DefaultHttpClient(params);
		}
	}

	// ============================ Https functions ============================

	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open HTTPS connection. Use this method to setup and accept all SSL
	 * certificates from HTTPS protocol.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection openSConnection(String url)
			throws IOException {
		URL theURL = new URL(url);
		trustAllHosts();
		HttpsURLConnection https = (HttpsURLConnection) theURL.openConnection();
		https.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		return https;
	}

	/**
	 * Open HTTP connection
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection openConnection(String url)
			throws IOException {
		URL theURL = new URL(url);
		return (HttpURLConnection) theURL.openConnection();
	}
}
