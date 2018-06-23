/*
 * Name: EventInfo.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.object;

public class EventInfo {

	private String eventTime;
	private String eventPlayer;
	private String teamName;
	private String eventScore;
	private String eventType;
	private String eventTeamId;
	private String scoreType;
	private int eventOrderTeam;

	public int getEventOrderTeam() {
		return eventOrderTeam;
	}

	public void setEventOrderTeam(int eventOrderTeam) {
		this.eventOrderTeam = eventOrderTeam;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventPlayer() {
		return eventPlayer;
	}

	public void setEventPlayer(String eventPlayer) {
		this.eventPlayer = eventPlayer;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getEventScore() {
		return eventScore;
	}

	public void setEventScore(String eventScore) {
		this.eventScore = eventScore;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTeamId() {
		return eventTeamId;
	}

	public void setEventTeamId(String eventTeamId) {
		this.eventTeamId = eventTeamId;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

}
