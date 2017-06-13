package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

public interface AdminService {
	public ArrayList<UserDTO> showAllUser();
	public ArrayList<UserDetailDTO> showAllUserDetail();
	public ArrayList<LanguageDTO> showAllLanguage();
	public ArrayList<UserLanguageDTO> showAllUserLanguage();
	public ArrayList<AuthDTO> showAllAuth();
	public ArrayList<UserAuthDTO> showAllUserAuth();
	public ArrayList<SecuredResourceDTO> showAllSecuredResource();
	public ArrayList<SecuredResourceAuthDTO> showAllSecuredResourceAuth();
	public void modifyUserAuth(HashMap<String, String> param);
	public void modifySecuredResourceAuth(List<SecuredResourceAuthDTO> auths);
	
}
