package com.travel.mate.dto;

import java.util.List;

public class TravelImageDTO {
	int travelCode;
	String image;
	
	List<TravelImageDTO> travelImages;

	public int getTravelCode() {
		return travelCode;
	}

	public void setTravelCode(int travelCode) {
		this.travelCode = travelCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<TravelImageDTO> getTravelImages() {
		return travelImages;
	}

	public void setTravelImages(List<TravelImageDTO> travelImages) {
		this.travelImages = travelImages;
	}
}
