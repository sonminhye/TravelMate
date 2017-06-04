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
		
		
		return "showReview";
	}
	
	@RequestMapping(value = "/showReview", method = RequestMethod.GET)
	public String showReview(Model model) {
		
		return "showReview";
	}

	
}
