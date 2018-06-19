package com.sentosatech.worldcup2014de.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class HomeFragment extends BaseFragment {

	private TextView lbltimerDay, lbltimerHour, lbltimerMin, lbltimerSec;
	long milliseconds;
	long endTime;
	private LinearLayout layoutHomeScreen1, layoutHomeScreen2;
	private Timer timer;

	@SuppressLint("SimpleDateFormat")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_home, container, false);
		timer = new Timer();
		layoutHomeScreen1 = (LinearLayout) view
				.findViewById(R.id.layoutHomeScreen1);
		layoutHomeScreen2 = (LinearLayout) view
				.findViewById(R.id.layoutHomeScreen2);
		layoutHomeScreen1.setVisibility(View.GONE);
		layoutHomeScreen2.setVisibility(View.VISIBLE);
		lbltimerDay = (TextView) view.findViewById(R.id.lbltimerDay);
		lbltimerHour = (TextView) view.findViewById(R.id.lbltimerHours);
		lbltimerMin = (TextView) view.findViewById(R.id.lbltimerMin);
		lbltimerSec = (TextView) view.findViewById(R.id.lbltimerSec);
		Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
				"DS-DIGI.TTF");
		lbltimerDay.setTypeface(face);
		lbltimerHour.setTypeface(face);
		lbltimerMin.setTypeface(face);
		lbltimerSec.setTypeface(face);
		countDownTime();
		return view;
	}

	public void countDownTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy,HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));

		Date braziltime;
		try {
			braziltime = formatter.parse(GlobalValue.timeCountDown);
			milliseconds = braziltime.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (((milliseconds - System.currentTimeMillis()) / 1000) > 0) {
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					if (getActivity() != null)
						getActivity().runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Long serverUptimeSeconds = ((milliseconds - System
										.currentTimeMillis()) / 1000);
								if (serverUptimeSeconds <= 0) {
									layoutHomeScreen1
											.setVisibility(View.VISIBLE);
									layoutHomeScreen2.setVisibility(View.GONE);
									lbltimerDay.setText("00");
									lbltimerHour.setText("00");
									lbltimerMin.setText("00");
									lbltimerSec.setText("00");
									timer.cancel();
									timer.purge();

								} else {
									String serverUptimeDay = String.format(
											"%02d ",
											serverUptimeSeconds / 86400);
									String serverUptimeHour = String
											.format("%02d ",
													(serverUptimeSeconds % 86400) / 3600);
									String serverUptimeMin = String
											.format("%02d ",
													((serverUptimeSeconds % 86400) % 3600) / 60);
									String serverUptimeSec = String
											.format("%02d",
													((serverUptimeSeconds % 86400) % 3600) % 60);
									lbltimerDay.setText(serverUptimeDay);
									lbltimerHour.setText(serverUptimeHour);
									lbltimerMin.setText(serverUptimeMin);
									lbltimerSec.setText(serverUptimeSec);
								}

							}
						});
				}
			}, 0, 1000);

		} else {
			layoutHomeScreen1.setVisibility(View.VISIBLE);
			layoutHomeScreen2.setVisibility(View.GONE);
		}
	}
}
