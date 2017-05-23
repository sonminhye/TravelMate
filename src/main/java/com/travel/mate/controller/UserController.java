package com.travel.mate.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
//
//	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	public String signUp(Model model) {
//		
//		return "signup";
//	}
//	
//	@RequestMapping(value = "/signin", method = RequestMethod.GET)
//	public String signIn(Model model) {
//		
//		return "signup";
//	}	
	
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Locale locale, Model model) {
	
		return "myPage";
	}
	
	
}
