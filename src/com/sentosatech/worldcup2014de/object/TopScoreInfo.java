/*
 * Name: TopScoreInfo.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.object;

public class TopScoreInfo {

	private String playerNamwe;
	private String countryName;
	private String countryCode;
	private int imageId;
	private int score;
	
	
	
	public String getPlayerNamwe() {
		return playerNamwe;
	}
	public void setPlayerNamwe(String playerNamwe) {
		this.playerNamwe = playerNamwe;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
