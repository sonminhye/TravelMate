package com.travel.mate.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {
	
	

	@RequestMapping(value = "/showReviewForm", method = RequestMethod.GET)
	public String showReviewForm(Model model) {
		
		return "showReviewForm";
	}
	
	@RequestMapping(value = "/doWriteReview", method = RequestMethod.POST)
	public String showTravelForm(Model model) {
		
		//사용자가 별점 및 리뷰를 작성
		//결과적으로 리뷰를 한 후에는, 자신이 단 리뷰를 확인할 수 있어야 하므로 showReview 페이지로 이동
		//리턴 값을 어떻게 해줄지는 나중에 생각...
		
		return "showReview";
	}
	
	@RequestMapping(value = "/showReview", method = RequestMethod.GET)
	public String showReview(Model model) {
		
		return "showReview";
	}

	
}
