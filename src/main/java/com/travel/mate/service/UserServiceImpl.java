package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Transactional(readOnly=true)
@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="UserDAO")
	private UserDAO userDAO;
	
	//test용 리스트출력
	@Override
	public ArrayList<UserDTO> showList(){
		System.out.println("listService");
		return userDAO.selectTestList();
	}
	
	//회원가입
	@Transactional(readOnly=false)
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO, String authority, List<UserLanguageDTO> langs){
		System.out.println("doSignup Service");
		int userCode = 0;
		
		userDAO.insertUser(userDTO);
		userCode = userDTO.getUserCode();               //auto increment된 값 얻어오기
		System.out.println("userCode : " + userCode);   //확인용출력
		
		userDetailDTO.setUserCode(userCode);            //PK userCode값 입력
		for(UserLanguageDTO lang : langs){
			lang.setUserCode(userCode);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		param.put("authority", authority);

		userDAO.insertUserDetail(userDetailDTO);
		userDAO.insertUserAuthority(param);
		userDAO.insertUserLanguage(langs);
		
	}

	@Override
	public UserDetailDTO showUserDetail(int userCode) {
		// TODO Auto-generated method stub
		return userDAO.selectUserDetail(userCode);
	}

	public int checkSignup(String id) {
		return userDAO.selectUserId(id);
	}

}
