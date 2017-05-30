package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

public interface TravelService {
	
	void insertTravel(TravelDTO travel) throws Exception;

	void insertTravelDetail(TravelDetailDTO travelDetail);

	void insertTravelImage(TravelImageDTO travelImage);
	
	void insertTravelRoute(TravelRouteDTO travelRoute);

	List<Map<String, Object>> selectTravel(Map<String, Object> map);

	List<Map<String, Object>> selectTravelDetail(int code);

	List<Map<String, Object>> selectTravelRoute(int code);

}
