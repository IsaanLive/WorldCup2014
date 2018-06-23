/*
 * Name: Match.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.object;

public class Match {

	private int matchId;
	private String matchStartTime;
	private String mediumId;
	private int groupId;
	private String matchDate;
	private String matchInfo;
	private String fullNameTeam1;
	private String fullNameTeam2;
	private String codeTeam1;
	private String codeTeam2;
	private int imageId1;
	private int imageId2;
	private String score1;
	private String score2;
	private String matchStatus;
	private int country1;
	private int country2;
	private int goalCountry1;
	private int goalCountry2;
	private int idStadium;
	private String stadiumName;
	private String winTeam;
	private int imageWin;

	public int getImageWin() {
		return imageWin;
	}

	public void setImageWin(int imageWin) {
		this.imageWin = imageWin;
	}

	public String getWinTeam() {
		return winTeam;
	}

	public void setWinTeam(String winTeam) {
		this.winTeam = winTeam;
	}

	public Match() {
	}

	public Match(int matchId, String matchStartTime, String mediumId,
			int groupId, String matchDate, String matchInfo,
			String matchStatus, int country1, int country2, int goalCountry1,
			int goalCountry2, int idStadium) {

		super();
		this.matchId = matchId;
		this.matchStartTime = matchStartTime;
		this.mediumId = mediumId;
		this.groupId = groupId;
		this.matchDate = matchDate;
		this.matchInfo = matchInfo;
		this.matchStatus = matchStatus;
		this.country1 = country1;
		this.country2 = country2;
		this.goalCountry1 = goalCountry1;
		this.goalCountry2 = goalCountry2;
		this.idStadium = idStadium;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getMatchStartTime() {
		return matchStartTime;
	}

	public void setMatchStartTime(String matchStartTime) {
		this.matchStartTime = matchStartTime;
	}

	public String getStadiumId() {
		return mediumId;
	}

	public void setMediumId(String mediumId) {
		this.mediumId = mediumId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(String matchInfo) {
		this.matchInfo = matchInfo;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	public int getCountry1() {
		return country1;
	}

	public void setCountry1(int country1) {
		this.country1 = country1;
	}

	public int getCountry2() {
		return country2;
	}

	public void setCountry2(int country2) {
		this.country2 = country2;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public int getGoalCountry1() {
		return goalCountry1;
	}

	public String getCodeTeam1() {
		return codeTeam1;
	}

	public void setCodeTeam1(String codeTeam1) {
		this.codeTeam1 = codeTeam1;
	}

	public String getCodeTeam2() {
		return codeTeam2;
	}

	public void setCodeTeam2(String codeTeam2) {
		this.codeTeam2 = codeTeam2;
	}

	public void setGoalCountry1(int goalCountry1) {
		this.goalCountry1 = goalCountry1;
	}

	public int getGoalCountry2() {
		return goalCountry2;
	}

	public void setGoalCountry2(int goalCountry2) {
		this.goalCountry2 = goalCountry2;
	}

	public int getIdStadium() {
		return idStadium;
	}

	public void setIdStadium(int idStadium) {
		this.idStadium = idStadium;
	}

	public String getFullNameTeam1() {
		return fullNameTeam1;
	}

	public void setFullNameTeam1(String fullNameTeam1) {
		this.fullNameTeam1 = fullNameTeam1;
	}

	public String getFullNameTeam2() {
		return fullNameTeam2;
	}

	public void setFullNameTeam2(String fullNameTeam2) {
		this.fullNameTeam2 = fullNameTeam2;
	}

	public int getImageId1() {
		return imageId1;
	}

	public void setImageId1(int imageId1) {
		this.imageId1 = imageId1;
	}

	public int getImageId2() {
		return imageId2;
	}

	public void setImageId2(int imageId2) {
		this.imageId2 = imageId2;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}
}
