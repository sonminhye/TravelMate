package com.travel.mate.service;

import java.util.ArrayList;

import com.travel.mate.dto.ChatRoomDTO;

public interface ChatService {
	public ArrayList<ChatRoomDTO> showChatRooms(int userCode);
}
