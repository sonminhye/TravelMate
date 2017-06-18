package com.travel.mate.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.travel.mate.event.UserEvent;
import com.travel.mate.security.MyUser;
import com.travel.mate.service.UserService;

@Controller
public class UserController {
	Logger log = Logger.getLogger(this.getClass());

	private final ApplicationEventPublisher publisher;
	
	
	@Resource(name = "UserService")
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/doSignUp", method = RequestMethod.POST)
	public String doSignUp(@ModelAttribute @Valid UserDTO userDTO ,BindingResult result1,  
			               @ModelAttribute @Valid UserDetailDTO userDetailDTO, BindingResult result2 ,
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
	public String myPage(Model model) {
		System.out.println("myPage controller");

		return "myPage";
	}
	
	
	@RequestMapping(value = "/myInfo", method = RequestMethod.POST)
	public String myInfo(@RequestParam("userCode") int userCode, Model model) {
		System.out.println("myPage controller");

		UserDTO user = userService.showUser(userCode);
		UserDetailDTO userDetail = userService.showUserDetail(userCode); 
		
		model.addAttribute("user",user);
		model.addAttribute("userDetail", userDetail);
		
		return "myInfo";
	}
	
	
	@RequestMapping(value = "/modifyUserDetail", method = RequestMethod.POST)
	public String modifyUserDetail(@ModelAttribute UserDetailDTO userDetailDTO, Model model) {
		System.out.println("modifyUserDetail controller");
		/*보안을 위해 서버단에서 세션을 통해 userCode를 얻음*/
		int userCode = 0;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();

		if(principal != null && principal instanceof MyUser){
			userCode = ((MyUser)principal).getUserCode();
		}
		userDetailDTO.setUserCode(userCode);
		userService.updateUserDetail(userDetailDTO);
		model.addAttribute("userCode", userDetailDTO.getUserCode());
		
		publisher.publishEvent(new UserEvent(this, userDetailDTO.getName()));
		return "myPage";
	}
	
	/*비밀번호 변경 뷰*/
	@RequestMapping(value = "/myPassword", method = RequestMethod.POST)
	public String myPassword(@RequestParam("userCode") int userCode, Model model) {
		System.out.println("myPassword controller");
		
		UserDTO user = userService.showUser(userCode);
		
		model.addAttribute("user",user);
		return "myPassword";
	}
	
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public String modifyPassword(@ModelAttribute UserDTO userDTO,
								 @RequestParam("originalPassword") String originalPassword, 
						  		 @RequestParam("newPassword") String newPassword,
								 @RequestParam("newPasswordConfirm") String newPasswordConfirm,
								 Model model) {
		System.out.println("modifyPassword controller");
		/*보안을 위해 서버단에서 세션을 통해 정보를 얻음*/
		int userCode = 0;
		String password = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();

		if(principal != null && principal instanceof MyUser){
			userCode = ((MyUser)principal).getUserCode();
			password = ((MyUser)principal).getPassword();
		}
		
		userDTO.setUserCode(userCode);
		userDTO.setPassword(password);
		
		/*비밀번호 변경 시도 후 한번이라도 실패할 경우 fail을 true로 설정(실패 메시지 출력 위해)*/
		boolean fail = false;
		
		/*기존 비밀번호가 DB와 일치할 경우 && 새 비밀번호와 새 비밀번호 확인이 일치할 경우*/
		if(passwordEncoder.matches(originalPassword, userDTO.getPassword()) && newPassword.equals(newPasswordConfirm)){
			/*새로운 패스워드 암호화*/
			String bCryptNew = passwordEncoder.encode(newPassword);
			userDTO.setPassword(bCryptNew);
			userService.updatePassword(userDTO);			
			return "myPage";
		}
		else{
			fail = true;
			model.addAttribute("fail",fail);
			model.addAttribute("userCode", userDTO.getUserCode());
			return "forward:/myPassword";
		}
		
	}
}
