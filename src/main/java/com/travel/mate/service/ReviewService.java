package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import com.travel.mate.dto.ReviewDTO;
import com.travel.mate.dto.TravelDTO;

public interface ReviewService {

	List<Map<String, Object>> selectReviewAll(TravelDTO travelDto);

	List<Map<String, Object>> selectReviewWriteCheck(TravelDTO travelDto);

	void insertReview(ReviewDTO reviewDto);

	List<Map<String, Object>> selectReviewWrite(TravelDTO travelDto);

}
