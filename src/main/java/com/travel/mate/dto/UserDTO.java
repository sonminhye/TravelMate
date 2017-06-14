package com.travel.mate.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {
	private int userCode;
	@NotNull private String id;
	@NotNull private String password;
	
	public UserDTO(){
		
	}

	public UserDTO(int userCode, String id, String password) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.password = password;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserDTO [userCode=" + userCode + ", id=" + id + ", password=" + password + "]";
	}
	
	
	
}
