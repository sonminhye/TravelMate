package com.travel.mate.dto;

import java.util.Date;

public class TravelDTO {
	
	int travelCode;
	int userCode;
	String title;
	String content;
	Date writeDate;

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
	
}