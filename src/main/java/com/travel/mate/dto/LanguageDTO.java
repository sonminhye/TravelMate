package com.travel.mate.dto;

import java.util.List;

public class LanguageDTO {
	private int userCode;
	private int languageCode;
	
	private List<LanguageDTO> langDTOList;

	public LanguageDTO(){
		
	}
	
	public LanguageDTO(List<LanguageDTO> langDTOList){
		this.langDTOList = langDTOList;
	}
	
	
	public LanguageDTO(int userCode, int languageCode) {
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

	public List<LanguageDTO> getLangDTOList() {
		return langDTOList;
	}

	public void setLangDTOList(List<LanguageDTO> langDTOList) {
		this.langDTOList = langDTOList;
	}
	
	
}
