package com.travel.mate.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dao.ChatDAO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.dto.UserDTO;

@Repository
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode) {
		// TODO Auto-generated method stub
		System.out.println("showChatList()");
		
		ArrayList<ChatRoomDTO> result = new ArrayList<ChatRoomDTO>();
		
		ChatService dao = sqlSession.getMapper(ChatService.class);
		result = dao.showChatRooms(userCode);
		
		return result;
	}

}
