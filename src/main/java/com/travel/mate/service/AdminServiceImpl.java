package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.mate.dao.AdminDAO;
import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Transactional(readOnly=true)
@Service("AdminService")
public class AdminServiceImpl implements AdminService{

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
	
	@Transactional(readOnly=false)
	public void updateUserAuth(UserAuthDTO userAuthDTO){
		adminDAO.updateUserAuth(userAuthDTO);
	}
	
	@Transactional(readOnly=false)
	public void modifySecuredResourceAuth(List<SecuredResourceAuthDTO> auths){
		int resourceCode = auths.get(0).getResourceCode();
	
		//먼저 기존 권한정보들을 지워줌
		adminDAO.deleteSecuredResourceAuth(resourceCode);
		
		//'없음' 체크박스를 선택하지 않은 경우에만 insert 해줌
		if(!auths.get(0).getAuthority().equals("none")){
			adminDAO.insertSecuredResourceAuth(auths);
		}
	}
	
	//update sortOrder
	@Transactional(readOnly=false)
	public void updateSecuredResource(SecuredResourceDTO securedResourceDTO){
		adminDAO.updateSecuredResource(securedResourceDTO);
	}

}
