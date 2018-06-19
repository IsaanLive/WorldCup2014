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
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.utility.lazylist.ImageLoader;

public class DetailGroupsInfoAdapter extends BaseAdapter {

	private ArrayList<Country> listCountry;
	public ImageLoader imageLoader;
	public Context context;
	private static LayoutInflater inflater = null;
	public String TAG = "DetailGroupsInfo";

	public DetailGroupsInfoAdapter(Activity activity, ArrayList<Country> d) {
		listCountry = d;
		context = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());

	}

	@Override
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.row_page_detail_group_info,
					null);
			holder = new ViewHolder();
			holder.lblNameCountryInfo = (TextView) convertView
					.findViewById(R.id.lblNameCountryInfo);
			holder.imgCountryInfo = (ImageView) convertView
					.findViewById(R.id.imgCountryInfo);
			convertView.setTag(holder);
		}
		Country country = listCountry.get(position);
		if (country != null) {
			holder = (ViewHolder) convertView.getTag();
			holder.imgCountryInfo.setImageResource(GlobalValue.listFlags
					.get(country.getCountryId()));
			holder.lblNameCountryInfo.setText(country.getCountryName());
			holder.lblNameCountryInfo.setSelected(true);

		}

		return convertView;
	}

	static class ViewHolder {
		private ImageView imgCountryInfo;
		private TextView lblNameCountryInfo;

	}

}
