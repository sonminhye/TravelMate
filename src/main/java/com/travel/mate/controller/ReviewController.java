/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 05. 25
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

package com.travel.mate.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.security.MyUser;
import com.travel.mate.service.ReviewService;

@Controller
public class ReviewController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "ReviewService")
	private ReviewService reviewService;
	
	/*
	 * Method	: writeReview
	 * Summary	: 작성한 리뷰를 Database에 등록하는 과정
	 * @param	: ApplyDTO(for get applyInfo)
	 * @param	: String content(for insert review)
	 * @param	: int point(for insert point)
	 * @Return	: void
	 */
	@RequestMapping(value = "/doWriteReview", method = RequestMethod.POST)
	public ModelAndView writeReview(@RequestParam("content") String content, @RequestParam("point") int point, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			// get travelCode
			String beforeUrl = request.getHeader("referer");
			int slashTravelCode = beforeUrl.lastIndexOf("/");
			int travelCode = Integer.parseInt(beforeUrl.substring(slashTravelCode + 1, beforeUrl.length()));
			
			// get userCode
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			int userCode = 0;
			
			if (principal != null && principal instanceof MyUser) {
				userCode = ((MyUser)principal).getUserCode();
			}
			
			ApplyDTO applyDto = new ApplyDTO();
			applyDto.setTravelCode(travelCode);
			applyDto.setUserCode(userCode);			
			
			reviewService.insertReview(applyDto, content, point);
			mv.setViewName("redirect:/readTravel/" + travelCode);
		}
		catch (Exception e) {
			mv.setViewName("redirect:/errorPage");
			log.error(e);
		}
		return mv;
	}
	
}
