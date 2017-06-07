package com.travel.mate.dto;

public class SecuredResourceAuthDTO {
	private int resourceCode;
	private String authority;
	
	public SecuredResourceAuthDTO(){
		
	}

	public SecuredResourceAuthDTO(int resourceCode, String authority) {
		super();
		this.resourceCode = resourceCode;
		this.authority = authority;
	}

	public int getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(int resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
