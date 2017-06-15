package com.travel.mate.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

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
	@RequestMapping(value = "/chat")
	public String chatView(HttpServletRequest request, Model model) {
		String rCode, roomCode;
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap!=null){
        	Map<String, Integer> map = (HashMap) flashMap.get("param");
        	rCode = map.get("rcode").toString();
        	roomCode = map.get("room").toString();
        }else{
        	rCode = request.getParameter("rcode"); // 받는 이 (상대방)
    		roomCode = request.getParameter("room"); //room Code 
        }
		
		int userCode = getUserCode(); //밑에 정의해준 userCode 를 받아오는 함수를 호출
		UserDetailDTO userDetail = userService.showUserDetail(userCode);
		String name = userDetail.getName();
		
		model.addAttribute("rcode", rCode);
		model.addAttribute("scode", userCode);
		model.addAttribute("name", name);
		model.addAttribute("room", roomCode);
		
		System.out.println(roomCode);
		
		//읽지않은 메세지가 있다면 읽기
		chatService.changeUnReadMessage(Integer.parseInt(roomCode), userCode);
		
		//현재 이 방에 채팅로그가 저장되어있다면, 불러오기
		ArrayList<ChatDTO> list = chatService.showChats(Integer.parseInt(roomCode));
		model.addAttribute("list", list);
		
		return "chat";
	}

	//채팅방 리스트 뷰
	@RequestMapping(value = "/chatList")
	public String chatListview(Model model) {
	
		int userCode = getUserCode();
		//채팅방의 리스트를 불러오는 부분
		ArrayList<ChatRoomDTO> list = chatService.showChatRooms(userCode);
		
		model.addAttribute("list", list);
		model.addAttribute("myCode", userCode);
		
		return "chatList";
	}
	
	@RequestMapping(value="/checkChatRoom")
	public String checkChatRoomExist(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		
		String result="";
		
		int senderCode = getUserCode(); //나의 코드
		int receiverCode = Integer.parseInt(request.getParameter("userCode")); //receiverCode(상대방)
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = fm.format(new Date());
		
		//두 유저간의 채팅방이 있는지 여부를 구해오는 함수
		ChatRoomDTO chatRoom = chatService.showChatRoomExist(senderCode, receiverCode);
		
		if(chatRoom ==null){ //채팅방이 없을 때
			chatRoom = chatService.addRoom(senderCode, receiverCode, currentTime);
		}
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("rcode",receiverCode);
		map.put("room", chatRoom.getRoomCode());		
		
		redirectAttributes.addFlashAttribute("param", map);
		result = "redirect:chat";
		return result;
	}
	
	//채팅방 리스트 뷰
	@ResponseBody
	@RequestMapping(value = "/getMoreChats", method=RequestMethod.POST)
	public ArrayList<ChatDTO> getMoreChats(@RequestBody Map<Object, Object> map) {
		
		String messageCode = map.get("messageCode").toString();
		String roomCode = map.get("room").toString();
		System.out.println(messageCode + "," + roomCode);
		//채팅방의 리스트를 불러오는 부분햣 
		ArrayList<ChatDTO> list = chatService.showChats(Integer.parseInt(roomCode), Integer.parseInt(messageCode));
		return list;
	}
	
	//유저의 코드를 받아오는 함수
	public int getUserCode(){
		
		auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal =  auth.getPrincipal();
		return ((MyUser)principal).getUserCode();
		
	}
}
