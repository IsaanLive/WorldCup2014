package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
import com.sentosatech.worldcup2014de.utility.NetworkUtility;

public class DetailGroupsSchedule extends BaseFragment {

	private ListView lsvDetailGroupsSchedule;
	private ArrayList<Match> listMatchDetaiGroups;
	private ScheduleAdapter scheduleDetaiGroupAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_detail_groups_schedule,
				container, false);
		initUI(view);
		initData();
		initControl();
		return view;

	}

	private void initControl() {
		lsvDetailGroupsSchedule
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						Bundle b = new Bundle();
						Match clickedMath = listMatchDetaiGroups.get(position);
						// b.putInt("Match", clickedMath.getMatchId());
						// b.putString("Day", clickedMath.getMatchDate());
						// b.putString("Time", clickedMath.getMatchStartTime());
						// b.putString("Stadium", clickedMath.getStadiumId());
						// b.putInt("Country1", clickedMath.getCountry1());
						// b.putInt("Country2", clickedMath.getCountry2());
						// b.putLong("Goal1", clickedMath.getGoalCountry1());
						// b.putLong("Goal2", clickedMath.getGoalCountry2());
						// b.putString("Info", clickedMath.getMatchInfo());
						// b.putString("Status", clickedMath.getMatchStatus());
						// b.putInt("idStadium", clickedMath.getIdStadium());
						Intent i = new Intent(getActivity(),
								InfoMatchActivity.class);
						InfoMatchActivity.currentMath = clickedMath;
						// i.putExtras(b);
						startActivity(i);
					}
				});

	}

	private void initUI(View view) {
		lsvDetailGroupsSchedule = (ListView) view
				.findViewById(R.id.lsvDetailGroupsSchedule);
	}

	public void initData() {
		// http: // fruitysolution.vn:8888/wc2014/backend/api/schedule?t=6&g=A
		if (NetworkUtility.getInstance(self).checkNetworkStatus()) {

			ModelManager.getData(getActivity(), true,
					WebServiceConfig.URL_GET_MATCH_BY_GROUPD
							+ getGroupId(GlobalValue.IdGroup),
					new ModelManagerListener() {

						@Override
						public void onWSError() {
						}

						@Override
						public void OnSuccess(String json) {
							if (json != null) {
								try {
									listMatchDetaiGroups = ParserUitility
											.parserListMatch(new JSONObject(
													json));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								scheduleDetaiGroupAdapter = new ScheduleAdapter(
										getActivity(), listMatchDetaiGroups);
								lsvDetailGroupsSchedule
										.setAdapter(scheduleDetaiGroupAdapter);
							}
						}
					});

		} else {
			String json = getJsonData(GlobalValue.IdGroup);
			try {
				listMatchDetaiGroups = ParserUitility.parserListMatch(new JSONObject(
						json));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scheduleDetaiGroupAdapter = new ScheduleAdapter(getActivity(),
					listMatchDetaiGroups);
			lsvDetailGroupsSchedule.setAdapter(scheduleDetaiGroupAdapter);
		}

	}

	private String getJsonData(int idGroup) {
		String url = "";
		switch (idGroup) {
		case 1:
			url = JsonConfig.FILE_MATCH_BY_GROUP_A;
			break;
		case 2:
			url = JsonConfig.FILE_MATCH_BY_GROUP_B;
			break;
		case 3:
			url = JsonConfig.FILE_MATCH_BY_GROUP_C;
			break;
		case 4:
			url = JsonConfig.FILE_MATCH_BY_GROUP_D;
			break;
		case 5:
			url = JsonConfig.FILE_MATCH_BY_GROUP_E;
			break;
		case 6:
			url = JsonConfig.FILE_MATCH_BY_GROUP_F;
			break;
		case 7:
			url = JsonConfig.FILE_MATCH_BY_GROUP_G;
			break;
		case 8:
			url = JsonConfig.FILE_MATCH_BY_GROUP_H;
			break;

		default:
			break;

		}
		return AssetUtil.getString(self, url);
	}

}
