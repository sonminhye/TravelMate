package com.travel.mate.dto;

public class AuthDTO {
	private String authority;
	private String authorityName;
	
	public AuthDTO(){
		
	}
	
	public AuthDTO(String authority, String authorityName) {
		super();
		this.authority = authority;
		this.authorityName = authorityName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	
	
}
