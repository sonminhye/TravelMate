package com.travel.mate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;
import com.travel.mate.dto.UserDTO;
import com.travel.mate.service.TravelService;
import com.travel.mate.service.TravelServiceImpl;

@Controller
public class TravelController extends HandlerInterceptorAdapter{

	@RequestMapping(value = "/travelList")
	public String travelList(Model model) {
		System.out.println("travelList");
		return "travelList";
	}
	
	@RequestMapping(value = "/readTravel", method = RequestMethod.GET)
	public String readTravel(Model model) {
		System.out.println("readTravel");
		return "readTravel";
	}
	
	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	// @ModelAttribute("jsp 파일에서 name="list[idx].field" 일때의 list 값") / DTO 객체에 담음 / 사용할 변수명
	public ModelAndView doWrite(
			@ModelAttribute("travel") TravelDTO travelDto,
			@ModelAttribute("travelDetail") TravelDetailDTO travelDetailDto,
			@ModelAttribute("travelImage") TravelImageDTO travelImageDto,
			@ModelAttribute("travelRouteList") TravelRouteDTO travelRouteDto) {
		// 좌표 여러개(리스트) 얻은 후
		/* DTO의 method를 콜하는 것에서 주의!
		 * jsp파일의 list명과 DTO 내의 객체이름이 같아야함
		 * */
		List<TravelDTO> travels = travelDto.getTravels();
		List<TravelDetailDTO> travelDetails = travelDetailDto.getTravelDetails();
		List<TravelImageDTO> travelImages = travelImageDto.getTravelImages();
		List<TravelRouteDTO> routes = travelRouteDto.getTravelRouteList();
		
		/* 	
		List<UserDTO> list = userService.showList();
		model.addAttribute("list", list);
		return "testList";
		 */
		
		if (null != travels && travels.size() > 0) {
			// travel table에 넣음
			for (TravelDTO travel : travels) {
				System.out.println("travel : " + travel.getTitle() + " " + travel.getContent());
				System.out.println("====");

				System.out.println("db test start");
				
				TravelDTO dto = new TravelDTO();
				
				dto.setTitle(travel.getTitle());
				dto.setContent(travel.getContent());
				
				List<TravelDTO> list = new ArrayList<TravelDTO>();
				list.add(dto);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				
//				TravelServiceImpl ts = new TravelServiceImpl();
//				ts.insertTravel(travels, travelDetails, travelImages, routes);
				System.out.println("db test end");
			}
		}
		if (null != travelDetails && travelDetails.size() > 0) {
			// traveldetail table에 넣음
			for (TravelDetailDTO travelDetail : travelDetails) {
				System.out.println("travelDetail : " + travelDetail.getStartDate() + " " + travelDetail.getStartTime());
				System.out.println(travelDetail.getEndDate() + " " + travelDetail.getEndTime());
				System.out.println(travelDetail.getMinPeople() + " " + travelDetail.getMaxPeople());
				System.out.println("====");
			}
		}
		if (null != travelImages && travelImages.size() > 0) {
			// travelimage table에 넣음
			for (TravelImageDTO travelImage : travelImages) {
				System.out.println("travelImage : " + travelImage.getImage());
				System.out.println("====");
			}
		}
		// 좌표 리스트에 있는 것을 모두 출력
		if (null != routes && routes.size() > 0) {
			// 변수 : 배열의 for문
			for (TravelRouteDTO route : routes) {
				System.out.println(route.getLat() + " " + route.getLng() + " " + route.getLocation());
			}
		}
		else {
			// 장소 선택을 하지 않아 리스트가 비어있는 경우
			System.out.println("장소 선택 x");
		}
		// ModelAndView(Object view, String modelName, Object modelObject) 렌더링 할 View와 View에 전달할 객체의 이름과 값
		// ModelAndView(String viewName, String modelName, Object modelObject) : ViewResolver에 전달할 View 이름과 View에 전달할 객체의 이름과 값
		return new ModelAndView("travelList", "writeTravel", travelRouteDto);
	}
	
	@RequestMapping(value = "/writeTravel")
	public String writeTravel(Model model) {
		// writeTravel.jsp로 이동
		return "writeTravel";
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
