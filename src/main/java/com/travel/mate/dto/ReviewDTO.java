package com.travel.mate.dto;

import java.util.Date;
import java.util.List;

public class ReviewDTO {
	int reviewCode;
	int applyCode;
	String content;
	Date writeDate;
	
	List<ReviewDTO> rlist;

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public int getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(int applyCode) {
		this.applyCode = applyCode;
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

	public List<ReviewDTO> getRlist() {
		return rlist;
	}

	public void setRlist(List<ReviewDTO> rlist) {
		this.rlist = rlist;
	}
	
}
