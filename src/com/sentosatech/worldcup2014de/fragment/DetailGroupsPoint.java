/*
 * Name: DetailGroupsPoint.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.JsonConfig;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.adapter.DetailGroupsPointAdapter;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.modelmanager.ParserUitility;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.utility.AssetUtil;
import com.sentosatech.worldcup2014de.utility.NetworkUtility;

public class DetailGroupsPoint extends BaseFragment {
	private TextView lblNameDetailGroupPoint;
	private ListView lsvPoint;
	private ArrayList<Country> arrCountry;
	private DetailGroupsPointAdapter detaiGroupsPointAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.page_detail_group_point,
				container, false);
		initUi(view);
		return view;
	}

	public void initUi(View view) {

		lsvPoint = (ListView) view.findViewById(R.id.lsvPoint);
		lblNameDetailGroupPoint = (TextView) view
				.findViewById(R.id.lblNameDetailGroupPoint);
		lblNameDetailGroupPoint.setText(getString(R.string.point_group_title)
				+ " " + GlobalValue.Name);
		loadData();

	}

	public void loadData() {

		if (NetworkUtility.getInstance(self).checkNetworkStatus()) {
			ModelManager.getData(getActivity(), true,
					WebServiceConfig.URL_GET_GROUP_DETAIL
							+ getGroupId(GlobalValue.IdGroup),
					new ModelManagerListener() {

						@Override
						public void onWSError() {
						}

						@Override
						public void OnSuccess(String json) {
							if (json != null)
								bindData(json);
						}

					});
		} else {
			String json = getJsonData(GlobalValue.IdGroup);
			bindData(json);
		}

	}

	private void bindData(String json) {
		Log.d("ALL SCHEDULE", "JSON : " + json);
		try {
			arrCountry = ParserUitility.parserListCountryPoint(new JSONObject(json));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Collections.sort(arrCountry, new Comparator<Country>() {

			public int compare(Country lhs, Country rhs) {
				if (rhs.getPoint() == lhs.getPoint()) {
					int hs1 = rhs.getAllGoalNumber() - rhs.getAllLoseNumber();
					int hs2 = lhs.getAllGoalNumber() - lhs.getAllLoseNumber();
					if (hs1 == hs2) {
						return rhs.getAllGoalNumber() - lhs.getAllGoalNumber();
					} else
						return (hs1 - hs2);
				} else
					return rhs.getPoint() - lhs.getPoint();
			}
		});
		detaiGroupsPointAdapter = new DetailGroupsPointAdapter(getActivity(),
				arrCountry);
		lsvPoint.setAdapter(detaiGroupsPointAdapter);
	}

	private String getJsonData(int idGroup) {
		String url = "";
		switch (idGroup) {
		case 1:
			url = JsonConfig.FILE_GROUP_A;
			break;
		case 2:
			url = JsonConfig.FILE_GROUP_B;
			break;
		case 3:
			url = JsonConfig.FILE_GROUP_C;
			break;
		case 4:
			url = JsonConfig.FILE_GROUP_D;
			break;
		case 5:
			url = JsonConfig.FILE_GROUP_E;
			break;
		case 6:
			url = JsonConfig.FILE_GROUP_F;
			break;
		case 7:
			url = JsonConfig.FILE_GROUP_G;
			break;
		case 8:
			url = JsonConfig.FILE_GROUP_H;
			break;

		default:
			break;

		}
		return AssetUtil.getString(self, url);
	}
}
