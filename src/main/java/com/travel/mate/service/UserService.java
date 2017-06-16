package com.travel.mate.service;

import java.util.ArrayList;
import java.util.List;

import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;


public interface UserService {
	public ArrayList<UserDTO> showList();

	public UserDTO showUser(int userCode);
	public UserDetailDTO showUserDetail(int userCode);
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO, String authority, List<UserLanguageDTO> langs);
	public int checkSignup(String id);
	public void updateUserDetail(UserDetailDTO userDetailDTO);
}
