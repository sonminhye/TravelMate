package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

// class를 Repository에 등록함으로써 bean으로 사용가능하게 한다
@Repository("TravelDAO")
public class TravelDAO extends AbstractDAO {

	public void insertTravel(TravelDTO travel) {
		insert("travel.insertTravel", travel);
	}

	public void insertTravelDetail(TravelDetailDTO travelDetail) {
		insert("travel.insertTravelDetail", travelDetail);
	}

	public void insertTravelImage(TravelImageDTO travelImage) {
		insert("travel.insertTravelImage", travelImage);
	}

	public void insertTravelRoute(TravelRouteDTO travelRoute) {
		insert("travel.insertTravelRoute", travelRoute);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravel(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("travel.selectTravel", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelDetail(int code) {
		return (List<Map<String, Object>>)selectList("travel.selectTravelDetail", code);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelRoute(int code) {
		return (List<Map<String, Object>>)selectList("travel.selectTravelRoute", code);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> scrollDown(Integer code) {
		return (List<Map<String, Object>>)selectList("travel.selectTravelScroll", code);
	}

	public void insertTravelApply(ApplyDTO apply) {
		insert("travel.insertTravelApply", apply);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelApply(ApplyDTO applyDto) {
		return (List<Map<String, Object>>)selectList("travel.selectTravelApply", applyDto);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelApplyCount(ApplyDTO applyDto) {
		return (List<Map<String, Object>>)selectList("travel.selectTravelApplyCount", applyDto);
	}

	public void deleteTravelApply(ApplyDTO apply) {
		delete("travel.deleteTravelApply", apply);
	}

}
