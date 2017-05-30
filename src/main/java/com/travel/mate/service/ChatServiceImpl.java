package com.travel.mate.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dao.ChatDAO;
import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.dto.UserDTO;

@Repository
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<ChatRoomDTO> showChatRooms(String id) {
		// TODO Auto-generated method stub
		System.out.println("showChatList()");
		
		ArrayList<ChatRoomDTO> result = new ArrayList<ChatRoomDTO>();
		
		ChatService dao = sqlSession.getMapper(ChatService.class);
		result = dao.showChatRooms(id);
		
		return result;
	}

	@Override
	public ArrayList<ChatDTO> showChats(int roomCode) {
		// TODO Auto-generated method stub
		System.out.println("showChats()");
		
		ArrayList<ChatDTO> result = new ArrayList<ChatDTO>();
		
		ChatService dao = sqlSession.getMapper(ChatService.class);
		result = dao.showChats(roomCode);
		for(int i = 0 ; i < result.size(); i++){
			ChatDTO dto = result.get(i);
			System.out.println(dto.getSenderName() + " : " + dto.getContent());
		}
		return result;
	}

	@Override
	public int showChatRoomExist(int senderCode, int receiverCode) {
		// TODO Auto-generated method stub
		System.out.println("showChatRoomExist()");
		
		int result = 0;
		
		ChatService dao = sqlSession.getMapper(ChatService.class);
		result = dao.showChatRoomExist(senderCode, receiverCode);
		
		return result;
		
	}
}
