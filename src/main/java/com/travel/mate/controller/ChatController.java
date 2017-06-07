package com.travel.mate.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
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

import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.ChatDTO;
import com.travel.mate.dto.ChatRoomDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.security.MyUser;
import com.travel.mate.service.ChatServiceImpl;
import com.travel.mate.service.UserServiceImpl;

@Controller
public class ChatController {

	@Autowired
	ChatServiceImpl chatService;

	@Autowired
	UserServiceImpl userService;

	Authentication auth;
	
	//채팅창 뷰
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatView(HttpServletRequest request, Model model) {
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		int userCode = ((MyUser)principal).getUserCode();
		System.out.println(" 내 코드 : " + userCode);
		UserDetailDTO userDetail = userService.showUserDetail(userCode);
		String name = userDetail.getName();
		String rCode = request.getParameter("rcode"); // 받는 이 (상대방)
		String roomCode = request.getParameter("room"); //room Code 
		
		System.out.println("내 이름: " + name);
		
		model.addAttribute("rcode", rCode);
		model.addAttribute("scode", userCode);
		model.addAttribute("name", name);
		model.addAttribute("room", roomCode);
		
		//현재 이 방에 채팅로그가 저장되어있다면, 불러오기
		ArrayList<ChatDTO> list = chatService.showChats(Integer.parseInt(roomCode));
		model.addAttribute("list", list);
		
		return "chat";
	}

	//채팅방 리스트 뷰
	@RequestMapping(value = "/chatList")
	public String chatListview(Model model) {
	
		auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal =  auth.getPrincipal();
		int myCode = ((MyUser)principal).getUserCode();
		//채팅방의 리스트를 불러오는 부분
		ArrayList<ChatRoomDTO> list = chatService.showChatRooms(((MyUser)principal).getUserCode());
		model.addAttribute("list", list);
		model.addAttribute("myCode", myCode);
		
		return "chatList";
	}
	
	@RequestMapping(value="/checkChatRoom")
	public String checkChatRoomExist(HttpServletRequest request ,Model model){
		
		String result="";
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal =  auth.getPrincipal();
		int senderCode = ((MyUser)principal).getUserCode(); //senderCode
		int receiverCode = Integer.parseInt(request.getParameter("userCode")); //receiverCode(상대방)
				
		Date date = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = fm.format(date);
		
		System.out.println("넣으려는 항목들" + senderCode + "," + receiverCode + "," + currentTime.toString());
		
		ChatRoomDTO chatRoom = chatService.showChatRoomExist(senderCode, receiverCode);
		
		if(chatRoom ==null){ //채팅방이 만들어지지 않았다는 얘기
			chatRoom = chatService.addRoom(senderCode, receiverCode, currentTime);
		}
		
		result = "redirect:chat?";
		result += "rcode=" + receiverCode + "&";
		result += "room=" + chatRoom.getRoomCode();
		return result;
	}
	
	@RequestMapping(value="/chattema")
	public String chatThemeTest(Model model){
		
		
		return "chattema";
	}

}
