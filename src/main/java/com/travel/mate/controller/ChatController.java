package com.travel.mate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.mate.service.ChatServiceImpl;

@Controller
public class ChatController {
	
	ChatServiceImpl chat;


	@RequestMapping(value = "/chat")
	public String chatView(Model model) {
		
		//이거 같은 경우도, 여기서 메세지를 보낸 후에, 어떤 페이지를 사용자에게 보여줄지 다시 논의해봐야 할 것 같음
		
		return "chat";
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(Model model) {
		
		//이거 같은 경우도, 여기서 메세지를 보낸 후에, 어떤 페이지를 사용자에게 보여줄지 다시 논의해봐야 할 것 같음
		
		return "viewMessage";
	}
	
	@RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
	public String viewMessage(Model model) {
		
		//ajax 를 통해 사용자가 읽지 않은 메세지를 가져오는 경우
		//이거 같은 경우도, 여기서 메세지를 보낸 후에, 어떤 페이지를 사용자에게 보여줄지 다시 논의해봐야 할 것 같음
		
		return "viewMeesage";
	}
}
