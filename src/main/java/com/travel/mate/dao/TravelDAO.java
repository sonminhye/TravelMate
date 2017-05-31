package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

// class를 Repository에 등록함으로써 bean으로 사용가능하게 한다
@Repository("TravelDAO")
public class TravelDAO extends AbstractDAO{

	public void insertTravel(TravelDTO travel) {
		System.out.println("DAO Call : insertTravel..");
		insert("travel.insertTravel", travel);
	}

	public void insertTravelDetail(TravelDetailDTO travelDetail) {
		System.out.println("DAO Call : insertTravelDetail..");
		insert("travel.insertTravelDetail", travelDetail);
	}

	public void insertTravelImage(TravelImageDTO travelImage) {
		System.out.println("DAO Call : insertTravelImage..");
		insert("travel.insertTravelImage", travelImage);
	}

	public void insertTravelRoute(TravelRouteDTO travelRoute) {
		System.out.println("DAO Call : insertTravelRoute..");
		insert("travel.insertTravelRoute", travelRoute);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravel(Map<String, Object> map) {
		System.out.println("DAO Call : selectTravel..");
		return (List<Map<String, Object>>)selectList("travel.selectTravel", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelDetail(int code) {
		System.out.println("DAO Call : selectTravelDetail..");
		return (List<Map<String, Object>>)selectList("travel.selectTravelDetail", code);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelRoute(int code) {
		System.out.println("DAO Call : selectTravelRoute..");
		return (List<Map<String, Object>>)selectList("travel.selectTravelRoute", code);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> scrollDown(Integer code) {
		System.out.println("DAO Call : scrollDown..");
		return (List<Map<String, Object>>)selectList("travel.selectTravelScroll", code);
	}

	public void insertTravelApply(ApplyDTO apply) {
		System.out.println("DAO Call : insertTravelApply..");
		insert("travel.insertTravelApply", apply);
	}

	public List<Map<String, Object>> selectTravelApply(ApplyDTO applyDto) {
		System.out.println("DAO Call : selectTravelApply..");
		return (List<Map<String, Object>>)selectList("travel.selectTravelApply", applyDto);
	}

	public List<Map<String, Object>> selectTravelApplyCount(ApplyDTO applyDto) {
		System.out.println("DAO Call : selectTravelApplyCount..");
		return (List<Map<String, Object>>)selectList("travel.selectTravelApplyCount", applyDto);
	}

	public void deleteTravelApply(ApplyDTO apply) {
		System.out.println("DAO Call : deleteTravelApply..");
		delete("travel.deleteTravelApply", apply);
		
	}

}
