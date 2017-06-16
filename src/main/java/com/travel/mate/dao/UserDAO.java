package com.travel.mate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;

@Repository("UserDAO")
public class UserDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> selectTestList(){
		return (ArrayList<UserDTO>) selectList("user.showList");
	}
	
	public void insertUser(UserDTO userDTO){
		insert("user.insertUser", userDTO);
	}
	
	public void insertUserDetail(UserDetailDTO userDetailDTO){
		insert("user.insertUserDetail", userDetailDTO);
	}
	
	public void insertUserAuthority(Map<String, Object> param){
		insert("user.insertUserAuthority", param);
	}
	
	public UserDTO selectUser(int userCode){
		return (UserDTO) selectOne("user.selectUser", userCode);
	}
	
	public UserDetailDTO selectUserDetail(int userCode){
		return (UserDetailDTO) selectOne("user.selectUserDetail", userCode);
	}

	public void insertUserLanguage(List<UserLanguageDTO> langs){
		insert("user.insertUserLanguage", langs);
	}
	
	/*아이디 중복체크*/
	public int selectUserId(String id){
		int rowcount = (Integer) selectOne("user.selectUserId", id);
		return rowcount;
	}
	
	public void updateUserDetail(UserDetailDTO userDetailDTO){
		update("user.updateUserDetail", userDetailDTO);
	}
	
	// userDetail meanPoint update
	public void updateUserMeanPoint(UserDetailDTO userDetailDto) {
		update("user.updateUserMeanPoint", userDetailDto);
	}
}
