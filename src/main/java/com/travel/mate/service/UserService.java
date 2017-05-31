package com.travel.mate.service;

import java.util.ArrayList;

import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;


public interface UserService {
	public ArrayList<UserDTO> showList();
	public UserDetailDTO showDetailList(int userCode);
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO);
}
