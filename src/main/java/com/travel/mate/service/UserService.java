package com.travel.mate.service;

import java.util.ArrayList;
import java.util.List;

import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;


public interface UserService {
	public ArrayList<UserDTO> showList();
	public void doSignup(UserDTO userDTO, UserDetailDTO userDetailDTO, List<LanguageDTO> langs);
	public int checkSignup(String id);
}
