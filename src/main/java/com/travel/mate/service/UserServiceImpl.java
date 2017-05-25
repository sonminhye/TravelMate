package com.travel.mate.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dto.UserDTO;

@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<UserDTO> showList(){
		System.out.println("listService");
		
		ArrayList<UserDTO> result = new ArrayList<UserDTO>();
		
		UserService dao = sqlSession.getMapper(UserService.class);
		result = dao.showList();
		
		return result;
		
	}

}
