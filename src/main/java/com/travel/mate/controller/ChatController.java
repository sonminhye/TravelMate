package com.travel.mate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.mate.service.ChatServiceImpl;

@Controller
public class ChatController {
	
	ChatServiceImpl chat;


	@RequestMapping(value = "/chat", method = RequestMethod.GET )
	public String chatView(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String room = request.getParameter("room");
	
		model.addAttribute("name", name);
		model.addAttribute("room", room);
		
		return "chat";
	}
	
	@RequestMapping(value = "/chatList")
	public String chatListview(Model model) {
		
		//�̰� ���� ��쵵, ���⼭ �޼����� ���� �Ŀ�, � �������� ����ڿ��� �������� �ٽ� �����غ��� �� �� ����
		
		return "chatList";
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(Model model) {
		
		//�̰� ���� ��쵵, ���⼭ �޼����� ���� �Ŀ�, � �������� ����ڿ��� �������� �ٽ� �����غ��� �� �� ����
		
		return "viewMessage";
	}
	
	@RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
	public String viewMessage(Model model) {
		
		//ajax �� ���� ����ڰ� ���� ���� �޼����� �������� ���
		//�̰� ���� ��쵵, ���⼭ �޼����� ���� �Ŀ�, � �������� ����ڿ��� �������� �ٽ� �����غ��� �� �� ����
		
		return "viewMeesage";
	}
}
