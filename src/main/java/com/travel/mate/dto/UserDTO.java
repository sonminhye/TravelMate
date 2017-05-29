package com.travel.mate.dto;

public class UserDTO {
	private int userCode;
	private String id;
	private String password;
	private String authority;
	
	public UserDTO(){
		
	}

	public UserDTO(int userCode, String id, String password, String authority) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.password = password;
		this.authority = authority;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserDTO [userCode=" + userCode + ", id=" + id + ", password=" + password + ", authority=" + authority
				+ "]";
	}
	
	
	
}
