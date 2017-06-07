package com.travel.mate.dto;

import java.util.List;

public class TravelRouteDTO {
	int travelRouteCode;
	int travelCode;
	double lat;
	double lng;
	String location;
	
	List<TravelRouteDTO> trlist;
	
	public int getTravelRouteCode() {
		return travelRouteCode;
	}

	public void setTravelRouteCode(int travelRouteCode) {
		this.travelRouteCode = travelRouteCode;
	}

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

	public List<TravelRouteDTO> getTrlist() {
		return trlist;
	}

	public void setTrlist(List<TravelRouteDTO> trlist) {
		this.trlist = trlist;
	}
}
