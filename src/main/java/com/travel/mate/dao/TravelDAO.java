package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

// class를 Repository에 등록함으로써 bean으로 사용가능하게 한다
@Repository("TravelDAO")
public class TravelDAO extends AbstractDAO{

	public void insertTravel(TravelDTO travel) {
		System.out.println("DAO에서 inserTravel을 콜했습니다");
		insert("travel.insertTravel", travel);
	}

	public void insertTravelDetail(TravelDetailDTO travelDetail) {
		// TODO Auto-generated method stub
		System.out.println("DAO에서 insertTravelDetail을 콜했습니다");
		insert("travel.insertTravelDetail", travelDetail);
	}

	public void insertTravelImage(TravelImageDTO travelImage) {
		System.out.println("DAO에서 insertTravelImage를 콜했습니다");
		insert("travel.insertTravelImage", travelImage);
	}

	public void insertTravelRoute(TravelRouteDTO travelRoute) {
		System.out.println("DAO에서 insertTravelRoute를 콜했습니다");
		insert("travel.insertTravelRoute", travelRoute);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravel(Map<String, Object> map) {
		System.out.println("DAO에서 selectTravel을 콜했습니다");
		return (List<Map<String, Object>>)selectList("travel.selectTravel", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTravelDetail(int code) {
		System.out.println("DAO에서 selectTravelDetail을 콜했습니다");
		return (List<Map<String, Object>>)selectList("travel.selectTravelDetail", code);
	}

	public List<Map<Object, Object>> selectTravelDetailbyCode(int code) {
		System.out.println("int : DAO에서 selectTravelDetail을 콜했습니다");
		return (List<Map<Object, Object>>)selectList("travel.selectTravelDetailbyCode", code);
	}
}
