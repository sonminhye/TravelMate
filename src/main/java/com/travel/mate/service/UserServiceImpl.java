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
		return userDAO.selectUserDetail(userCode);
	}

	
	/*회원가입을 위해 유저정보를 insert.*/ 
	@Transactional(readOnly=false)
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO, String authority, List<UserLanguageDTO> langs){
		System.out.println("doSignup Service");
		int userCode = 0;
		
		/*userDTO를 먼저 삽입한 후 auto increment 된 PK 값 userCode를 얻어온다.
		userDetailDTO, langs, authority 는 해당 값을 FK로 가지므로 가져온 값을 입력해준다.*/
		userDAO.insertUser(userDTO);					
		userCode = userDTO.getUserCode();             
		
		userDetailDTO.setUserCode(userCode);            
		for(UserLanguageDTO lang : langs){
			lang.setUserCode(userCode);
		}
		
		UserAuthDTO userAuthDTO  = new UserAuthDTO(userCode, authority); 

		userDAO.insertUserDetail(userDetailDTO);
		userDAO.insertUserAuthority(userAuthDTO);
		userDAO.insertUserLanguage(langs);
		
	}
	
	/*아이디 중복체크*/
	public int checkSignup(String id) {
		return userDAO.selectUserId(id);
	}
	
	/*회원정보 수정*/
	@Transactional(readOnly=false)
	public void updateUserDetail(UserDetailDTO userDetailDTO){
		userDAO.updateUserDetail(userDetailDTO);
	}
	
	/*비밀번호 수정*/
	@Transactional(readOnly=false)
	public void updatePassword(UserDTO userDTO){
		userDAO.updatePassword(userDTO);
	}

}
