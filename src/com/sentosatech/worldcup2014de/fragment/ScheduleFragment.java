/*
 * Name: ScheduleFragment.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.InfoMatchActivity;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.activity.config.JsonConfig;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.adapter.ScheduleAdapter;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.modelmanager.ParserUitility;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.utility.AssetUtil;
import com.sentosatech.worldcup2014de.utility.DebugLog;
import com.sentosatech.worldcup2014de.utility.NetworkUtility;
import com.sentosatech.worldcup2014de.utility.StringUtility;

public class ScheduleFragment extends BaseFragment {
	private ListView lsvSchedule;
	private ArrayList<Match> listCurrentMath;
	private ScheduleAdapter scheduleAdapter;
	private Spinner spnSelect, spnCountry;
	private TextView lblNoData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_schedule, container, false);
		initUi(view);
		initData();
		loadSchedule();

		return view;
	}

	private void loadSchedule() {

		if (GlobalValue.listAllMath == null
				|| GlobalValue.listAllMath.size() == 0) {

			if (NetworkUtility.getInstance(self).checkNetworkStatus()) {
				callAPI();
			} else {
				DebugLog.e("JSON", JsonConfig.FILE_ALL_MATCH);
				String json = AssetUtil.getString(getActivity(),
						JsonConfig.FILE_ALL_MATCH);
				try {
					GlobalValue.listAllMath = ParserUitility
							.parserListMatch(new JSONObject(json));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listCurrentMath = GlobalValue.listAllMath;
				bindListView();
				closeProgressDialog();
				settingControl();
			}

		} else {
			listCurrentMath = GlobalValue.listAllMath;
			bindListView();
			settingControl();
		}
	}

	public void callAPI() {
		try {
			if (self != null) {

				ModelManager.getData(self, true,
						WebServiceConfig.URL_GET_ALL_SCHEDULE,
						new ModelManagerListener() {
							@Override
							public void onWSError() {
								// TODO Auto-generated method stub
							}

							@Override
							public void OnSuccess(String json) {

								try {
									GlobalValue.listAllMath = ParserUitility
											.parserListMatch(new JSONObject(
													json));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								listCurrentMath = GlobalValue.listAllMath;
								bindListView();
								closeProgressDialog();
								settingControl();

							}
						});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void settingControl() {

		TextView tv = (TextView) spnSelect.getSelectedView();
		if (tv != null)
			tv.setTextColor(Color.WHITE);
		TextView tv2 = (TextView) spnCountry.getSelectedView();
		if (tv2 != null)
			tv2.setTextColor(Color.WHITE);

		spnSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				try {
					((TextView) parent.getChildAt(0))
							.setTextColor(getResources()
									.getColor(R.color.white));
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String nameSpiner = spnSelect.getSelectedItem().toString();
				String today1 = getString(R.string.to_day);
				String allOfSchedule = getString(R.string.all_of_schedule);
				String history = getString(R.string.history);
				String future = getString(R.string.future);
				String country = getString(R.string.country);

				if (nameSpiner.equalsIgnoreCase(today1)) {
					listCurrentMath = getListMathByTime(2);
					spnCountry.setVisibility(View.GONE);
				} else if (nameSpiner.equalsIgnoreCase(allOfSchedule)) {
					listCurrentMath = GlobalValue.listAllMath;
					spnCountry.setVisibility(View.GONE);
				} else if (nameSpiner.equalsIgnoreCase(history)) {
					listCurrentMath = getListMathByTime(3);
					spnCountry.setVisibility(View.GONE);
				} else if (nameSpiner.equalsIgnoreCase(future)) {
					listCurrentMath = getListMathByTime(1);
					spnCountry.setVisibility(View.GONE);
				} else if (nameSpiner.equalsIgnoreCase(country)) {
					spnCountry.setVisibility(View.VISIBLE);

					if (spnCountry.getSelectedItem() == null) {
						spnCountry.setSelection(0);
						GlobalValue.NameCountry = GlobalValue.listImageInfo
								.get(0).getCountryName();
					}

					listCurrentMath = getListMathByCountry(GlobalValue.NameCountry);
				}
				bindListView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				try {
					((TextView) arg0.getChildAt(0)).setTextColor(getResources()
							.getColor(R.color.white));
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		spnSelect.setSelection(0, true);
		lsvSchedule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Match match = listCurrentMath.get(position);
				InfoMatchActivity.currentMath = match;
				Intent i = new Intent(getActivity(), InfoMatchActivity.class);
				startActivity(i);
			}
		});
	}

	private void bindListView() {

		scheduleAdapter = new ScheduleAdapter(getActivity(), listCurrentMath);
		if (listCurrentMath != null && listCurrentMath.size() > 0)
			lblNoData.setVisibility(View.GONE);
		else
			lblNoData.setVisibility(View.VISIBLE);
		lsvSchedule.setAdapter(scheduleAdapter);

	}

	private void initUi(View view) {
		lsvSchedule = (ListView) view.findViewById(R.id.lsvTab1);
		spnSelect = (Spinner) view.findViewById(R.id.spnSchedule);
		spnCountry = (Spinner) view.findViewById(R.id.spnCountry);
		spnCountry.setVisibility(View.GONE);
		lblNoData = (TextView) view.findViewById(R.id.lblNoData);
	}

	private void initData() {

		// ----------Creat Spiner--------
		List<String> arrSpiner = new ArrayList<String>();
		arrSpiner.add(getString(R.string.all_of_schedule));
		arrSpiner.add(getString(R.string.history));
		arrSpiner.add(getString(R.string.country));
		arrSpiner.add(getString(R.string.to_day));
		arrSpiner.add(getString(R.string.future));
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_dropdown_item_1line, arrSpiner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.page_spiner, arrSpiner);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSelect.setAdapter(adapter);

		// ----------Creat SpinerCountry ----------

		List<String> arrSpinerCountry = new ArrayList<String>();

		for (int i = 0; i < GlobalValue.listImageInfo.size(); i++) {
			if (i < GlobalValue.listImageInfo.size() - 1)
				arrSpinerCountry.add(GlobalValue.listImageInfo.get(i)
						.getCountryName2());
		}
		ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(
				getActivity(), R.layout.page_spiner, arrSpinerCountry);
		adapterCountry
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCountry.setAdapter(adapterCountry);
		if (GlobalValue.NameCountry == null) {
			GlobalValue.NameCountry = spnCountry.getSelectedItem().toString();
		}

		spnCountry.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if ((TextView) arg0.getChildAt(0) != null) {
					((TextView) arg0.getChildAt(0)).setTextColor(getResources()
							.getColor(R.color.white));
				}
				GlobalValue.NameCountry = spnCountry.getSelectedItem()
						.toString();
				listCurrentMath = getListMathByCountry(GlobalValue.listImageInfo
						.get(arg2).getCountryName2());

				bindListView();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	private ArrayList<Match> getListMathByCountry(String countryId) {
		ArrayList<Match> result = new ArrayList<Match>();

		for (Match item : GlobalValue.listAllMath) {
			// DebugLog.d("FIND NAME", "FIND NAME :"+countryId);
			// DebugLog.d("List NAME", "list NAME :"+item.getFullNameTeam1());
			// DebugLog.d("List code", "list code :"+item.getCodeTeam1());
			if (item.getFullNameTeam1().equalsIgnoreCase(countryId)
					|| item.getFullNameTeam2().equalsIgnoreCase(countryId))
				result.add(item);
		}
		return result;
	}

	// 1 : future,2: today, 3:history
	private ArrayList<Match> getListMathByTime(int type) {

		ArrayList<Match> result = new ArrayList<Match>();
		for (Match item : GlobalValue.listAllMath) {
			switch (type) {
			case 1:
				if (StringUtility.isDateSoonerNow("yyyy-MM-dd HH:mm",
						item.getMatchDate()))
					result.add(item);
				break;
			case 2:
				if (StringUtility.isDateEqualToday("yyyy-MM-dd HH:mm",
						item.getMatchDate()))
					result.add(item);
				break;
			case 3:
				if (!StringUtility.isDateSoonerNow("yyyy-MM-dd HH:mm",
						item.getMatchDate()))
					result.add(item);
				break;

			default:
				break;
			}
		}

		return result;

	}
}
