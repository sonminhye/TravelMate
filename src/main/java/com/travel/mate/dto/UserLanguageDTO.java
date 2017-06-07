package com.travel.mate.dto;

import java.util.List;

public class UserLanguageDTO {
	private int userCode;
	private int languageCode;
	
	private List<UserLanguageDTO> langDTOList;

	public UserLanguageDTO(){
		
	}
	
	public UserLanguageDTO(List<UserLanguageDTO> langDTOList){
		this.langDTOList = langDTOList;
	}
	
	
	public UserLanguageDTO(int userCode, int languageCode) {
		super();
		this.userCode = userCode;
		this.languageCode = languageCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}


	public int getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(int languageCode) {
		this.languageCode = languageCode;
	}

	public List<UserLanguageDTO> getLangDTOList() {
		return langDTOList;
	}

	public void setLangDTOList(List<UserLanguageDTO> langDTOList) {
		this.langDTOList = langDTOList;
	}
	
	
}
