package com.travel.mate.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.service.ChatService;

@Repository("ChatDAO")
public class ChatDAO extends AbstractDAO{
	
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode) {
		// TODO Auto-generated method stub
		System.out.println("showChatList()");
		return (ArrayList<ChatRoomDTO>) selectList("chat.showChatRooms", userCode);
	}

	public ArrayList<ChatDTO> showChats(int roomCode) {
		// TODO Auto-generated method stub
		System.out.println("showChats()");
		return (ArrayList<ChatDTO>) selectList("chat.showChats", roomCode);
	}

	public ChatRoomDTO showChatRoomExist(ChatRoomDTO chatRoom) {
		// TODO Auto-generated method stub
		System.out.println("showChatRoomExist()");
		
		//select One 인지, selectList 인지 모르겠음
		return (ChatRoomDTO) selectOne("chat.showChatRoomExist", chatRoom);
	}

	public ChatRoomDTO addRoom(ChatRoomDTO chatRoom) {
		// TODO Auto-generated method stub
		System.out.println("전 : "+ chatRoom.getRoomCode());
		insert("chat.addChatRoom", chatRoom);
		System.out.println("후 : "+ chatRoom.getRoomCode());
		return chatRoom;
	}
	
	public int checkUnReadMessage(int userCode) {
		// TODO Auto-generated method stub
		return (Integer) selectOne("chat.checkUnReadMessage", userCode);
	}

	public void changeUnReadMessage(ChatDTO chat) {
		// TODO Auto-generated method stub
		update("chat.changeUnReadMessage", chat);
	}
}
