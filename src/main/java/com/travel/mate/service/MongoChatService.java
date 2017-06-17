package com.travel.mate.service;

import java.util.ArrayList;
import java.util.List;

import com.travel.mate.dto.ChatDTO;

public interface MongoChatService {
	public ArrayList<ChatDTO> showChats(int roomCode);
	public ArrayList<ChatDTO> showChats(int roomCode, String messageCode);
	public int checkUnReadMessage(int userCode);
	public ArrayList<Object> checkUnReadMessageList(int userCode);
	public void changeUnReadMessage(int roomCode, int userCode);

}
