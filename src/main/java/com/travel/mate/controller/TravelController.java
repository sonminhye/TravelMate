package com.travel.mate.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;
import com.travel.mate.service.TravelService;

@Controller
public class TravelController {

	@Resource(name = "TravelService")
	private TravelService travelService;
	
	// ajax scroll event
	@RequestMapping(value = "/scrollDown", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> scrollDownPOST(@RequestBody String keys) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		// JSON 데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = (JSONObject) jsonParser.parse(keys);
		int code = Integer.parseInt((String) jsonObject.get("travelCode"));
		List<Map<String, Object>> scroll = travelService.scrollDown(code - 1);
		return scroll;
	}
	
	// 첫 화면 6개
	@RequestMapping(value = "/travelList")
	public ModelAndView travelList(Map<String, Object> map) {
		// view Setting
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/travelList");
		
		List<Map<String, Object>> list = travelService.selectTravel(map);
		mv.addObject("list", list);
		return mv;
	}

	// 리스트 이미지를 클릭하여 해당 글에 대한 정보 읽기
	@RequestMapping(value = "/readTravel")
	public ModelAndView readTravel(TravelDTO travelDto) {
		// view Setting
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/readTravel");
		
		List<Map<String, Object>> listinfo = travelService.selectTravelDetail(travelDto);
		List<Map<String, Object>> listRoute = travelService.selectTravelRoute(travelDto);
		
		// 신청 버튼 or 취소버튼을 위함
		List<Map<String, Object>> listApply = travelService.selectTravelApply(travelDto);
		List<Map<String, Object>> listApplyCount = travelService.selectTravelApplyCount(travelDto);
		
		mv.addObject("list", listinfo);
		mv.addObject("route", listRoute);
		
		mv.addObject("applyList", listApply);
		mv.addObject("applyCount", listApplyCount);
		return mv;
	}

	// 여행 글쓰기를 눌렀을 때
	@RequestMapping(value = "/writeTravel")
	public String writeTravel() {
		// writeTravel.jsp로 이동
		return "writeTravel";
	}
	
	// 글쓰기를 등록할 때
	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	// @ModelAttribute("jsp 파일에서 name="list[idx].field" 일때의 list 값") / DTO 객체에 담음 / 사용할 변수명
	public ModelAndView doWrite(@ModelAttribute("tlist") TravelDTO travelDto,
			@ModelAttribute("tdlist") TravelDetailDTO travelDetailDto,
			@ModelAttribute("tilist") TravelImageDTO travelImageDto,
			@ModelAttribute("trlist") TravelRouteDTO travelRouteDto) throws Exception {

		// view Setting
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/travelList");

		travelService.insertTravel(travelDto, travelDetailDto, travelImageDto, travelRouteDto);
		return mv;
	}

	@RequestMapping(value = "/doApply", method = RequestMethod.POST)
	public ModelAndView doApply(@ModelAttribute("alist") ApplyDTO applyDto) {
		// view Setting
		ModelAndView mv = new ModelAndView();
		// 신청 후 mypage로 던짐
		// 일단 테스트용으로 main페이지
		mv.setViewName("/main");
		
		travelService.insertTravelApply(applyDto);
		
		return mv;
	}

	@RequestMapping(value = "/doCancel", method = RequestMethod.POST)
	public ModelAndView doCancel(@ModelAttribute("alist") ApplyDTO applyDto) {
		// view Setting
		ModelAndView mv = new ModelAndView();
		// 신청 후 mypage로 던짐
		// 일단 테스트용으로 main페이지
		mv.setViewName("/main");
		
		travelService.deleteTravelApply(applyDto);
		return mv;
	}

}
