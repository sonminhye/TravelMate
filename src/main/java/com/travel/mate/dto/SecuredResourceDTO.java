package com.travel.mate.dto;

public class SecuredResourceDTO {
	private int resourceCode;
	private String resourcePattern;
	private int sortOrder;
	
	public SecuredResourceDTO(){
		
	}

	public SecuredResourceDTO(int resourceCode, String resourcePattern, int sortOrder) {
		super();
		this.resourceCode = resourceCode;
		this.resourcePattern = resourcePattern;
		this.sortOrder = sortOrder;
	}

	public int getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(int resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourcePattern() {
		return resourcePattern;
	}

	public void setResourcePattern(String resourcePattern) {
		this.resourcePattern = resourcePattern;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
}
