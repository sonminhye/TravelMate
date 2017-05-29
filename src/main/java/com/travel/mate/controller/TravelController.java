package com.travel.mate.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;
import com.travel.mate.service.TravelService;

@Controller
public class TravelController extends HandlerInterceptorAdapter {

	@Resource(name = "TravelService")
	private TravelService travelService;

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

	@RequestMapping(value = "/readTravel")
	public ModelAndView readTravel(TravelDTO travelDto) {
		System.out.println("readTravel");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/readTravel");
		// System.out.println(travelDetailDto.getTravelCode());
		// int travelCode = travelDetailDto.getTravelCode();

		System.out.println("dto :" + travelDto);

		System.out.println("dto.code: " + travelDto.getTravelCode());
		int code = travelDto.getTravelCode();

		List<Map<String, Object>> list = travelService.selectTravelDetail(code);
		mv.addObject("list", list);

		System.out.println("list 출력");

		System.out.println(list);
		return mv;
	}

	@RequestMapping(value = "/writeTravel")
	public String writeTravel(Model model) {
		// writeTravel.jsp로 이동
		return "writeTravel";
	}

	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	// @ModelAttribute("jsp 파일에서 name="list[idx].field" 일때의 list 값") / DTO 객체에
	// 담음 / 사용할 변수명
	public ModelAndView doWrite(@ModelAttribute("tlist") TravelDTO travelDto,
			@ModelAttribute("tdlist") TravelDetailDTO travelDetailDto,
			@ModelAttribute("tilist") TravelImageDTO travelImageDto,
			@ModelAttribute("trlist") TravelRouteDTO travelRouteDto) throws Exception {
		// 좌표 여러개(리스트) 얻은 후
		/*
		 * DTO의 method를 콜하는 것에서 주의! jsp파일의 list명과 DTO 내의 객체이름이 같아야함
		 */
		System.out.println(travelRouteDto);
		List<TravelDTO> travels = travelDto.getTlist();
		List<TravelDetailDTO> travelDetails = travelDetailDto.getTdlist();
		List<TravelImageDTO> travelImages = travelImageDto.getTilist();
		List<TravelRouteDTO> routes = travelRouteDto.getTrlist();

		// 다시 표시될 페이지 주소 세팅
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/travelList");

		if (null != travels && travels.size() > 0) {
			// travel table에 넣음
			for (TravelDTO travel : travels) {
				System.out.println("travel : " + travel.getTitle() + " " + travel.getContent());
				System.out.println("====");
				System.out.println("insert : travel");
				travelService.insertTravel(travel);
				System.out.println("insert end");
			}
		}
		if (null != travelDetails && travelDetails.size() > 0) {
			// traveldetail table에 넣음
			for (TravelDetailDTO travelDetail : travelDetails) {
				System.out.println("travelDetail : " + travelDetail.getStartDate() + " " + travelDetail.getStartTime());
				System.out.println(travelDetail.getEndDate() + " " + travelDetail.getEndTime());
				System.out.println(travelDetail.getMinPeople() + " " + travelDetail.getMaxPeople());
				System.out.println("====");
				System.out.println("insert : detail");
				travelService.insertTravelDetail(travelDetail);
				System.out.println("insert end");
			}
		}
		if (null != travelImages && travelImages.size() > 0) {
			// travelimage table에 넣음
			for (TravelImageDTO travelImage : travelImages) {
				System.out.println("travelImage : " + travelImage.getImage());
				System.out.println("====");
				System.out.println("insert : image");
				travelService.insertTravelImage(travelImage);
				System.out.println("insert end");
			}
		}
		// 좌표 리스트에 있는 것을 모두 출력
		if (null != routes && routes.size() > 0) {
			// 변수 : 배열의 for문
			for (TravelRouteDTO route : routes) {
				System.out.println(route.getLat() + " " + route.getLng() + " " + route.getLocation());
				System.out.println("====");
				System.out.println("insert: routes");
				travelService.insertTravelRoute(route);
			}
		} else {
			// 장소 선택을 하지 않아 리스트가 비어있는 경우
			System.out.println("장소 선택 x");
		}
		// ModelAndView(Object view, String modelName, Object modelObject) 렌더링 할
		// View와 View에 전달할 객체의 이름과 값
		// ModelAndView(String viewName, String modelName, Object modelObject) :
		// ViewResolver에 전달할 View 이름과 View에 전달할 객체의 이름과 값
		return mv;
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
