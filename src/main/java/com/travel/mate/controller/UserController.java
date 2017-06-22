package com.travel.mate.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travel.mate.dto.UserDTO;
import com.travel.mate.dto.UserDetailDTO;
import com.travel.mate.dto.UserLanguageDTO;
import com.travel.mate.security.MyUser;
import com.travel.mate.service.UserService;
import com.travel.mate.user.ReloadableUser;

@Controller
public class UserController {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	ReloadableUser reloadUser;
	
	@Resource(name = "UserService")
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	
	/*회원 가입*/
	@RequestMapping(value = "/doSignUp", method = RequestMethod.POST)
	public String doSignUp(@ModelAttribute @Valid UserDTO userDTO ,BindingResult result1,  
			               @ModelAttribute @Valid UserDetailDTO userDetailDTO, BindingResult result2 ,
						   @RequestParam("authority") String authority,
						   @ModelAttribute("langDTOList") UserLanguageDTO languageDTO, 
						   Model model) {

		    //userDTO에 대한 Validation 오류 검사
	        if (result1.hasErrors()) {
	            // 에러 출력
	            List<ObjectError> list = result1.getAllErrors();
	            for (ObjectError e : list) {
	                log.error(" ObjectError : " + e);
	            }
	            return "signUp";
	        }
	        //userDetailDTO에 대한 Validation 오류 검사
	        if (result2.hasErrors()) {
	            // 에러 출력
	            List<ObjectError> list = result2.getAllErrors();
	            for (ObjectError e : list) {
	                log.error(" ObjectError : " + e);
	            }
	            return "signUp";
	        }
		   

		//패스워드 암호화
		String bCryptStr = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(bCryptStr);
		
		List<UserLanguageDTO> langs = languageDTO.getLangDTOList();
		//체크박스에서 선택되지 않은 language는 null값이므로 이를 list에서 제거해 주는 작업
		for(Iterator<UserLanguageDTO> it = langs.iterator(); it.hasNext();){
			UserLanguageDTO lang = it.next();
			
			if(lang.getLanguageCode() == 0){
				it.remove();
			}//end if
		}//end for

		userService.doSignup(userDTO, userDetailDTO, authority, langs);
				
		//회원가입 후 로그인 페이지로 이동
		return "signIn"; 
	}
	
	/*로그인 페이지*/
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) {
	
		return "signIn";
	}
	
	/*회원가입 페이지*/
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
	
		return "signUp";
	}
	
	/*아이디 중복체크.
	 * request에서 얻은 id 값을 DB에서 조회하여
	 * 중복되는 칼럼 갯수를 반환받는다.
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkSignup", method = RequestMethod.POST)
	public String checkSignup(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		int rowcount = userService.checkSignup(id);
		
		return String.valueOf(rowcount);
	}
	
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(HttpServletRequest request, Model model) {
		System.out.println("myPage controller");
		
		return "myPage";
	}
	
	/*내 정보 변경 뷰*/
	@RequestMapping(value = "/myInfo", method = RequestMethod.POST)
	public String myInfo(Model model) {

		/*SecurityContextHolder 에서 계정정보 및 userCode 를 얻어옴.*/
		int userCode = getUserCode();
		UserDTO user = userService.showUser(userCode);
		UserDetailDTO userDetail = userService.showUserDetail(userCode); 
		
		model.addAttribute("user",user);
		model.addAttribute("userDetail", userDetail);
		
		return "myInfo";
	}
	
	/*내 정보 변경 액션*/
	@RequestMapping(value = "/modifyUserDetail", method = RequestMethod.POST)
	public String modifyUserDetail(@ModelAttribute UserDetailDTO userDetailDTO, Model model,  RedirectAttributes redirectAttributes) {
		
		int userCode = getUserCode();
		userDetailDTO.setUserCode(userCode);
		userService.updateUserDetail(userDetailDTO);

		return "redirect:myPage";

	}
	
	
	/*비밀번호 변경 뷰*/
	@RequestMapping(value = "/myPassword", method = RequestMethod.POST)
	public String myPassword(Model model) {

		int userCode = getUserCode();
		UserDTO user = userService.showUser(userCode);
		
		model.addAttribute("user",user);
		return "myPassword";
	}
	
	/*비밀번호 변경 액션*/
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public String modifyPassword(@ModelAttribute UserDTO userDTO,
								 @RequestParam("originalPassword") String originalPassword, 
						  		 @RequestParam("newPassword") String newPassword,
								 @RequestParam("newPasswordConfirm") String newPasswordConfirm,
								 Model model) {

		int userCode = getUserCode();
		String password = getPassword();
	
		userDTO.setUserCode(userCode);
		userDTO.setPassword(password);
		
		/*비밀번호 변경 시도 후 한번이라도 실패할 경우 fail을 true로 설정(실패 메시지 출력 위해)*/
		boolean fail = false;
		
		/*기존 비밀번호가 DB와 일치할 경우 && 새 비밀번호와 새 비밀번호 확인이 일치할 경우*/
		if(passwordEncoder.matches(originalPassword, password) && newPassword.equals(newPasswordConfirm)){
			/*새로운 패스워드 암호화*/
			String bCryptNew = passwordEncoder.encode(newPassword);
			userDTO.setPassword(bCryptNew);
			userService.updatePassword(userDTO);		
			reloadUser.reloadAuthentication(userDTO.getId()); //세션재설정

			return "redirect:myPage";
		}
		else{
			fail = true;
			model.addAttribute("fail",fail);
			return "forward:myPassword";
		}
		
	}
	
	/*세션으로부터 유저코드 얻어옴.*/
	public int getUserCode(){
		return ((MyUser)getPrincipal()).getUserCode();
	
	}
	
	/*세션으로부터 패스워드 얻어옴*/
	public String getPassword(){
		return ((MyUser)getPrincipal()).getPassword();
	}
	
	/*세션으로부터 Principal 얻어옴*/
	public Object getPrincipal(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 return auth.getPrincipal();
	}
}
