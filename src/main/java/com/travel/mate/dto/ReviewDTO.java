package com.travel.mate.dto;

import java.util.Date;
import java.util.List;

public class ReviewDTO {
	int travelCode;
	int userCode;
	String content;
	Date writeDate;
	
	List<ReviewDTO> rlist;
	
	public List<ReviewDTO> getRlist() {
		return rlist;
	}
	public void setRlist(List<ReviewDTO> rlist) {
		this.rlist = rlist;
	}
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
