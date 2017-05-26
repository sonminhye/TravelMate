package com.travel.mate.dto;

import java.util.List;

public class TravelRouteDTO {
	int travelCode;
	double lat;
	double lng;
	String location;
	
	List<TravelRouteDTO> TravelRouteList;

	public int getTravelCode() {
		return travelCode;
	}

	public void setTravelCode(int travelCode) {
		this.travelCode = travelCode;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<TravelRouteDTO> getTravelRouteList() {
		return TravelRouteList;
	}

	public void setTravelRouteList(List<TravelRouteDTO> travelRouteList) {
		TravelRouteList = travelRouteList;
	}
}
