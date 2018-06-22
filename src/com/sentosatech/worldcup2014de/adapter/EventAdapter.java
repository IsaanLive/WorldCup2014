/*
 * Name: EventAdapter.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.object.EventInfo;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.utility.lazylist.ImageLoader;

public class EventAdapter extends BaseAdapter {

	private ArrayList<EventInfo> listEvent;
	public ImageLoader imageLoader;
	public Context context;
	private static LayoutInflater inflater = null;
	public String TAG = "DetailGroupsInfo";

	private Match match;

	public EventAdapter(Activity activity, ArrayList<EventInfo> d, Match match) {
		listEvent = d;
		this.match = match;
		context = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}
	@Override
	public int getCount() {
		return listEvent.size();
	}

	@Override
	public Object getItem(int position) {
		return listEvent.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.row_event, null);
			holder = new ViewHolder();
			holder.imgInfo1 = (ImageView) convertView
					.findViewById(R.id.imgInfo1);
			holder.imgInfo2 = (ImageView) convertView
					.findViewById(R.id.imgInfo2);

			holder.lblInfo1 = (TextView) convertView
					.findViewById(R.id.lblInfo1);
			holder.lblInfo2 = (TextView) convertView
					.findViewById(R.id.lblInfo2);
			holder.lblScore = (TextView) convertView
					.findViewById(R.id.lblScore);
			holder.lblTime = (TextView) convertView.findViewById(R.id.lblTime);

			convertView.setTag(holder);
		}
		EventInfo item = listEvent.get(position);
		if (item != null) {
			holder = (ViewHolder) convertView.getTag();
			holder.lblScore.setText(item.getEventScore());
			holder.lblTime.setText(item.getEventTime() + "'");
			// Event for first team
			if (item.getTeamName().equalsIgnoreCase(match.getCodeTeam1())) {
				
				holder.imgInfo2.setVisibility(View.GONE);
				holder.lblInfo2.setVisibility(View.GONE);
				holder.imgInfo1.setVisibility(View.VISIBLE);
				holder.lblInfo1.setVisibility(View.VISIBLE);

				holder.lblInfo1.setText(item.getEventPlayer());
				holder.imgInfo1.setImageResource(getImageByType(item
						.getEventType()));
			} else {

				holder.imgInfo1.setVisibility(View.GONE);
				holder.lblInfo1.setVisibility(View.GONE);
				holder.imgInfo2.setVisibility(View.VISIBLE);
				holder.lblInfo2.setVisibility(View.VISIBLE);

				holder.lblInfo2.setText(item.getEventPlayer());
				holder.imgInfo2.setImageResource(getImageByType(item
						.getEventType()));

			}

		}

		return convertView;
	}

	static class ViewHolder {

		private ImageView imgInfo1, imgInfo2;
		private TextView lblScore, lblInfo1, lblInfo2, lblTime;

	}

	private int getImageByType(String eventType) {

		if (eventType.equalsIgnoreCase("goal"))
			return R.drawable.icon_goal;
		else if (eventType.equalsIgnoreCase("Red card")) {
			return R.drawable.icon_red_card;
		} else if (eventType.equalsIgnoreCase("Yellow card")) {
			return R.drawable.icon_yellow_card;
		} else if (eventType.equalsIgnoreCase("Penalty"))
			return R.drawable.icon_goal;
		else
			return R.drawable.icon_goal;

	}

}
