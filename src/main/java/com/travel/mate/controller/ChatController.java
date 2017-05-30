package com.travel.mate.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.service.ChatServiceImpl;

@Controller
public class ChatController {

	@Autowired
	ChatServiceImpl chatService;

	//채팅창 뷰
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatView(HttpServletRequest request, Model model) {
		
		try {
			String sCode = request.getParameter("scode"); //보내는 이 (자신)
			String rCode = request.getParameter("rcode"); // 받는 이 (상대방)
			String roomCode = request.getParameter("room"); //room Code 
			String userName = URLDecoder.decode(request.getParameter("name"),"UTF-8");

			System.out.println("정보");
			System.out.println("userCode : " + sCode);
			System.out.println("roomCode : " + roomCode);
			System.out.println("userName : " + userName);
			
			model.addAttribute("rcode", rCode);
			model.addAttribute("scode", sCode);
			model.addAttribute("name", userName);
			model.addAttribute("room", roomCode);
			
			//현재 이 방에 채팅로그가 저장되어있다면, 불러오기
			ArrayList<ChatDTO> list = chatService.showChats(Integer.parseInt(roomCode));
			model.addAttribute("list", list);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "chat";
	}

	//채팅방 리스트 뷰
	@RequestMapping(value = "/chatList")
	public String chatListview(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		String id = authentication.getName();

		//채팅방의 리스트를 불러오는 부분
		ArrayList<ChatRoomDTO> list = chatService.showChatRooms(id);
		model.addAttribute("list", list);
		return "chatList";
	}
	
	@RequestMapping(value="/checkChatRoom")
	public String checkChatRoomExist(HttpServletRequest request ,Model model){
		
		int senderCode = 2; // 채팅 먼저 거는 쪽
		int receiverCode = 3; //채팅 메세지 받는 쪽
		
		
		//채팅방이 있는지 보기
		//없다면 만들고, 룸 번호 건네주기
		//있으면 룸번호 건네주기
		
		return "redirect:/chat";
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(Model model) {
		
		return "viewMessage";
	}

	@RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
	public String viewMessage(Model model) {

		return "viewMeesage";
	}
}
