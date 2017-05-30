package com.travel.mate.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.travel.mate.security.MyUser;
import com.travel.mate.service.ChatServiceImpl;

@Controller
public class ChatController {

	@Autowired
	ChatServiceImpl chatService;

	//채팅창 뷰
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatView(HttpServletRequest request, Model model) {
		
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Object principal = authentication.getPrincipal();
			
			int sCode = ((MyUser)principal).getUserCode();
			
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
		
		String result="";
		
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		int senderCode = ((MyUser)principal).getUserCode();
		int receiverCode = Integer.parseInt(request.getParameter("userCode"));
		ChatRoomDTO chatRoom = chatService.showChatRoomExist(senderCode, receiverCode);
		Date date;
		
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(new Date().toString());
		
		
		if(chatRoom ==null){ //채팅방이 만들어지지 않았다는 얘기
			chatRoom = chatService.addRoom(senderCode, receiverCode, date.toString());
		}
		
		result = "chat?";
		result += "scode" + senderCode + "&";
		result += "scode" + receiverCode + "&";
		result += "scode" + date.toString() + "&";
		result += "room" + chatRoom.getRoomCode()+ "&";
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
