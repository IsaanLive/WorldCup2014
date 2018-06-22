/*
 * Name: MainActivity.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.database.DatabaseUtility;
import com.sentosatech.worldcup2014de.fragment.GroupsFragment;
import com.sentosatech.worldcup2014de.fragment.HomeFragment;
import com.sentosatech.worldcup2014de.fragment.ScheduleFragment;
import com.sentosatech.worldcup2014de.fragment.TreeMapFragment;
import com.sentosatech.worldcup2014de.indicator.TabPageIndicator;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.Match;

public class MainActivity extends FragmentActivity {
	private TabPageIndicator indicator;
	private ViewPager page;
	private List<String> listTab;
	private LinearLayout layoutAbout;
	private AdView adView;
	private Button btnRefresh;
	private HomeFragment homeFragment;
	private ScheduleFragment scheduleFragment;
	private GroupsFragment groupFragment;
	private TreeMapFragment treeMapFragment;
	private TextView lblHeader;
	private Activity self;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		self = this;
		page = (ViewPager) findViewById(R.id.viewPager);
		layoutAbout = (LinearLayout) findViewById(R.id.layoutAbout);
		homeFragment = new HomeFragment();
		scheduleFragment = new ScheduleFragment();
		groupFragment = new GroupsFragment();
		treeMapFragment = new TreeMapFragment();
		lblHeader = (TextView) findViewById(R.id.textView1);
		lblHeader.setText(getString(R.string.app_name).toUpperCase());

		listTab = new ArrayList<String>();
		listTab.add(getString(R.string.tab_home));
		listTab.add(getString(R.string.tab_schedule));
		listTab.add(getString(R.string.tab_group));
		listTab.add(getString(R.string.tab_treemap));
		initAdModLayout();

		// ----set data Group ------------

		GlobalValue.listCountriesByGroup = new ArrayList<ArrayList<Country>>();
		for (int i = 1; i < 9; i++) {
			GlobalValue.listCountriesByGroup.add(DatabaseUtility
					.getlistCountryByIdGroup(this, i));
		}

		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		FragmentPagerAdapter pagerAdapter = new LessonFragmentAdapter(
				getSupportFragmentManager());
		page.setAdapter(pagerAdapter);
		indicator.setViewPager(page);
		layoutAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TopScoreActivity.class);
				startActivity(i);

			}
		});
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Reload schedule
				if (page.getCurrentItem() == 1) {
					scheduleFragment.callAPI();
				}
				// Reload treemap
				else if (page.getCurrentItem() == 3) {
					treeMapFragment.loadData();
				}
			}
		});

		convertDateByTimeZone();
		btnRefresh.setVisibility(View.GONE);
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0 || arg0 == 2)
					btnRefresh.setVisibility(View.GONE);
				else
					btnRefresh.setVisibility(View.VISIBLE);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private List<String> getDateFromDatabase() {
		List<String> list = new ArrayList<String>();
		List<Match> listMatch = DatabaseUtility.getAllMatchInfo(this);
		for (int i = 0; i < listMatch.size(); i++) {
			StringBuilder DateTime = new StringBuilder();
			DateTime = DateTime.append(listMatch.get(i).getMatchDate())
					.append(",").append(listMatch.get(i).getMatchStartTime());
			list.add(DateTime.toString());
		}
		return list;

	}

	private void convertDateByTimeZone() {
		GlobalValue.allDay = new ArrayList<String>();
		GlobalValue.allDayofWeek = new ArrayList<String>();
		GlobalValue.allMonth = new ArrayList<String>();
		GlobalValue.allStartTimeMatch = new ArrayList<String>();
		List<String> listTime = getDateFromDatabase();
		SimpleDateFormat sourceFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm");
		sourceFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
		SimpleDateFormat formatDate = new SimpleDateFormat("EEEE,MMM,dd,HH:mm");
		for (int i = 0; i < listTime.size(); i++) {
			Date abc;
			try {
				abc = sourceFormat.parse(listTime.get(i));
				String a = formatDate.format(abc);
				String[] b = a.split(",");
				GlobalValue.allDayofWeek.add(b[0]);
				GlobalValue.allMonth.add(b[1]);
				GlobalValue.allDay.add(b[2] + "");
				GlobalValue.allStartTimeMatch.add(b[3]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}

	private void initAdModLayout() {
		adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId(GlobalValue.ADMOB_ID);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutAds);
		if (layout != null) {

			layout.addView(adView);
			AdRequest adRequest = new AdRequest.Builder().build();
			adView.loadAd(adRequest);
		}
	}

	@Override
	public void onBackPressed() {
		showQuitDialog();
	}

	private void showQuitDialog() {
		AlertDialog.Builder a = new AlertDialog.Builder(getApplicationContext())
				.setTitle(R.string.title_check_quit_app)
				.setMessage(R.string.check_quit_app)
				.setPositiveButton(R.string.button_ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								finish();
								GlobalValue.listAllMath = null;
							}
						})
				.setNegativeButton(R.string.button_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								arg0.dismiss();
							}
						});
		AlertDialog alert = a.create();
		alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alert.show();
	}

	class LessonFragmentAdapter extends FragmentPagerAdapter {

		public LessonFragmentAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return homeFragment;

			case 1:
				return scheduleFragment;

			case 2:
				return groupFragment;
			case 3:
				return treeMapFragment;

			default:
				return homeFragment;
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return listTab.get(position);
		}

		@Override
		public int getCount() {
			return listTab.size();
		}
	}
}
