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
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.utility.lazylist.ImageLoader;

public class DetailGroupsPointAdapter extends BaseAdapter {

	private ArrayList<Country> listCountry;
	public ImageLoader imageLoader;
	public Context context;
	private static LayoutInflater inflater = null;
	public String TAG = "DetailGroupsPoint";

	public DetailGroupsPointAdapter(Activity activity, ArrayList<Country> d) {
		listCountry = d;
		context = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return listCountry.size();
	}

	@Override
	public Object getItem(int position) {
		return listCountry.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.row_page_detail_group_point, null);
			holder = new ViewHolder();
			holder.lblNameCountry = (TextView) convertView
					.findViewById(R.id.lblNameCountry);
			holder.lblPts = (TextView) convertView.findViewById(R.id.lblPts);
			holder.lblMP = (TextView) convertView.findViewById(R.id.lblMP);
			holder.lblW = (TextView) convertView.findViewById(R.id.lblW);
			holder.lblD = (TextView) convertView.findViewById(R.id.lblD);
			holder.lblL = (TextView) convertView.findViewById(R.id.lblL);
			holder.lblHs = (TextView) convertView.findViewById(R.id.lblHs);
			holder.imgCountry = (ImageView) convertView
					.findViewById(R.id.imgCountry);
			convertView.setTag(holder);
		}
		Country h = listCountry.get(position);
		if (h != null) {
			
			holder = (ViewHolder) convertView.getTag();
			holder.imgCountry.setImageResource(h.getCountryImage());
			holder.lblNameCountry.setText(h.getCountryName());
			holder.lblNameCountry.setSelected(true);
			holder.lblPts.setText(h.getPoint() + "");
			holder.lblMP.setText(h.getMp() + "");
			holder.lblW.setText(h.getW() + "");
			holder.lblD.setText(h.getD() + "");
			holder.lblL.setText(h.getL() + "");
			String hs = String.valueOf(h.getAllGoalNumber()
					- h.getAllLoseNumber());
			holder.lblHs.setText(hs + "");

		}
		return convertView;
	}

	static class ViewHolder {
		private ImageView imgCountry;
		private TextView lblNameCountry, lblMP, lblW, lblPts, lblD, lblL,
				lblHs;
	}

}
