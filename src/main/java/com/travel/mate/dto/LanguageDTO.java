package com.travel.mate.dto;

public class LanguageDTO {
	private int languageCode;
	private String language;
	
	public LanguageDTO(){
		
	}
	public LanguageDTO(int languageCode, String language) {
		super();
		this.languageCode = languageCode;
		this.language = language;
	}


	public int getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(int languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
