package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

public interface TravelService {
	/* 여행등록 */
	void insertTravel(TravelDTO travelDto, TravelDetailDTO travelDetailDto, TravelRouteDTO travelRouteDto, MultipartHttpServletRequest request);

	/* 여행리스트 및 읽기 */
	List<Map<String, Object>> selectTravel(Map<String, Object> map);
	List<Map<String, Object>> selectTravelDetail(TravelDTO travelDto);
	List<Map<String, Object>> selectTravelRoute(TravelDTO travelDto);

	/* 스크롤 */
	List<Map<String, Object>> scrollDown(Integer code);

	/* 신청 */
	void insertTravelApply(ApplyDTO applyDto);
	/* 신청취소 */
	void deleteTravelApply(ApplyDTO applyDto);
	/* 신청한 사람 및 인원 수 */
	List<Map<String, Object>> selectTravelApply(TravelDTO travelDto);
	List<Map<String, Object>> selectTravelApplyCount(TravelDTO travelDto);

}
