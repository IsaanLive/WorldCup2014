package com.sentosatech.worldcup2014de.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.Group;
import com.sentosatech.worldcup2014de.utility.lazylist.ImageLoader;

public class GroupsAdapter extends BaseAdapter {

	private List<Group> listGroup;
	public ImageLoader imageLoader;
	public Activity context;
	private static LayoutInflater inflater = null;
	public String TAG = "Groups";
	private AQuery aq;

	public GroupsAdapter(Activity activity, ArrayList<Group> d) {
		listGroup = d;
		context = activity;
		aq = new AQuery(context);
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return listGroup.size();
	}

	@Override
	public Object getItem(int position) {
		return listGroup.get(position);
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
			convertView = inflater.inflate(R.layout.row_page_groups, null);
			holder.imgCountry1 = (ImageView) convertView
					.findViewById(R.id.imgCountry1);
			holder.imgCountry2 = (ImageView) convertView
					.findViewById(R.id.imgCountry2);
			holder.imgCountry3 = (ImageView) convertView
					.findViewById(R.id.imgCountry3);
			holder.imgCountry4 = (ImageView) convertView
					.findViewById(R.id.imgCountry4);
			holder.lblName = (TextView) convertView.findViewById(R.id.lblname);
			holder.lblName1 = (TextView) convertView
					.findViewById(R.id.lblName1);
			holder.lblName2 = (TextView) convertView
					.findViewById(R.id.lblName2);
			holder.lblName3 = (TextView) convertView
					.findViewById(R.id.lblName3);
			holder.lblName4 = (TextView) convertView
					.findViewById(R.id.lblName4);
			holder.lblPoint1 = (TextView) convertView
					.findViewById(R.id.lblPoint1);
			holder.lblPoint2 = (TextView) convertView
					.findViewById(R.id.lblPoint2);
			holder.lblPoint3 = (TextView) convertView
					.findViewById(R.id.lblPoint3);
			holder.lblPoint4 = (TextView) convertView
					.findViewById(R.id.lblPoint4);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Group h = listGroup.get(position);
		if (h != null) {
			holder = (ViewHolder) convertView.getTag();
			Country country1, country2, country3, country4;
			List<Country> list = GlobalValue.listCountriesByGroup.get(h.getGroupId() - 1);
			country1 = list.get(0);
			country2 = list.get(1);
			country3 = list.get(2);
			country4 = list.get(3);
			 aq.id(holder.imgCountry1).image(GlobalValue.listFlags.get(country1.getCountryId()));
			 aq.id(holder.imgCountry2).image(GlobalValue.listFlags.get(country2.getCountryId()));
			 aq.id(holder.imgCountry3).image(GlobalValue.listFlags.get(country3.getCountryId()));
			 aq.id(holder.imgCountry4).image(GlobalValue.listFlags.get(country4.getCountryId()));
//			holder.imgCountry1.setImageResource(GlobalValue.listFlags
//					.get(country1.getCountryId()));
//			holder.imgCountry2.setImageResource(GlobalValue.listFlags
//					.get(country2.getCountryId()));
//			holder.imgCountry3.setImageResource(GlobalValue.listFlags
//					.get(country3.getCountryId()));
//			holder.imgCountry4.setImageResource(GlobalValue.listFlags
//					.get(country4.getCountryId()));

			// -------- SET POINT AND UPDATE IN COUNTRY--------------------

			holder.lblPoint1.setText("" + country1.getPoint());
			holder.lblPoint2.setText("" + country2.getPoint());
			holder.lblPoint3.setText("" + country3.getPoint());
			holder.lblPoint4.setText("" + country4.getPoint());
			
			
			//Hide point textview because can't get point from API for now
			
			holder.lblPoint1.setVisibility(View.GONE);
			holder.lblPoint2.setVisibility(View.GONE);
			holder.lblPoint3.setVisibility(View.GONE);
			holder.lblPoint4.setVisibility(View.GONE);
			
			
			// ----------------SET POINT AND UPDATE IN COUNTRY-----------------
			// ArrayList<Match_CountryInfo> listMatch3 = DatabaseUtility
			// .getlistMatchCountryByIdCountry(context,
			// country3.getCountryId());
			// for (int j = 0; j < listMatch3.size(); j++) {
			// if (listMatch3.get(j).getGoalNumber() > listMatch3.get(j)
			// .getLoseNumber()) {
			//
			// c = c + 3;
			//
			// } else if (listMatch3.get(j).getGoalNumber() == listMatch3.get(j)
			// .getLoseNumber()) {
			// c = c + 1;
			// } else {
			// c = c + 0;
			// }
			//
			// }
			// DatabaseUtility.updatePoint(context, c, country3.getCountryId());

			// -------------------------------------
			holder.lblName.setText(h.getGroupName());
			holder.lblName1.setText(country1.getCountryKey());
			holder.lblName2.setText(country2.getCountryKey());
			holder.lblName3.setText(country3.getCountryKey());
			holder.lblName4.setText(country4.getCountryKey());

		} else {
			Log.i(TAG, "Null Object");
		}

		return convertView;
	}

	static class ViewHolder {
		private ImageView imgCountry1, imgCountry2, imgCountry3, imgCountry4;
		private TextView lblName, lblName1, lblName2, lblName3, lblName4,
				lblPoint1, lblPoint2, lblPoint3, lblPoint4;
	}

}
