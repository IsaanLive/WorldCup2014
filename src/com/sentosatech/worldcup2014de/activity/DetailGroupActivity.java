package com.sentosatech.worldcup2014de.activity;

import java.util.ArrayList; 
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.fragment.DetailGroupsInfo;
import com.sentosatech.worldcup2014de.fragment.DetailGroupsPoint;
import com.sentosatech.worldcup2014de.fragment.DetailGroupsSchedule;
import com.sentosatech.worldcup2014de.indicator.TabPageIndicator;

public class DetailGroupActivity extends BaseActivity {
	private Button btnback, btnRefresh;
	private TabPageIndicator indicator;
	private TextView lblNameGroup;
	private ViewPager page;
	private List<String> listTab;
	private DetailGroupsPoint pointFragment;
	private DetailGroupsSchedule scheduleFragment;
	private DetailGroupsInfo infoFragment;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("DetailGroup", "Created activity");
		setContentView(R.layout.detailgroup);
		btnback = (Button) findViewById(R.id.btnback);
		btnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}

		});
		String Name;
		Bundle b1 = getIntent().getExtras(); // get data
		GlobalValue.Name = Name = b1.getString("NameGroup");

		lblNameGroup = (TextView) findViewById(R.id.lblNameGroup);
		lblNameGroup.setText(getString(R.string.group_) + " " + Name);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (self != null) {
					pointFragment.loadData();
					scheduleFragment.initData();
				}
			}
		});
		page = (ViewPager) findViewById(R.id.viewPager);
		listTab = new ArrayList<String>();
		listTab.add(getString(R.string.point));
		listTab.add(getString(R.string.schedule));
		listTab.add(getString(R.string.info));
		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		loadData();
		
//		MainActivity.timeManager.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				if (self != null) {
//					pointFragment.loadData();
//					scheduleFragment.initData();
//				}
//			}
//		}, GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 1000 * 60,
//				GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 60 * 1000);

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		self=null;
	}

	private void loadData() {
		pointFragment = new DetailGroupsPoint();
		scheduleFragment = new DetailGroupsSchedule();
		infoFragment = new DetailGroupsInfo();
		FragmentPagerAdapter pagerAdapter = new LessonFragmentAdapter(
				getSupportFragmentManager());
		page.setAdapter(pagerAdapter);
		indicator.setViewPager(page);

	}

	class LessonFragmentAdapter extends FragmentPagerAdapter {

		public LessonFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return pointFragment;

			case 1:
				return scheduleFragment;

			case 2:
				return infoFragment;

			default:
				return pointFragment;
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
