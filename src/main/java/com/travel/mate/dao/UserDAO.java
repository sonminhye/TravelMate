package com.travel.mate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;

@Repository("UserDAO")
public class UserDAO extends AbstractDAO{

	public void insertUser(UserDTO userDTO){
		insert("user.insertUser", userDTO);
	}
	
	public void insertUserDetail(UserDetailDTO userDetailDTO){
		insert("user.insertUserDetail", userDetailDTO);
	}
	
	public void insertUserAuthority(UserAuthDTO userAuthDTO){
		insert("user.insertUserAuthority", userAuthDTO);
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
	
	/*아이디 중복체크
	 *  DB조회 후 id와 중복되는 칼럼의 갯수를 반환*/
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
	
	public void updatePassword(UserDTO userDTO){
		update("user.updatePassword", userDTO);
	}
}
