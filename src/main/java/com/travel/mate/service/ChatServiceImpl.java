package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dao.ChatDAO;
import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.dto.UserDTO;

@Repository
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
	public ArrayList<ChatDTO> showChats(int roomCode) {
		// TODO Auto-generated method stub
		System.out.println("showChats()");
		// 채팅 결과들 목록 나열하고 싶을 때 이곳에서 for 문을 돌리면 됌
		return chatDAO.showChats(roomCode);
	}
	
	@Override
	public ArrayList<ChatDTO> showChats(int roomCode, int messageCode) {
		// TODO Auto-generated method stub
		System.out.println("showChats()");
		ChatDTO chatDTO = new ChatDTO();
		chatDTO.setRoomCode(roomCode);
		chatDTO.setMessageCode(messageCode);
		// 채팅 결과들 목록 나열하고 싶을 때 이곳에서 for 문을 돌리면 됌
		return chatDAO.showChats(chatDTO);
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
	public ChatRoomDTO addRoom(int senderCode, int receiverCode, String latestdate) {
		// TODO Auto-generated method stub
		ChatRoomDTO chatRoom = new ChatRoomDTO();

		chatRoom.setSenderCode(senderCode);
		chatRoom.setreceiverCode(receiverCode);
		chatRoom.setLatestDate(latestdate);
		
		return chatDAO.addRoom(chatRoom);
	}

	@Override
	public int checkUnReadMessage(int userCode) {
		// TODO Auto-generated method stub
		
		return chatDAO.checkUnReadMessage(userCode);
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
