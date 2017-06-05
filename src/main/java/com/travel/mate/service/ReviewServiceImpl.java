package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.travel.mate.dao.ReviewDAO;
import com.travel.mate.dto.ReviewDTO;
import com.travel.mate.dto.TravelDTO;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Resource(name="ReviewDAO")
	private ReviewDAO reviewDAO;

	@Override
	public List<Map<String, Object>> selectReviewAll(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		return reviewDAO.selectReviewAll(tcode);
	}

	@Override
	public List<Map<String, Object>> selectReviewWriteCheck(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		// 로그인한 사람 코드
		int ucode = travelDto.getUserCode();
		
		ReviewDTO reviewDto = new ReviewDTO();
		reviewDto.setTravelCode(tcode);
		reviewDto.setUserCode(ucode);
		return reviewDAO.selectReviewWriteCheck(reviewDto);
	}

	@Override
	public void insertReview(ReviewDTO reviewDto) {
		List<ReviewDTO> reviews = reviewDto.getRlist();
		if ((null != reviews && reviews.size() > 0)) {
			for (ReviewDTO review : reviews) {
				reviewDAO.insertReview(review);
			}
		}
	}

	@Override
	public List<Map<String, Object>> selectReviewWrite(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		// 로그인한 사람 코드
		int ucode = travelDto.getUserCode();
		
		ReviewDTO reviewDto = new ReviewDTO();
		reviewDto.setTravelCode(tcode);
		reviewDto.setUserCode(ucode);
		return reviewDAO.selectReviewWrite(reviewDto);
	}

}
