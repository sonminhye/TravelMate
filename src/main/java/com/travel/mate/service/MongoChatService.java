package com.travel.mate.service;

import java.util.ArrayList;

import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;

public interface MongoChatService {
	public ArrayList<ChatDTO> showChats(int roomCode);
	public ArrayList<ChatDTO> showChats(int roomCode, String messageCode);
	public int checkUnReadMessage(int userCode);
	public ArrayList<ChatRoomDTO> checkUnReadMessageList(int userCode);
	public void changeUnReadMessage(int roomCode, int userCode);
}
