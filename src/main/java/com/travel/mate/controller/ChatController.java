package com.travel.mate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.mate.service.ChatServiceImpl;

@Controller
public class ChatController {
	
	ChatServiceImpl chat;
	
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(Model model) {
		
		//�̰� ���� ��쵵, ���⼭ �޼����� ���� �Ŀ�, � �������� ����ڿ��� �������� �ٽ� �����غ��� �� �� ����
		
		return "viewMessage";
	}
	
	@RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
	public String viewMessage(Model model) {
		
		//�̰� ���� ��쵵, ���⼭ �޼����� ���� �Ŀ�, � �������� ����ڿ��� �������� �ٽ� �����غ��� �� �� ����
		
		return "viewMeesage";
	}
}
