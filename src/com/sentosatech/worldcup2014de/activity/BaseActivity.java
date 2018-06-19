package com.sentosatech.worldcup2014de.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

	public BaseActivity self;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
		try {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
				this.getActionBar().hide();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onCreate(arg0);
		self = this;

	}

}
