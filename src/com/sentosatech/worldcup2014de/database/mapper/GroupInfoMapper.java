package com.sentosatech.worldcup2014de.database.mapper;

import android.database.Cursor;

import com.sentosatech.worldcup2014de.database.DBKeyConfig;
import com.sentosatech.worldcup2014de.object.Group;

public class GroupInfoMapper implements RowMapper<Group>{

	@Override
	public Group mapRow(Cursor row, int rowNum) {
		
		Group groupInfo = new Group();
		groupInfo.setGroupId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_GROUP_ID));
		groupInfo.setGroupName(CursorParseUtility.getString(row, DBKeyConfig.KEY_GROUP_NAME));
		return groupInfo;
	}

}
