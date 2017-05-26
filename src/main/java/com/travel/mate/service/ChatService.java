package com.travel.mate.service;

import java.util.ArrayList;

import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;

public interface ChatService {
	
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode);
	public ArrayList<ChatDTO> showChats(int roomCode);
	public int showChatRoomExist(int senderCode, int receiverCode);
	
}
