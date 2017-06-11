package com.travel.mate.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.service.ReviewService;

@Controller
public class ReviewController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "ReviewService")
	private ReviewService reviewService;
	
	// 리뷰 작성 요청
	@RequestMapping(value = "/doWriteReview", method = RequestMethod.POST)
	public ModelAndView writeReview(@ModelAttribute("alist") ApplyDTO applyDto, @RequestParam("content") String content, @RequestParam("point") int point) {
		ModelAndView mv = new ModelAndView();
		
		try {
			reviewService.insertReview(applyDto, content, point);
			mv.setViewName("redirect:/travelList");
		}
		catch (Exception e) {
			mv.setViewName("redirect:/errorPage");
			log.error(e);
		}
		return mv;
	}

}
