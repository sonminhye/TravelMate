package com.travel.mate.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.service.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	//TEST용//////////////
	@RequestMapping(value = "/testList", method = RequestMethod.GET)
	public String showList(Model model) {
		System.out.println("list()");
		
		List<UserDTO> list = userService.showList();
		model.addAttribute("list", list);
		return "testList";

	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@ModelAttribute UserDTO userDTO ,@ModelAttribute UserDetailDTO userDetailDTO,
						 @ModelAttribute List<LanguageDTO> langDTOList, Model model) {
		System.out.println("signup controller");
		//값확인용
		System.out.println(userDTO.toString());
		System.out.println(userDetailDTO.toString());
		
		userService.doSignup(userDTO, userDetailDTO, langDTOList);
			
		for(LanguageDTO dto : langDTOList){
			System.out.println("리스트값 : " + dto.getAbleLang());
		}
		return "signIn"; //회원가입 후 로그인 페이지로
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) {
	
		return "signIn";
	}
	
	@RequestMapping(value = "/checkSignup", method = RequestMethod.POST)
	@ResponseBody
	public String checkSignup(HttpServletRequest request, Model model) {
		System.out.println("chcekSignup controller");
		
		String id = request.getParameter("id");
		int rowcount = userService.checkSignup(id);
		
		return String.valueOf(rowcount);
	}
	
	
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Locale locale, Model model) {
	
		return "myPage";
	}
	
	

}
