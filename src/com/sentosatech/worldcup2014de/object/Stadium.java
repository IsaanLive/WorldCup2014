package com.sentosatech.worldcup2014de.object;

public class Stadium {
	private int idStadium;
	private String capacity;
	private String fullNameStadium;
	private int imageId;

	public Stadium() {

	}

	public Stadium(int idStadium, String capacity, String fullNameStadium,
			int imageId) {
		super();
		this.idStadium = idStadium;
		this.capacity = capacity;
		this.imageId = imageId;
		this.fullNameStadium = fullNameStadium;
	}

	public int getIdStadium() {
		return idStadium;
	}

	public void setIdStadium(int idStadium) {
		this.idStadium = idStadium;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getFullNameStadium() {
		return fullNameStadium;
	}

	public void setFullNameStadium(String fullNameStadium) {
		this.fullNameStadium = fullNameStadium;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
