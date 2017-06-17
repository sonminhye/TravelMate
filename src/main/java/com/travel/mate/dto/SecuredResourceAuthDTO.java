package com.travel.mate.dto;

import java.util.List;

public class SecuredResourceAuthDTO {
	private int resourceCode;
	private String authority;
	
	private List<SecuredResourceAuthDTO> securedResourceAuthDTOList;
	
	public SecuredResourceAuthDTO(){
		
	}

	public SecuredResourceAuthDTO(List<SecuredResourceAuthDTO> securedResourceAuthDTOList) {
		this.securedResourceAuthDTOList = securedResourceAuthDTOList;
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

	public List<SecuredResourceAuthDTO> getSecuredResourceAuthDTOList() {
		return securedResourceAuthDTOList;
	}

	public void setSecuredResourceAuthDTOList(List<SecuredResourceAuthDTO> securedResourceAuthDTOList) {
		this.securedResourceAuthDTOList = securedResourceAuthDTOList;
	}
	
	
}
