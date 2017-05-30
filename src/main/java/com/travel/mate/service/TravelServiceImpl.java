package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.travel.mate.dao.TravelDAO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

// 서비스 선언, controller에서 resource를 통해 접근
@Service("TravelService")
public class TravelServiceImpl implements TravelService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="TravelDAO")
	private TravelDAO travelDAO;

	@Override
	public void insertTravel(TravelDTO travel) throws Exception {
			travelDAO.insertTravel(travel);	
	}

	@Override
	public void insertTravelDetail(TravelDetailDTO travelDetail) {
		travelDAO.insertTravelDetail(travelDetail);
	}

	@Override
	public void insertTravelImage(TravelImageDTO travelImage) {
		travelDAO.insertTravelImage(travelImage);
	}

	@Override
	public void insertTravelRoute(TravelRouteDTO travelRoute) {
		travelDAO.insertTravelRoute(travelRoute);
	}

	@Override
	public List<Map<String, Object>> selectTravel(Map<String, Object> map) {
		return travelDAO.selectTravel(map);
	}

	@Override
	public List<Map<String, Object>> selectTravelDetail(int code) {
		return travelDAO.selectTravelDetail(code);
	}

	@Override
	public List<Map<String, Object>> selectTravelRoute(int code) {
		return travelDAO.selectTravelRoute(code);
	}

	@Override
	public List<Map<String, Object>> scrollDown(Integer code) {
		return travelDAO.scrollDown(code);
	}
}