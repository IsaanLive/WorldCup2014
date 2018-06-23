/*
 * Name: ImageInfo.java,v $
 * Version: $Revision: 1.4 $
 * Date: $Date: Nov 15, 2013 2:05:59 PM $
 *
 * Copyright (C) Wolfgang Holzem - All rights reserved.
 */
package com.sentosatech.worldcup2014de.object;

public class ImageInfo {
	private String countryName;
	private String countryName2;
	private String countryCode;
	private int imageId;

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

	public ImageInfo(String countryName, String countryName2,
			String countryCode, int imageId) {
		super();
		this.countryName2 = countryName2;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.imageId = imageId;
	}

	public String getCountryName2() {
		return countryName2;
	}

	public void setCountryName2(String countryName2) {
		this.countryName2 = countryName2;
	}

}
