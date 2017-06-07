package com.travel.mate.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;

public interface ChatService {
	
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode);
	public ArrayList<ChatDTO> showChats(int roomCode);
	public ChatRoomDTO showChatRoomExist(int senderCode, int receiverCode);
	public ChatRoomDTO addRoom(int senderCode, int receiverCode, String date);
	public int checkUnReadMessage(int userCode);
	public void changeUnReadMessage(int roomCode, int userCode);
}
