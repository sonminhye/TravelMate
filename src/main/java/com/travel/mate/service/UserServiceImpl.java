package com.travel.mate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Transactional(readOnly=true)
@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="UserDAO")
	private UserDAO userDAO;

	@Override
	public UserDTO showUser(int userCode){
		return userDAO.selectUser(userCode);
	}
	
	@Override
	public UserDetailDTO showUserDetail(int userCode) {
		// TODO Auto-generated method stub
		return userDAO.selectUserDetail(userCode);
	}

	//회원가입
	@Transactional(readOnly=false)
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO, String authority, List<UserLanguageDTO> langs){
		System.out.println("doSignup Service");
		int userCode = 0;
		
		userDAO.insertUser(userDTO);					//user 테이블에 삽입 후
		userCode = userDTO.getUserCode();               //auto increment된 userCode 값 얻어오기
		
		//userCode값 입력
		userDetailDTO.setUserCode(userCode);            
		for(UserLanguageDTO lang : langs){
			lang.setUserCode(userCode);
		}
		
		UserAuthDTO userAuthDTO  = new UserAuthDTO(userCode, authority); //유저의 권한

		userDAO.insertUserDetail(userDetailDTO);
		userDAO.insertUserAuthority(userAuthDTO);
		userDAO.insertUserLanguage(langs);
		
	}

	public int checkSignup(String id) {
		return userDAO.selectUserId(id);
	}
	
	@Transactional(readOnly=false)
	public void updateUserDetail(UserDetailDTO userDetailDTO){
		userDAO.updateUserDetail(userDetailDTO);
	}
	
	@Transactional(readOnly=false)
	public void updatePassword(UserDTO userDTO){
		userDAO.updatePassword(userDTO);
	}

}
