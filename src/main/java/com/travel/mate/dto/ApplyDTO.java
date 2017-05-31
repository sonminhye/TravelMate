package com.travel.mate.dto;

import java.util.List;

public class ApplyDTO {
	int travelCode;
	int userCode;
	
	List<ApplyDTO> alist;

	public int getTravelCode() {
		return travelCode;
	}

	public void setTravelCode(int travelCode) {
		this.travelCode = travelCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public List<ApplyDTO> getAlist() {
		return alist;
	}

	public void setAlist(List<ApplyDTO> alist) {
		this.alist = alist;
	}
}
