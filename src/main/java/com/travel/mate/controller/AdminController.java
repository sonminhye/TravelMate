package com.travel.mate.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.mate.dto.AuthDTO;
import com.travel.mate.dto.LanguageDTO;
import com.travel.mate.dto.SecuredResourceAuthDTO;
import com.travel.mate.dto.SecuredResourceDTO;
import com.travel.mate.dto.UserAuthDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.security.service.ReloadableFilterInvocationSecurityMetadataSource;
import com.travel.mate.service.AdminService;

@Controller
public class AdminController {

	@Resource(name = "AdminService")
	private AdminService adminService;
	
	/*spring security의 권한정보를 재설정하는 reload 메소드를 호출하기 위해 */
	@Autowired
	ReloadableFilterInvocationSecurityMetadataSource relodable;
	
	/*목록불러오기*/
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String adminPage(Locale locale, Model model) {
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
	
	
	/*접근권한 없을 때*/
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {		
		return "accessDenied";
	}
	
	/*회원권한변경*/
	@RequestMapping(value = "/modifyUserAuth", method = RequestMethod.POST)
	public String modifyUserAuth(@ModelAttribute UserAuthDTO userAuthDTO,
							 Model model) {
		
		adminService.updateUserAuth(userAuthDTO);
		
		return "redirect:adminPage";
	}

	/*url별 접근권한 변경*/
	@RequestMapping(value = "/modifySecuredResourceAuth", method = RequestMethod.POST)
	public String modifySecuredResourceAuth(@ModelAttribute("securedResourceAuthDTOList") SecuredResourceAuthDTO securedResourceAuthDTO,
											@ModelAttribute SecuredResourceDTO securedResourceDTO,
							 				Model model) {
	
		List<SecuredResourceAuthDTO> auths = securedResourceAuthDTO.getSecuredResourceAuthDTOList();
	
		/*선택되지 않은 체크박스는 null값이므로 이를 list에서 제거해 주는 작업*/
		for(Iterator<SecuredResourceAuthDTO> it = auths.iterator(); it.hasNext();){
			SecuredResourceAuthDTO auth = it.next();
			
			if(auth.getAuthority() == null){
				it.remove();
			}		
		}//end for
		
		/*auths가 비어있지 않을 때만(체크박스가 최소 한개 선택되었을 때) 쿼리를 실행*/ 
		if(!auths.isEmpty()){
			adminService.modifySecuredResourceAuth(auths);
		}
		
		/*update sortOrder*/ 
		adminService.updateSecuredResource(securedResourceDTO);
		
		/*Spring security resource 재설정(reload 메소드 호출)*/
		try {
			relodable.reload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:adminPage";
	}
}
