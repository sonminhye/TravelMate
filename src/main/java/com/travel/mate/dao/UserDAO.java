package com.travel.mate.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;

@Repository("UserDAO")
public class UserDAO extends AbstractDAO{

	public ArrayList<UserDTO> selectTestList(){
		return (ArrayList<UserDTO>) selectList("user.showList");
	}
	
	public int insertUser(UserDTO userDTO){
		System.out.println("DAO에서 insertUser를 콜했습니다");
		
		int userCode;
		userCode = (Integer) insert("user.insertUser", userDTO);
		return userCode;
	}
	
	public void insertUserDetail(UserDetailDTO userDetailDTO){
		System.out.println("DAO에서 insertUserDetail을 콜했습니다");
		
		insert("user.insertUserDetail", userDetailDTO);
	}
}
