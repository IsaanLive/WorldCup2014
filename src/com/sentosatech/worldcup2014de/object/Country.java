package com.sentosatech.worldcup2014de.object;

public class Country {
	private int countryId;
	private String countryName;
	private String countryKey;
	private int countryImage;
	private int groupId;
	private int point;
	private int allGoalNumber;
	private int allLoseNumber;
	private String poisition;
	private String countryInfo;
	private int mp;
	private int w;
	private int d;
	private int l;
	public Country() {
	}
	public Country(int countryId, String countryName, String countryKey,
			int countryImage, int groupId, int point, int allGoalNumber,
			int allLoseNumber, String poisition, String countryInfo, int mp,
			int w, int d, int l) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryKey = countryKey;
		this.countryImage = countryImage;
		this.groupId = groupId;
		this.point = point;
		this.allGoalNumber = allGoalNumber;
		this.allLoseNumber = allLoseNumber;
		this.poisition = poisition;
		this.countryInfo = countryInfo;
		this.mp = mp;
		this.w = w;
		this.d = d;
		this.l = l;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryKey() {
		return countryKey;
	}
	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}
	public int getCountryImage() {
		return countryImage;
	}
	public void setCountryImage(int countryImage) {
		this.countryImage = countryImage;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getAllGoalNumber() {
		return allGoalNumber;
	}
	public void setAllGoalNumber(int allGoalNumber) {
		this.allGoalNumber = allGoalNumber;
	}
	public int getAllLoseNumber() {
		return allLoseNumber;
	}
	public void setAllLoseNumber(int allLoseNumber) {
		this.allLoseNumber = allLoseNumber;
	}
	public String getPoisition() {
		return poisition;
	}
	public void setPoisition(String poisition) {
		this.poisition = poisition;
	}
	public String getCountryInfo() {
		return countryInfo;
	}
	public void setCountryInfo(String countryInfo) {
		this.countryInfo = countryInfo;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	
}
