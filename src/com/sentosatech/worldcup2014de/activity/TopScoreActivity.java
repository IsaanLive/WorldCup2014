package com.sentosatech.worldcup2014de.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.JsonConfig;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.adapter.TopScoreAdapter;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.modelmanager.ParserUitility;
import com.sentosatech.worldcup2014de.object.TopScoreInfo;
import com.sentosatech.worldcup2014de.utility.AssetUtil;
import com.sentosatech.worldcup2014de.utility.NetworkUtility;

public class TopScoreActivity extends BaseActivity {

	private ArrayList<TopScoreInfo> listTopScore;
	private TopScoreAdapter topScoreAdapter;
	private ListView lsvTopSCore;
	private LinearLayout btnLinkMyweb;
	private Button btnRefresh;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.page_top_score);

		lsvTopSCore = (ListView) findViewById(R.id.lsvTopSccore);
		btnLinkMyweb = (LinearLayout) findViewById(R.id.btnLinkMyWeb);
		btnLinkMyweb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = getString(R.string.myWeb);
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		loadData();

		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				loadData();

			}
		});
	}

	private void loadData() {
		if (NetworkUtility.getInstance(self).checkNetworkStatus()) {
			ModelManager.getData(self, true,
					WebServiceConfig.URL_GET_TOP_SCORE,
					new ModelManagerListener() {

						@Override
						public void onWSError() {
							// TODO Auto-generated method stub
							findViewById(R.id.empty)
									.setVisibility(View.VISIBLE);
						}

						@Override
						public void OnSuccess(String json) {
							listTopScore = ParserUitility.parserListTopScore(json);
							bindData();

						}
					});
		} else {
			String json = AssetUtil.getString(self, JsonConfig.FILE_TREE_MAP);
			listTopScore = ParserUitility.parserListTopScore(json);
			bindData();
		}
	}

	private void bindData() {

		topScoreAdapter = new TopScoreAdapter(TopScoreActivity.this,
				listTopScore);
		lsvTopSCore.setEmptyView(findViewById(R.id.empty));
		lsvTopSCore.setAdapter(topScoreAdapter);

	}

}
