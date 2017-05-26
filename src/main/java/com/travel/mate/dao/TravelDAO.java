package com.travel.mate.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

// class를 Repository에 등록함으로써 bean으로 사용가능하게 한다
@Repository
public class TravelDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void insertTravel(List<TravelDTO> travelDto, List<TravelDetailDTO> travelDetailDto, List<TravelImageDTO> travelImageDto,
			List<TravelRouteDTO> travelRouteDto) {
		sqlSession.insert("insertTravel", travelDto);
	}
}
