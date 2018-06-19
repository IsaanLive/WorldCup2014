package com.sentosatech.worldcup2014de.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.utility.StringUtility;

public class ScheduleAdapter extends BaseAdapter {
	private ArrayList<Match> listMatch;
	public Context context;
	private LayoutInflater inflater = null;
	public String TAG = "Tab1";

	public ScheduleAdapter(Activity activity, ArrayList<Match> d) {
		listMatch = d;
		context = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return listMatch.size();
	}

	@Override
	public Object getItem(int position) {
		return listMatch.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_page_schedule, null);
			holder.lblTime = (TextView) convertView.findViewById(R.id.lbltime);
			holder.lblMatch = (TextView) convertView
					.findViewById(R.id.lblmatch);
			holder.lblDate = (TextView) convertView.findViewById(R.id.lblDate);
			
			holder.lblName1 = (TextView) convertView
					.findViewById(R.id.lblname1);
			holder.lblName2 = (TextView) convertView
					.findViewById(R.id.lblname2);
			holder.lblGoal1 = (TextView) convertView
					.findViewById(R.id.lblgoal1);
			holder.lblGoal2 = (TextView) convertView
					.findViewById(R.id.lblgoal2);
			holder.lblSvd = (TextView) convertView.findViewById(R.id.lblsvd);
			holder.imgCountry1 = (ImageView) convertView
					.findViewById(R.id.imgcountry1);
			holder.imgCountry2 = (ImageView) convertView
					.findViewById(R.id.imgcountry2);
			convertView.setTag(holder);
		}

		Match o = listMatch.get(position);
		if (o != null) {

			holder = (ViewHolder) convertView.getTag();
			holder.imgCountry1.setImageResource(o.getImageId1());
			holder.imgCountry2.setImageResource(o.getImageId2());
			holder.lblDate.setText(StringUtility.formatDate("yyyy-MM-dd HH:mm",
					o.getMatchDate(), "EEE, MMM dd"));
			holder.lblTime.setText(StringUtility.formatDate("yyyy-MM-dd HH:mm",
					o.getMatchDate(), "HH:mm"));
			holder.lblName1.setText(o.getFullNameTeam1());
			holder.lblName2.setText(o.getFullNameTeam2());
			holder.lblMatch.setText(" " + o.getMatchId());
			holder.lblSvd.setText(o.getStadiumName());
			holder.lblName2.setSelected(true);
			holder.lblName1.setSelected(true);
			// If match not take place
			if (!StringUtility.isDateSoonerNow("yyyy-MM-dd HH:mm",
					o.getMatchDate())) {
				holder.lblGoal1.setText(o.getScore1());
				holder.lblGoal2.setText(o.getScore2());

			} else {
				holder.lblGoal1.setText("?");
				holder.lblGoal2.setText("?");
			}
		} else {
			Log.i(TAG, "Null Object");
		}

		return convertView;
	}

	static class ViewHolder {
		private ImageView imgCountry1, imgCountry2;
		private TextView lblDate, lblTime, lblMatch, lblName1, lblName2,
				lblGoal1, lblGoal2, lblSvd;
	}
}
