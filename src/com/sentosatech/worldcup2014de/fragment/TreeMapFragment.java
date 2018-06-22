/*
 * Name: TreeMapFragment.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sentosatech.worldcup2014de.BaseFragment;
import com.sentosatech.worldcup2014de.R;
import com.sentosatech.worldcup2014de.activity.config.JsonConfig;
import com.sentosatech.worldcup2014de.activity.config.WebServiceConfig;
import com.sentosatech.worldcup2014de.modelmanager.ModelManager;
import com.sentosatech.worldcup2014de.modelmanager.ModelManagerListener;
import com.sentosatech.worldcup2014de.modelmanager.ParserUitility;
import com.sentosatech.worldcup2014de.object.Country;
import com.sentosatech.worldcup2014de.object.Match;
import com.sentosatech.worldcup2014de.utility.AssetUtil;
import com.sentosatech.worldcup2014de.utility.DebugLog;
import com.sentosatech.worldcup2014de.utility.NetworkUtility;

public class TreeMapFragment extends BaseFragment {

	private ImageView a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2, g1, g2,
			h1, h2, w49, w50, w51, w52, w53, w54, w55, w56, w57, w58, w59, w60,
			w61, w62, w63, w64, l61, l62;
	private TextView lbla1, lbla2, lblb1, lblb2, lblc1, lblc2, lbld1, lbld2,
			lble1, lble2, lblf1, lblf2, lblg1, lblg2, lblh1, lblh2, lblw49,
			lblw50, lblw51, lblw52, lblw53, lblw54, lblw55, lblw56, lblw57,
			lblw58, lblw59, lblw60, lblw61, lblw62, lblw63, lblw64, lbll61,
			lbll62;

	private ArrayList<Match> listMatch14, listMatch18, listMatch116,
			listMatchtp, listMatchfn;

	private Match match;
	// private ConvertBitmap convert = new ConvertBitmap();
	private Country country, country1, country2, country3, country4, country5,
			country6, country7, country8, country9, country10, country11,
			country12, country13, country14, country15, country16;
	private Country win49, win50, win51, win52, win53, win54, win55, win56,
			win57, win58, win59, win60, win61, win62, win63, win64, lose61,
			lose62;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_treemap, container, false);
		initUI(view);
		loadData();
		// MainActivity.timeManager.schedule(new TimerTask() {
		// @Override
		// public void run() {
		// loadData();
		// }
		// }, GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 1000 * 60,
		// GlobalValue.TIME_CHECK_UPDATE_INTERVAL * 60 * 1000);
		return view;

	}

	public void loadData() {
		try {
			if (NetworkUtility.getInstance(self).checkNetworkStatus()) {

				ModelManager.getData(self, true,
						WebServiceConfig.URL_GET_TREE_MAP,
						new ModelManagerListener() {

							@Override
							public void onWSError() {
								// TODO Auto-generated method stub

							}

							@Override
							public void OnSuccess(String json) {
								DebugLog.e("abc", json);
								listMatch116 = ParserUitility.parserListMatch(json,
										"116");

								listMatch14 = ParserUitility.parserListMatch(json,
										"14");
								listMatch18 = ParserUitility.parserListMatch(json,
										"18");
								listMatchfn = ParserUitility.parserListMatch(json,
										"fn");
								listMatchtp = ParserUitility.parserListMatch(json,
										"tp");
								bindData();

							}

						});
			} else {
				String json = AssetUtil.getString(self,
						JsonConfig.FILE_TREE_MAP);
				listMatch116 = ParserUitility.parserListMatch(json, "116");
				listMatch14 = ParserUitility.parserListMatch(json, "14");
				listMatch18 = ParserUitility.parserListMatch(json, "18");
				listMatchfn = ParserUitility.parserListMatch(json, "fn");
				listMatchtp = ParserUitility.parserListMatch(json, "tp");
				bindData();
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
	}

	private void bindData() {
		if (listMatchfn != null && listMatchfn.size() > 0)

		{
			Match finalMatch = listMatchfn.get(0);
			w61.setImageResource(finalMatch.getImageId1());
			w62.setImageResource(finalMatch.getImageId2());
			lblw61.setText(finalMatch.getFullNameTeam1());
			lblw62.setText(finalMatch.getFullNameTeam2());
			w64.setImageResource(finalMatch.getImageWin());
			lblw64.setText(finalMatch.getWinTeam());

		}
		if (listMatchtp != null && listMatchtp.size() > 0) {
			Match matchTp = listMatchtp.get(0);
			l61.setImageResource(matchTp.getImageId1());
			l62.setImageResource(matchTp.getImageId2());
			lbll61.setText(matchTp.getFullNameTeam1());
			lbll62.setText(matchTp.getFullNameTeam2());

			w63.setImageResource(matchTp.getImageWin());
			lblw63.setText(matchTp.getWinTeam());

		}
		if (listMatch14 != null && listMatch14.size() > 0) {

			if (listMatch14.size() > 0) {
				Match match14_1 = listMatch14.get(0);
				w58.setImageResource(match14_1.getImageId1());
				w57.setImageResource(match14_1.getImageId2());
				lblw58.setText(match14_1.getFullNameTeam1());
				lblw57.setText(match14_1.getFullNameTeam2());
			}
			if (listMatch14.size() > 1) {

				Match match14_2 = listMatch14.get(1);
				w60.setImageResource(match14_2.getImageId1());
				w59.setImageResource(match14_2.getImageId2());
				lblw60.setText(match14_2.getFullNameTeam1());
				lblw59.setText(match14_2.getFullNameTeam2());
			}
		}

		if (listMatch18 != null && listMatch18.size() > 0) {

			DebugLog.e("tree map", listMatch18.size() + "");

			if (listMatch18.size() > 0) {
				Match match18_1 = listMatch18.get(0);
				w53.setImageResource(match18_1.getImageId1());
				w54.setImageResource(match18_1.getImageId2());
				lblw53.setText(match18_1.getFullNameTeam1());
				lblw54.setText(match18_1.getFullNameTeam2());

			}
			if (listMatch18.size() > 1) {

				Match match18_2 = listMatch18.get(1);
				w49.setImageResource(match18_2.getImageId1());
				w50.setImageResource(match18_2.getImageId2());
				lblw49.setText(match18_2.getFullNameTeam1());
				lblw50.setText(match18_2.getFullNameTeam2());

			}
			if (listMatch18.size() > 2) {
				Match match18_3 = listMatch18.get(2);
				w55.setImageResource(match18_3.getImageId1());
				w56.setImageResource(match18_3.getImageId2());
				lblw55.setText(match18_3.getFullNameTeam1());
				lblw56.setText(match18_3.getFullNameTeam2());

			}
			if (listMatch18.size() > 3) {

				Match match18_4 = listMatch18.get(3);
				w51.setImageResource(match18_4.getImageId1());
				w52.setImageResource(match18_4.getImageId2());
				lblw51.setText(match18_4.getFullNameTeam1());
				lblw52.setText(match18_4.getFullNameTeam2());
			}

		}
		if (listMatch116 != null && listMatch116.size() > 0) {

			if (listMatch116.size() > 0) {
				Match match16_1 = listMatch116.get(0);
				a1.setImageResource(match16_1.getImageId1());
				b2.setImageResource(match16_1.getImageId2());
				lbla1.setText(match16_1.getFullNameTeam1());
				lblb2.setText(match16_1.getFullNameTeam2());
			}
			if (listMatch116.size() > 1) {

				Match match116_2 = listMatch116.get(1);
				c1.setImageResource(match116_2.getImageId1());
				d2.setImageResource(match116_2.getImageId2());
				lblc1.setText(match116_2.getFullNameTeam1());
				lbld2.setText(match116_2.getFullNameTeam2());
			}

			if (listMatch116.size() > 2) {
				Match match16_5 = listMatch116.get(2);
				b1.setImageResource(match16_5.getImageId1());
				a2.setImageResource(match16_5.getImageId2());
				lblb1.setText(match16_5.getFullNameTeam1());
				lbla2.setText(match16_5.getFullNameTeam2());
			}

			if (listMatch116.size() > 3) {

				Match match116_6 = listMatch116.get(3);
				d1.setImageResource(match116_6.getImageId1());
				c2.setImageResource(match116_6.getImageId2());
				lbld1.setText(match116_6.getFullNameTeam1());
				lblc2.setText(match116_6.getFullNameTeam2());
			}

			if (listMatch116.size() > 4) {
				Match match116_3 = listMatch116.get(4);
				e1.setImageResource(match116_3.getImageId1());
				f2.setImageResource(match116_3.getImageId2());
				lble1.setText(match116_3.getFullNameTeam1());
				lblf2.setText(match116_3.getFullNameTeam2());
			}

			if (listMatch116.size() > 5) {

				Match match116_4 = listMatch116.get(5);
				g1.setImageResource(match116_4.getImageId1());
				h2.setImageResource(match116_4.getImageId2());
				lblg1.setText(match116_4.getFullNameTeam1());
				lblh2.setText(match116_4.getFullNameTeam2());
			}

			if (listMatch116.size() > 6) {
				Match match116_7 = listMatch116.get(6);
				f1.setImageResource(match116_7.getImageId1());
				e2.setImageResource(match116_7.getImageId2());
				lblf1.setText(match116_7.getFullNameTeam1());
				lble2.setText(match116_7.getFullNameTeam2());
			}
			if (listMatch116.size() > 7) {

				Match match116_8 = listMatch116.get(7);
				h1.setImageResource(match116_8.getImageId1());
				g2.setImageResource(match116_8.getImageId2());
				lblh1.setText(match116_8.getFullNameTeam1());
				lblg2.setText(match116_8.getFullNameTeam2());
			}

		}

	}

	private void initUI(View view) {
		a1 = (ImageView) view.findViewById(R.id.a1);
		a2 = (ImageView) view.findViewById(R.id.a2);
		b1 = (ImageView) view.findViewById(R.id.b1);
		b2 = (ImageView) view.findViewById(R.id.b2);
		c1 = (ImageView) view.findViewById(R.id.c1);
		c2 = (ImageView) view.findViewById(R.id.c2);
		d1 = (ImageView) view.findViewById(R.id.d1);
		d2 = (ImageView) view.findViewById(R.id.d2);
		e1 = (ImageView) view.findViewById(R.id.e1);
		e2 = (ImageView) view.findViewById(R.id.e2);
		f1 = (ImageView) view.findViewById(R.id.f1);
		f2 = (ImageView) view.findViewById(R.id.f2);
		g1 = (ImageView) view.findViewById(R.id.g1);
		g2 = (ImageView) view.findViewById(R.id.g2);
		h1 = (ImageView) view.findViewById(R.id.h1);
		h2 = (ImageView) view.findViewById(R.id.h2);
		w49 = (ImageView) view.findViewById(R.id.w49);
		w50 = (ImageView) view.findViewById(R.id.w50);
		w51 = (ImageView) view.findViewById(R.id.w51);
		w52 = (ImageView) view.findViewById(R.id.w52);
		w53 = (ImageView) view.findViewById(R.id.w53);
		w54 = (ImageView) view.findViewById(R.id.w54);
		w55 = (ImageView) view.findViewById(R.id.w55);
		w56 = (ImageView) view.findViewById(R.id.w56);
		w57 = (ImageView) view.findViewById(R.id.w57);
		w58 = (ImageView) view.findViewById(R.id.w58);
		w59 = (ImageView) view.findViewById(R.id.w59);
		w60 = (ImageView) view.findViewById(R.id.w60);
		w61 = (ImageView) view.findViewById(R.id.w61);
		w62 = (ImageView) view.findViewById(R.id.w62);
		w63 = (ImageView) view.findViewById(R.id.w63);
		w64 = (ImageView) view.findViewById(R.id.w64);
		l61 = (ImageView) view.findViewById(R.id.l61);
		l62 = (ImageView) view.findViewById(R.id.l62);

		lbla1 = (TextView) view.findViewById(R.id.lbla1);
		lbla2 = (TextView) view.findViewById(R.id.lbla2);
		lblb1 = (TextView) view.findViewById(R.id.lblb1);
		lblb2 = (TextView) view.findViewById(R.id.lblb2);
		lblc1 = (TextView) view.findViewById(R.id.lblc1);
		lblc2 = (TextView) view.findViewById(R.id.lblc2);
		lbld1 = (TextView) view.findViewById(R.id.lbld1);
		lbld2 = (TextView) view.findViewById(R.id.lbld2);
		lble1 = (TextView) view.findViewById(R.id.lble1);
		lble2 = (TextView) view.findViewById(R.id.lble2);
		lblf1 = (TextView) view.findViewById(R.id.lblf1);
		lblf2 = (TextView) view.findViewById(R.id.lblf2);
		lblg1 = (TextView) view.findViewById(R.id.lblg1);
		lblg2 = (TextView) view.findViewById(R.id.lblg2);
		lblh1 = (TextView) view.findViewById(R.id.lblh1);
		lblh2 = (TextView) view.findViewById(R.id.lblh2);
		lblw49 = (TextView) view.findViewById(R.id.lblw49);
		lblw50 = (TextView) view.findViewById(R.id.lblw50);
		lblw51 = (TextView) view.findViewById(R.id.lblw51);
		lblw52 = (TextView) view.findViewById(R.id.lblw52);
		lblw53 = (TextView) view.findViewById(R.id.lblw53);
		lblw54 = (TextView) view.findViewById(R.id.lblw54);
		lblw55 = (TextView) view.findViewById(R.id.lblw55);
		lblw56 = (TextView) view.findViewById(R.id.lblw56);
		lblw57 = (TextView) view.findViewById(R.id.lblw57);
		lblw58 = (TextView) view.findViewById(R.id.lblw58);
		lblw59 = (TextView) view.findViewById(R.id.lblw59);
		lblw60 = (TextView) view.findViewById(R.id.lblw60);
		lblw61 = (TextView) view.findViewById(R.id.lblw61);
		lblw62 = (TextView) view.findViewById(R.id.lblw62);
		lblw63 = (TextView) view.findViewById(R.id.lblw63);
		lblw64 = (TextView) view.findViewById(R.id.lblw64);
		lbll61 = (TextView) view.findViewById(R.id.lbll61);
		lbll62 = (TextView) view.findViewById(R.id.lbll62);

	}

}
