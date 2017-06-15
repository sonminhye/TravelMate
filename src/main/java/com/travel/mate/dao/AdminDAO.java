package com.travel.mate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Repository("AdminDAO")
public class AdminDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> selectUserList(){
		return (ArrayList<UserDTO>)selectList("admin.selectUserList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDetailDTO> selectUserDetailList(){
		return (ArrayList<UserDetailDTO>)selectList("admin.selectUserDetailList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<LanguageDTO> selectLanguageList(){
		return (ArrayList<LanguageDTO>)selectList("admin.selectLanguageList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserLanguageDTO> selectUserLanguageList(){
		return (ArrayList<UserLanguageDTO>)selectList("admin.selectUserLanguageList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AuthDTO> selectAuthList(){
		return (ArrayList<AuthDTO>)selectList("admin.selectAuthList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserAuthDTO> selectUserAuthList(){
		return (ArrayList<UserAuthDTO>)selectList("admin.selectUserAuthList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SecuredResourceDTO> selectSecuredResourceList(){
		return (ArrayList<SecuredResourceDTO>)selectList("admin.selectSecuredResourceList");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SecuredResourceAuthDTO> selectSecuredResourceAuthList(){
		return (ArrayList<SecuredResourceAuthDTO>)selectList("admin.selectSecuredResourceAuthList");
	}
	
	public void updateUserAuth(UserAuthDTO userAuthDTO){
		update("admin.updateUserAuth", userAuthDTO);
	}
	
	public void deleteSecuredResourceAuth(int resourceCode){
		delete("admin.deleteSecuredResourceAuth", resourceCode);
	}
	
	public void insertSecuredResourceAuth(List<SecuredResourceAuthDTO> auths){
		insert("admin.insertSecuredResourceAuth", auths);
	}
	
	public void updateSecuredResource(SecuredResourceDTO securedResourceDTO){
		update("admin.updateSecuredResource", securedResourceDTO);
	}
}
