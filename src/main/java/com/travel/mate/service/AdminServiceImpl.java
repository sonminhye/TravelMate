package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dao.AdminDAO;
import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Repository
public class AdminServiceImpl implements AdminService{
	@Autowired
	private SqlSession sqlSession;
	
	@Resource(name="AdminDAO")
	private AdminDAO adminDAO;

	public ArrayList<UserDTO> showAllUser() {
		return adminDAO.selectUserList();
	}

	public ArrayList<UserDetailDTO> showAllUserDetail() {
		return adminDAO.selectUserDetailList();
	}

	public ArrayList<LanguageDTO> showAllLanguage(){
		return adminDAO.selectLanguageList();
	}
	
	public ArrayList<UserLanguageDTO> showAllUserLanguage(){
		return adminDAO.selectUserLanguageList();
	}
	
	public ArrayList<AuthDTO> showAllAuth() {
		return adminDAO.selectAuthList();
	}

	public ArrayList<UserAuthDTO> showAllUserAuth() {
		return adminDAO.selectUserAuthList();
	}

	public ArrayList<SecuredResourceDTO> showAllSecuredResource() {
		return adminDAO.selectSecuredResourceList();
	}

	public ArrayList<SecuredResourceAuthDTO> showAllSecuredResourceAuth() {
		return adminDAO.selectSecuredResourceAuthList();
	}
	
	public void modifyUserAuth(HashMap<String, String> param){
		adminDAO.updateUserAuth(param);
	}

}
