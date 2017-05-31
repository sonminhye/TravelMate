package com.travel.mate.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;

@Repository("UserDAO")
public class UserDAO extends AbstractDAO{

	public ArrayList<UserDTO> selectTestList(){
		return (ArrayList<UserDTO>) selectList("user.showList");
	}
	
	public void insertUser(UserDTO userDTO){
		System.out.println("DAO에서 insertUser를 콜했습니다");
		
		insert("user.insertUser", userDTO);
	}
	
	public void insertUserDetail(UserDetailDTO userDetailDTO){
		System.out.println("DAO에서 insertUserDetail을 콜했습니다");
		
		insert("user.insertUserDetail", userDetailDTO);
	}
	
	public void insertLanguageList(List<LanguageDTO> langDTOList){
		
	}
	
	public int selectUserId(String id){
		System.out.println("DAO에서 selectUserId를 콜했음");
		
		int rowcount = (Integer) selectOne("user.selectUserId", id);
		return rowcount;
	}
}
