package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.DetailGroupActivity;
import com.sentosatech.worldcup2014de.activity.config.GlobalValue;
import com.sentosatech.worldcup2014de.adapter.GroupsAdapter;
import com.sentosatech.worldcup2014de.database.DatabaseUtility;
import com.sentosatech.worldcup2014de.object.Group;

public class GroupsFragment extends BaseFragment {

	private GridView grvGroups;
	private ArrayList<Group> arrListGroup;
	private GroupsAdapter groupsAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_groups, container, false);
		initUI(view);
//		initData();
		initControl();
//		initAdModLayout(view);
		new loadData().execute();
		return view;

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void initControl() {
		
		grvGroups.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Bundle b1 = new Bundle();
				b1.putString("NameGroup", arrListGroup.get(arg2).getGroupName());
				GlobalValue.IdGroup =arg2+1;
				Intent inten = new Intent(getActivity(), DetailGroupActivity.class);
				inten.putExtras(b1);
				startActivity(inten);

			}
		});
	}
	
	private void initUI(View view) {
		grvGroups = (GridView) view.findViewById(R.id.grvGroups);

	}
	public class loadData extends AsyncTask<Void, Void, Integer> {

		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
		}
		@Override
		protected Integer doInBackground(Void... arg0) {
			arrListGroup = DatabaseUtility.getAllGroupInfo(getActivity());
			groupsAdapter = new GroupsAdapter(getActivity(), arrListGroup);
			
			return -1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			grvGroups.setAdapter(groupsAdapter);
		}

		
	}

}
