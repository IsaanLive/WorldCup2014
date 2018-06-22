/*
 * Name: NoScrollListView.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListView extends ListView {

	public NoScrollListView(Context context) {
		super(context);
	}

	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec,
				MeasureSpec.makeMeasureSpec(MeasureSpec.UNSPECIFIED, 0));

		// here I assume that height's being calculated for one-child only, seen
		// it in ListView's source which is actually a bad idea
		int childHeight = getDividerHeight()
				+ getMeasuredHeight()
				- (getListPaddingTop() + getListPaddingBottom() + getVerticalFadingEdgeLength() * 2);

		int fullHeight = getListPaddingTop() + getListPaddingBottom()
				+ childHeight * (getCount());

		setMeasuredDimension(getMeasuredWidth(), fullHeight);
	}
}
