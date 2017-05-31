package com.travel.mate.dto;

import java.util.List;

public class LanguageDTO {
	private int userCode;
	private String ableLang;
	
	private List<LanguageDTO> langDTOList;

	public LanguageDTO(){
		
	}
	
	public LanguageDTO(List<LanguageDTO> langDTOList){
		this.langDTOList = langDTOList;
	}
	
	
	public LanguageDTO(int userCode, String ableLang) {
		super();
		this.userCode = userCode;
		this.ableLang = ableLang;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getAbleLang() {
		return ableLang;
	}

	public void setAbleLang(String ableLang) {
		this.ableLang = ableLang;
	}

	public List<LanguageDTO> getLangDTOList() {
		return langDTOList;
	}

	public void setLangDTOList(List<LanguageDTO> langDTOList) {
		this.langDTOList = langDTOList;
	}
	
	
}
