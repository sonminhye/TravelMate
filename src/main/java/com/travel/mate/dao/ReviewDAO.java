package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ReviewDTO;

import org.springframework.stereotype.Repository;

@Repository("ReviewDAO")
public class ReviewDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewAll(int tcode) {
		return (List<Map<String, Object>>)selectList("review.selectReviewAll", tcode);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewWriteCheck(ReviewDTO reviewDto) {
		return (List<Map<String, Object>>)selectList("review.selectReviewWriteCheck", reviewDto);
	}

	public void insertReview(ReviewDTO review) {
		insert("review.insertReview", review);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewWrite(ReviewDTO reviewDto) {
		return (List<Map<String, Object>>)selectList("review.selectReviewWrite", reviewDto);
	}

}
