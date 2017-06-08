package com.travel.mate.controller;

import javax.annotation.Resource;

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
	
	@Resource(name = "ReviewService")
	private ReviewService reviewService;
	
	@RequestMapping(value = "/doWriteReview", method = RequestMethod.POST)
	public ModelAndView writeReview(@ModelAttribute("alist") ApplyDTO applyDto, @RequestParam("content") String content, @RequestParam("point") int point) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/main");
		
		reviewService.insertReview(applyDto, content, point);
		
		return mv;
	}

}
