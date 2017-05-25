package com.travel.mate.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TravelController {

	@RequestMapping(value = "/travelList", method = RequestMethod.GET)
	public String travelList(Model model) {
		System.out.println("travelList");
		return "travelList";
	}
	
	@RequestMapping(value = "/showTravelForm", method = RequestMethod.GET)
	public String showTravelForm(Model model) {
		System.out.println("showTravelForm");
		return "showTravelForm";
	}
	

	@RequestMapping(value = "/writeTravel")
	public String doWriteTravel(Model model) {
		System.out.println("writeTravel");
		return "writeTravel";
	}
	

	@RequestMapping(value = "/showTravelContent", method = RequestMethod.GET)
	public String showTravelContent(Model model) {
		
		
		
		return "showTravelContent";
	}
	

	@RequestMapping(value = "/joinTravel", method = RequestMethod.POST)
	public String joinTravel(Model model) {
		
		
		return "showTravel";
	}

	@RequestMapping(value = "/cancelTravel", method = RequestMethod.POST)
	public String cancelTravel(Model model) {
		
		
		return "showTravelContent";
	}
	
}
