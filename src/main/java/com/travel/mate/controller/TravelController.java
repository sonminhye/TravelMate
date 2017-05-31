package com.travel.mate.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;
import com.travel.mate.service.TravelService;

@Controller
public class TravelController extends HandlerInterceptorAdapter {

	@Resource(name = "TravelService")
	private TravelService travelService;
	
	// ajax scroll event
	@RequestMapping(value = "/scrollDown", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> scrollDownPOST(@RequestBody String keys) throws ParseException{
		System.out.println(keys);
		JSONParser jsonParser = new JSONParser();
		//JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = (JSONObject) jsonParser.parse(keys);
		int code = Integer.parseInt((String) jsonObject.get("travelCode"));
		List<Map<String, Object>> scroll = travelService.scrollDown(code - 1);
		return scroll;
	}
	
	// 첫 화면 6개
	@RequestMapping(value = "/travelList")
	public ModelAndView travelList(Map<String, Object> map) {
		System.out.println("travelList");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/travelList");
		List<Map<String, Object>> list = travelService.selectTravel(map);
		System.out.println(list);
		mv.addObject("list", list);
		return mv;
	}

	// 리스트 이미지를 클릭하여 해당 글에 대한 정보 읽기
	@RequestMapping(value = "/readTravel")
	public ModelAndView readTravel(TravelDTO travelDto) {
		System.out.println("readTravel");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/readTravel");
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		
		// 유저 코드
		int ucode = travelDto.getUserCode();
		System.out.println("user code : " + ucode);
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		
		List<Map<String, Object>> listinfo = travelService.selectTravelDetail(tcode);
		List<Map<String, Object>> listRoute = travelService.selectTravelRoute(tcode);
		
		// 신청 버튼 or 취소버튼을 위함
		List<Map<String, Object>> listApply = travelService.selectTravelApply(applyDto);
		
		mv.addObject("list", listinfo);
		mv.addObject("route", listRoute);
		
		mv.addObject("applyList", listApply);
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
		// 좌표 여러개(리스트) 얻은 후
		/*
		 * DTO의 method를 콜하는 것에서 주의! jsp파일의 list명과 DTO 내의 객체이름이 같아야함
		 */
		
		List<TravelDTO> travels = travelDto.getTlist();
		List<TravelDetailDTO> travelDetails = travelDetailDto.getTdlist();
		List<TravelImageDTO> travelImages = travelImageDto.getTilist();
		List<TravelRouteDTO> routes = travelRouteDto.getTrlist();

		// travelCode
		int travelCode = 0;
		
		// 다시 표시될 페이지 주소 세팅
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/travelList");

		if ((null != travels && travels.size() > 0)
				&& (null != travelDetails && travelDetails.size() > 0)
				&& (null != travelImages && travelImages.size() > 0)) {
			// travel table에 넣음
			for (TravelDTO travel : travels) {
				System.out.println("insert : travel");
				travelService.insertTravel(travel);
				// travelCode general
				travelCode = travel.getTravelCode();
				System.out.println("insert end");
			}
			// traveldetail table에 넣음
			for (TravelDetailDTO travelDetail : travelDetails) {
				System.out.println("====");
				System.out.println("insert : detail");
				// travelCode setting
				travelDetail.setTravelCode(travelCode);
				travelService.insertTravelDetail(travelDetail);
				System.out.println(travelDetail.getTravelCode());
				System.out.println("insert end");
			}
			// travelimage table에 넣음
			for (TravelImageDTO travelImage : travelImages) {
				System.out.println("====");
				System.out.println("insert : image");
				// travelCode setting
				travelImage.setTravelCode(travelCode);
				travelService.insertTravelImage(travelImage);
				System.out.println("insert end");
			}
		}
		// 장소 지정을 하나 이상 했다면 route table에 insert
		if (null != routes && routes.size() > 0) {
			for (TravelRouteDTO route : routes) {
				System.out.println("====");
				System.out.println("insert: routes");
				// travelCode setting
				route.setTravelCode(travelCode);
				travelService.insertTravelRoute(route);
				System.out.println("insert end");
			}
		} 
		else {
			// 장소 선택을 하지 않아 리스트가 비어있는 경우, insert 하지 않음
			System.out.println("장소 선택 x");
		}
		return mv;
	}

	@RequestMapping(value = "/doApply", method = RequestMethod.POST)
	public ModelAndView doApply(@ModelAttribute("alist") ApplyDTO applylDto) {
		List<ApplyDTO> applys = applylDto.getAlist();
		ModelAndView mv = new ModelAndView();
		// 신청 후 mypage로 던짐
		// 일단 테스트용으로 main페이지
		mv.setViewName("/main");
		
		if ((null != applys && applys.size() > 0)) {
			for (ApplyDTO apply : applys) {
				System.out.println("apply insert ready");
				travelService.insertTravelApply(apply);
				System.out.println("apply insert complete");
			}
		}
		
		return mv;
	}

	@RequestMapping(value = "/doCancel", method = RequestMethod.POST)
	public ModelAndView doCancel(@ModelAttribute("alist") ApplyDTO applylDto) {
		List<ApplyDTO> applys = applylDto.getAlist();
		ModelAndView mv = new ModelAndView();
		// 신청 후 mypage로 던짐
		// 일단 테스트용으로 main페이지
		mv.setViewName("/main");
		
		if ((null != applys && applys.size() > 0)) {
			for (ApplyDTO apply : applys) {
				System.out.println("apply delete ready");
				travelService.deleteTravelApply(apply);
				System.out.println("apply delete complete");
			}
		}
		return mv;
	}

}
