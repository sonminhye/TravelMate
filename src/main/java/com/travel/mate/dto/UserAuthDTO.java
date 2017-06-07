package com.travel.mate.dto;

public class UserAuthDTO {
	private int userCode;
	private String authority;
	
	public UserAuthDTO(){
		
	}

	public UserAuthDTO(int userCode, String authority) {
		super();
		this.userCode = userCode;
		this.authority = authority;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
