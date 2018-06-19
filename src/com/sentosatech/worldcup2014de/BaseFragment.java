package com.sentosatech.worldcup2014de;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.sentosatech.worldcup2014de.utility.LemonProgressDialog;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment {

	public Activity self;
	public AQuery aq;
	protected LemonProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		self = getActivity();
		aq = new AQuery(self);
	}

	public void showProgressDialog() {
		try {
			// showProgressDialog(getString(R.string.message_please_wait));
			if (progressDialog == null) {
				try {
					progressDialog = new LemonProgressDialog(self);
					progressDialog.show();
					progressDialog.setCancelable(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					progressDialog = new LemonProgressDialog(self);
					progressDialog.show();
					progressDialog.setCancelable(false);
					e.printStackTrace();
				}
			} else {
				if (!progressDialog.isShowing())
					progressDialog.show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			progressDialog = new LemonProgressDialog(self);
			progressDialog.show();
			progressDialog.setCancelable(false);
			e.printStackTrace();
		}
	}

	public void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog.cancel();
			progressDialog = null;
		}

	}

	public void showToast(int idString) {
		Toast.makeText(getActivity(), idString, Toast.LENGTH_SHORT).show();
	}

	public void showToast(String string) {
		Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
	}

	public String getGroupId(int index) {
		String result = "a";
		switch (index) {
		case 1:
			result = "a";
			break;
		case 2:
			result = "b";
			break;
		case 3:
			result = "c";
			break;
		case 4:
			result = "d";
			break;
		case 5:
			result = "e";
			break;
		case 6:
			result = "f";
			break;
		case 7:
			result = "g";
			break;
		case 8:
			result = "h";
			break;

		default:
			break;

		}
		return result;
	}
}
