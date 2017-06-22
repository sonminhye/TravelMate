package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.travel.mate.dao.ChatDAO;
import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.dto.UserDTO;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {

	@Resource(name = "ChatDAO")
	private ChatDAO chatDAO;

	@Override
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode) {
		// TODO Auto-generated method stub
		System.out.println("showChatList()");
		//채팅방 목록을 불러오는 함수
		return chatDAO.showChatRooms(userCode);
	}

	@Override
	public ChatRoomDTO showChatRoomExist(int senderCode, int receiverCode) {
		// TODO Auto-generated method stub
		System.out.println("showChatRoomExist()");
		
		ChatRoomDTO chatRoom = new ChatRoomDTO();
		chatRoom.setSenderCode(senderCode);
		chatRoom.setreceiverCode(receiverCode);
		
		return chatDAO.showChatRoomExist(chatRoom);
	}


	@Override
	public void changeUnReadMessage(int roomCode, int userCode) {
		// TODO Auto-generated method stub
		ChatDTO chat = new ChatDTO();
		
		chat.setRoomCode(roomCode);
		chat.setReceiverCode(userCode);
	
		chatDAO.changeUnReadMessage(chat);
	}
	
	
}
