package com.travel.mate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.service.AdminServiceImpl;

@Controller
public class AdminController {
	@Autowired
	private AdminServiceImpl adminService;
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String adminPage(Locale locale, Model model) {
		System.out.println("Admincontroller!!!!!");
		List<UserDTO> userList = adminService.showAllUser();
		List<UserDetailDTO> userDetailList = adminService.showAllUserDetail();
		List<LanguageDTO> languageList = adminService.showAllLanguage();
		List<UserLanguageDTO> userLanguageList = adminService.showAllUserLanguage();
		List<AuthDTO> authList = adminService.showAllAuth();
		List<UserAuthDTO> userAuthList = adminService.showAllUserAuth();
		List<SecuredResourceDTO> securedResourceList = adminService.showAllSecuredResource();
		List<SecuredResourceAuthDTO> securedResourceAuthList = adminService.showAllSecuredResourceAuth();

		
		model.addAttribute("userList", userList);
		model.addAttribute("userDetailList", userDetailList);
		model.addAttribute("languageList", languageList);
		model.addAttribute("userLanguageList", userLanguageList);
		model.addAttribute("authList", authList);
		model.addAttribute("userAuthList", userAuthList);
		model.addAttribute("securedResourceList", securedResourceList);
		model.addAttribute("securedResourceAuthList", securedResourceAuthList);
		
		return "adminPage";
	}
	
	
	@RequestMapping(value = "/modifyUserAuth", method = RequestMethod.POST)
	public String modifyUserAuth(@RequestParam("userCode") String userCode, 
			                 @RequestParam("authList") String authority,
							 Model model) {
		System.out.println("userCode : " + userCode);
		System.out.println("auth : " + authority);
	
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("userCode", userCode);
		param.put("authority", authority);
		
		adminService.modifyUserAuth(param);
		
		return "redirect:adminPage";
	}

	
}
