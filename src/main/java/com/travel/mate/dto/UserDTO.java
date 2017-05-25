package com.travel.mate.dto;

public class UserDTO {
	private int userCode;
	private String id;
	private String password;
	
	public UserDTO(){
		
	}
	
	public UserDTO(int userCode, String id, String password) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.password = password;
	}
	public int getuserCode() {
		return userCode;
	}
	public void setuserCode(int userCode) {
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
	
	
}
