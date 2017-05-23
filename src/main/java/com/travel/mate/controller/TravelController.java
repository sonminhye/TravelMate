package com.travel.mate.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TravelController {

	@RequestMapping(value = "/showTravelForm", method = RequestMethod.GET)
	public String showTravelForm(Model model) {
		System.out.println("showTravelForm");
		return "showTravelForm";
	}
	

	@RequestMapping(value = "/doWriteTravelForm")
	public String doWriteTravel(Model model) {
		System.out.println("doWriteTravelForm");
		return "showTravelContent";
	}
	

	@RequestMapping(value = "/showTravelContent", method = RequestMethod.GET)
	public String showTravelContent(Model model) {
		
		//���� ���� ������ �� �� �ֵ��� ������ ��ƿ��� �Լ�
		
		return "showTravelContent";
	}
	

	@RequestMapping(value = "/joinTravel", method = RequestMethod.POST)
	public String joinTravel(Model model) {
		//�����ڰ� ���� ������û
		
		return "showTravel";
	}

	@RequestMapping(value = "/cancelTravel", method = RequestMethod.POST)
	public String cancelTravel(Model model) {
		//�����ڰ� ���� ��ҽ�û
		
		return "showTravelContent";
	}
	
}
