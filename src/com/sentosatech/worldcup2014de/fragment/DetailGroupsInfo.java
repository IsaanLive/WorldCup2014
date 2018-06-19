package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.database.DatabaseUtility;
import com.sentosatech.worldcup2014de.object.Country;

public class DetailGroupsInfo extends BaseFragment {

	private WebView webView;
	private ArrayList<Country> arrCountry;
	private LinearLayout layout1, layout2, layout3, layout4;
	private ImageView imgCountry1, imgCountry2, imgCountry3, imgCountry4;
	private TextView lblKeyName1, lblKeyName2, lblKeyName3, lblKeyName4;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_info, container, false);
		getActivity().setProgressBarVisibility(true);
		arrCountry = new ArrayList<Country>();
		initUi(view);
		initControl();
		return view;
	}

	private void initControl() {
		imgCountry1.setImageResource(GlobalValue.listFlags.get(arrCountry
				.get(0).getCountryId()));
		imgCountry2.setImageResource(GlobalValue.listFlags.get(arrCountry
				.get(1).getCountryId()));
		imgCountry3.setImageResource(GlobalValue.listFlags.get(arrCountry
				.get(2).getCountryId()));
		imgCountry4.setImageResource(GlobalValue.listFlags.get(arrCountry
				.get(3).getCountryId()));
		lblKeyName1.setText(arrCountry.get(0).getCountryKey());
		lblKeyName2.setText(arrCountry.get(1).getCountryKey());
		lblKeyName3.setText(arrCountry.get(2).getCountryKey());
		lblKeyName4.setText(arrCountry.get(3).getCountryKey());
		layout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layout1.setBackgroundResource(R.drawable.bg_click_flag_blue);
				layout2.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout3.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout4.setBackgroundColor(getResources().getColor(R.color.background_frame));
				loadWebView(0);
			}
		});
		layout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout2.setBackgroundResource(R.drawable.bg_click_flag_blue);
				layout1.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout3.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout4.setBackgroundColor(getResources().getColor(R.color.background_frame));
				loadWebView(1);
			}
		});
		layout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout3.setBackgroundResource(R.drawable.bg_click_flag_blue);
				layout2.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout1.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout4.setBackgroundColor(getResources().getColor(R.color.background_frame));
				loadWebView(2);
			}
		});
		layout4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout4.setBackgroundResource(R.drawable.bg_click_flag_blue);
				layout2.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout3.setBackgroundColor(getResources().getColor(R.color.background_frame));
				layout1.setBackgroundColor(getResources().getColor(R.color.background_frame));
				loadWebView(3);
			}
		});
	}

	private void initUi(View view) {
		imgCountry1 = (ImageView) view.findViewById(R.id.imgCountry1);
		imgCountry2 = (ImageView) view.findViewById(R.id.imgCountry2);
		imgCountry3 = (ImageView) view.findViewById(R.id.imgCountry3);
		imgCountry4 = (ImageView) view.findViewById(R.id.imgCountry4);
		layout1 = (LinearLayout) view.findViewById(R.id.layout1);
		layout2 = (LinearLayout) view.findViewById(R.id.layout2);
		layout3 = (LinearLayout) view.findViewById(R.id.layout3);
		layout4 = (LinearLayout) view.findViewById(R.id.layout4);
		lblKeyName1 = (TextView) view.findViewById(R.id.lblKeyName1);
		lblKeyName2 = (TextView) view.findViewById(R.id.lblKeyName2);
		lblKeyName3 = (TextView) view.findViewById(R.id.lblKeyName3);
		lblKeyName4 = (TextView) view.findViewById(R.id.lblKeyName4);
		arrCountry = DatabaseUtility.getlistCountryByIdGroup(getActivity(),
				GlobalValue.IdGroup);
		webView = (WebView) view.findViewById(R.id.webViewInfo);
		loadWebView(0);
		layout1.setBackgroundResource(R.drawable.bg_click_flag_blue);

	}

	private void loadWebView(int i) {
		String urlWeb = arrCountry.get(i).getCountryInfo();
		if ((urlWeb + "1").equalsIgnoreCase("1")) {
			Toast.makeText(getActivity(), R.string.toast_message_no_data_match,
					Toast.LENGTH_LONG).show();
			webView.setVisibility(View.GONE);
		} else {
			try {
				webView.setWebChromeClient(new WebChromeClient());
				WebSettings setting = webView.getSettings();
				setting.setLoadWithOverviewMode(true);
				setting.setUseWideViewPort(true);
				webView.loadUrl(urlWeb);

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getActivity(),
						R.string.toast_message_no_data_match, Toast.LENGTH_LONG)
						.show();
			}
		}
	}

}
