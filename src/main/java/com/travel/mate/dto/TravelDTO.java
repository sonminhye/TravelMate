package com.travel.mate.dto;

import java.util.Date;
import java.util.List;

public class TravelDTO {
	int travelCode;
	int userCode;
	String title;
	String content;
	Date writeDate;
	
	List<TravelDTO> tlist;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public List<TravelDTO> getTlist() {
		return tlist;
	}

	public void setTlist(List<TravelDTO> tlist) {
		this.tlist = tlist;
	}
}
