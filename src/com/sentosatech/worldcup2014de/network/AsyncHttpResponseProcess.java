package com.sentosatech.worldcup2014de.network;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.utility.DialogUtility;
import com.sentosatech.worldcup2014de.utility.LemonProgressDialog;

/**
 * AsyncHttpResponseProcess: process server response
 * 
 * @author Lemon
 */
public class AsyncHttpResponseProcess implements AsyncHttpResponseListener {
	private static final String TAG = "AsyncHttpResponseProcess";

	private Context context;
	LemonProgressDialog progressDialog;

	public AsyncHttpResponseProcess(Context context) {
		this.context = context;
	}

	@Override
	public void before() {
		// Show waiting dialog during connection

		// context.showProgressDialog();
		try {
			progressDialog = new LemonProgressDialog(context);
			progressDialog.setCancelable(false);
			progressDialog.show();
		} catch (Exception e) {
		}
	}

	@Override
	public void after(int statusCode, String response) {
		// Process server response
		// context.closeProgressDialog();
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog.cancel();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		switch (statusCode) {

		case AsyncHttpBase.NETWORK_STATUS_OFF:
			try {
				DialogUtility.toast(context, R.string.msg_no_interet);
			} catch (Exception e) {
				DialogUtility.toast(context, R.string.msg_no_interet);
				e.printStackTrace();
			}
			break;
		case AsyncHttpBase.NETWORK_STATUS_OK:
			processHttpResponse(response);
			break;
		default:
			try {
				DialogUtility.toast(context, R.string.msg_no_interet);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DialogUtility.toast(context, R.string.msg_no_interet);
			}
		}
	}

	/**
	 * Process HttpResponse
	 * 
	 * @param response
	 */
	public void processHttpResponse(String response) {
		String json = "";
		try {
			// Get json response
			long current = System.currentTimeMillis();
			Log.d("ProcessJSOn", "JSON Parser  Receive" + current);
			json = response;
			// json = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

			if (json == null) {
				DialogUtility.alert(context, "Can't extract server data");
				return;
			}
			// Parser information
			long after = System.currentTimeMillis();
			Log.d("ProcessJSOn", "JSON Parser Convert ok " + (after - current));
			Log.i(TAG, "Webservice response :" + json);
			// CommonInfo commonInfo = ParserUtility.parserCommonResponse(json);
			// if (commonInfo.isSuccess()) {
			processIfResponseSuccess(json);
			// } else {
			// processIfResponseFail();
			// context.checkInvalidToken(commonInfo.getMessage());
			// }
		} catch (Exception e) {
			e.printStackTrace();
			try {
				DialogUtility.alert(context, "Connection error");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				DialogUtility.alert(context, "Connection error");
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Interface function
	 * 
	 * @throws JSONException
	 */
	public void processIfResponseSuccess(String response) {
		Log.i(TAG, "Process if response is success ===================");
	}

	/**
	 * Interface function
	 */
	public void processIfResponseFail() {
		// SmartLog.log(TAG, "Process if response is fail ===================");
	}
}
