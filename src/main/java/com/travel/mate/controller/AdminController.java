package com.travel.mate.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.travel.mate.security.service.ReloadableFilterInvocationSecurityMetadataSource;
import com.travel.mate.service.AdminServiceImpl;

@Controller
public class AdminController {
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	ReloadableFilterInvocationSecurityMetadataSource relodable;
	
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

	@RequestMapping(value = "/modifySecuredResourceAuth", method = RequestMethod.POST)
	public String modifySecuredResourceAuth(@ModelAttribute("securedResourceAuthDTOList") SecuredResourceAuthDTO securedResourceAuthDTO,
							 Model model) {
	
		List<SecuredResourceAuthDTO> auths = securedResourceAuthDTO.getSecuredResourceAuthDTOList();
		//선택되지 않은 체크박스는 null값이므로 이를 list에서 제거해 주는 작업
		for(Iterator<SecuredResourceAuthDTO> it = auths.iterator(); it.hasNext();){
			SecuredResourceAuthDTO auth = it.next();
			
			if(auth.getAuthority() == null){
				it.remove();
			}
		}//end for

		//테스트용 출력
		for(SecuredResourceAuthDTO dto: auths){
			System.out.println("authList"+dto.getResourceCode() +" : " + dto.getAuthority());
		}
		
		adminService.modifySecuredResourceAuth(auths);
		
		//Spring security resource 재설정
		try {
			relodable.reload();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:adminPage";
	}
}
