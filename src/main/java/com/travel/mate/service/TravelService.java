package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelRouteDTO;

public interface TravelService {

	void insertTravel(TravelDTO travelDto, TravelDetailDTO travelDetailDto, TravelRouteDTO travelRouteDto, MultipartHttpServletRequest request) throws Exception;

	List<Map<String, Object>> selectTravel();
	List<Map<String, Object>> selectTravelDetail(TravelDTO travelDto);
	List<Map<String, Object>> selectTravelRoute(TravelDTO travelDto);

	List<Map<String, Object>> scrollDown(String keys) throws ParseException;

	void insertTravelApply(ApplyDTO applyDto) throws Exception;

	void deleteTravelApply(ApplyDTO applyDto) throws Exception;

	List<Map<String, Object>> selectTravelApply(TravelDTO travelDto);
	List<Map<String, Object>> selectTravelApplyCount(TravelDTO travelDto);

	List<Map<String, Object>> selectUserInfo(TravelDTO travelDto);
	
}