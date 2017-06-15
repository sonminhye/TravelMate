package com.travel.mate.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.service.UserService;

@Controller
public class UserController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	//TEST용//////////////
	@RequestMapping(value = "/testList", method = RequestMethod.GET)
	public String showList(Model model) {
		System.out.println("list()");
		
		List<UserDTO> list = userService.showList();
		model.addAttribute("list", list);
		return "testList";

	}
	
	@RequestMapping(value = "/doSignUp", method = RequestMethod.POST)
	public String doSignUp(@ModelAttribute @Valid UserDTO userDTO ,BindingResult result1,  @ModelAttribute @Valid UserDetailDTO userDetailDTO, BindingResult result2 ,
						 @RequestParam("authority") String authority,
						 @ModelAttribute("langDTOList") UserLanguageDTO languageDTO, 
						  Model model) {
		System.out.println("signup controller");

			//아이디중복체크해주기
	        // Validation 오류 발생시 
	        if (result1.hasErrors()) {
	            // 에러 출력
	            List<ObjectError> list = result1.getAllErrors();
	            for (ObjectError e : list) {
	                log.error(" ObjectError : " + e);
	            }
	            return "signUp";
	        }
	        if (result2.hasErrors()) {
	            // 에러 출력
	            List<ObjectError> list = result2.getAllErrors();
	            for (ObjectError e : list) {
	                log.error(" ObjectError : " + e);
	            }
	            return "signUp";
	        }
		   


		//암호화
		String bCryptStr = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(bCryptStr);
		
		List<UserLanguageDTO> langs = languageDTO.getLangDTOList();
		//선택되지 않은 언어는 null값이므로 이를 list에서 제거해 주는 작업
		for(Iterator<UserLanguageDTO> it = langs.iterator(); it.hasNext();){
			UserLanguageDTO lang = it.next();
			
			if(lang.getLanguageCode() == 0){
				it.remove();
			}
		}//end for
		
		///확인용 값 출력//
		System.out.println(userDTO.toString());
		System.out.println(userDetailDTO.toString());
		for(UserLanguageDTO dto: langs){
			System.out.println("langList"+dto.getUserCode() +" : " + dto.getLanguageCode());
		}
		
		userService.doSignup(userDTO, userDetailDTO, authority, langs);
				
		return "signIn"; //회원가입 후 로그인 페이지로
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) {
	
		return "signIn";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
	
		return "signUp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkSignup", method = RequestMethod.POST)
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
