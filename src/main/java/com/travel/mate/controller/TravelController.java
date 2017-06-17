/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 05. 25
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

package com.travel.mate.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelRouteDTO;
import com.travel.mate.service.TravelService;
import com.travel.mate.service.ReviewService;

@Controller
public class TravelController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "TravelService")
	private TravelService travelService;
	
	@Resource(name = "ReviewService")
	private ReviewService reviewService;
	
	/*
	 * Method	: scrollDown
	 * Summary	: Scroll 최하단으로 갈 시 작동하는 Controller
	 * @param	: String keys(travelCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@RequestMapping(value = "/scrollDown", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> scrollDown(@RequestBody String keys) throws ParseException {
		List<Map<String, Object>> scroll = travelService.scrollDown(keys);
		return scroll;
	}
	
	/*
	 * Method	: travelList
	 * Summary	: 첫 화면 리스트 6개를 보여주는 Controller
	 * @param	: Notthing
	 * @Return	: ModelAndView
	 */
	@RequestMapping(value = "/travelList")
	public ModelAndView travelList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/travelList");
		
		List<Map<String, Object>> list = travelService.selectTravel();
		mv.addObject("list", list);
		return mv;
	}

	/*
	 * Method	: readTravel(view travel info)
	 * Summary	: 여행 정보 상세
	 * @param	: TravelDTO(for use travelInfo)
	 * @param	: PathVarible(for use url)
	 * @Return	: ModelAndView
	 */
	@RequestMapping(value = "/readTravel/{travelCode}")
	public ModelAndView readTravel(TravelDTO travelDto, @PathVariable int travelCode, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/readTravel");
		
		List<Map<String, Object>> listinfo = travelService.selectTravelDetail(travelDto);
		List<Map<String, Object>> listRoute = travelService.selectTravelRoute(travelDto);
		
		// apply or cancel button
		List<Map<String, Object>> listApply = travelService.selectTravelApply(travelDto);
		List<Map<String, Object>> listApplyCount = travelService.selectTravelApplyCount(travelDto);
		
		// writed review list
		List<Map<String, Object>> listReview = reviewService.selectReviewAll(travelDto);
		// is possible write review
		List<Map<String, Object>> listReviewWriteCheck = reviewService.selectReviewWriteCheck(travelDto);
		// is write reivew
		List<Map<String, Object>> listReviewWrite = reviewService.selectReviewWrite(travelDto);
		
		// userInfo
		List<Map<String, Object>> listUserInfo = travelService.selectUserInfo(travelDto);
		
		mv.addObject("list", listinfo);
		mv.addObject("route", listRoute);
		
		mv.addObject("applyList", listApply);
		mv.addObject("applyCount", listApplyCount);
		
		mv.addObject("reviewList", listReview);
		mv.addObject("reviewWriteCheck", listReviewWriteCheck);
		mv.addObject("reviewWrite", listReviewWrite);
		
		mv.addObject("userInfo", listUserInfo);
		
		request.setAttribute("travelCode", travelCode);
		
		return mv;
	}

	/*
	 * Method	: writeTravel
	 * Summary	: 여행 글 작성으로 이동
	 * @param	: Notthing
	 * @Return	: String
	 */
	@RequestMapping(value = "/writeTravel")
	public String writeTravel() {
		return "writeTravel";
	}
	
	/*
	 * Method	: doWrite
	 * Summary	: 작성한 여행 정보를 Database에 등록하는 과정
	 * @param	: TravelDTO(for insert travel)
	 * @param	: TravelDetailDTO(for insert travelDetail)
	 * @param	: TravelRouteDTO(for insert travelRoute)
	 * @param	: MultipartHttpServletRequest(for insert travelImage)
	 * @Return	: ModelAndview
	 */
	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	public ModelAndView doWrite(@ModelAttribute TravelDTO travelDto,
			@ModelAttribute TravelDetailDTO travelDetailDto,
			@ModelAttribute("trlist") TravelRouteDTO travelRouteDto,
			MultipartHttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			travelService.insertTravel(travelDto, travelDetailDto, travelRouteDto, request);
			mv.setViewName("redirect:/travelList");
		}
		catch (Exception e) {
			mv.setViewName("redirect:/errorPage");
			log.error(e);
		}
		return mv;
	}

	/*
	 * Method	: doApply
	 * Summary	: 여행 신청하면 Database에 등록하는 과정
	 * @param	: ApplyDTO(for insert apply)
	 * @Return	: ModelAndView
	 */
	@RequestMapping(value = "/doApply", method = RequestMethod.POST)
	public ModelAndView doApply(@ModelAttribute ApplyDTO applyDto) {
		ModelAndView mv = new ModelAndView();
		try {
			travelService.insertTravelApply(applyDto);
			mv.setViewName("redirect:/travelList");
		}
		catch (Exception e) {
			mv.setViewName("redirect:/errorPage");
			log.error(e);
		}
		return mv;
	}

	/*
	 * Method	: doCancel
	 * Summary	: 여행 신청 취소하면 Database에서 삭제 되는 과정
	 * @param	: ApplyDTO(for delete apply)
	 * @Return	: ModelAndView
	 */
	@RequestMapping(value = "/doCancel", method = RequestMethod.POST)
	public ModelAndView doCancel(@ModelAttribute ApplyDTO applyDto) {
		ModelAndView mv = new ModelAndView();
		try {
			travelService.deleteTravelApply(applyDto);
			mv.setViewName("redirect:/travelList");
		}
		catch (Exception e) {
			mv.setViewName("redirect:/errorPage");
			log.error(e);
		}
		return mv;
	}
	
	/*
	 * Method	: goErrorPage
	 * Summary	: 예외가 발생할 경우 이동하는 페이지
	 * @param	: Notthing
	 * @Return	: String
	 */
	@RequestMapping(value = "/errorPage")
	public String goErrorPage() {	
		return "errorPage";
	}
	
}