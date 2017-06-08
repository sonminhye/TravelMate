package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.ReviewDTO;

import org.springframework.stereotype.Repository;

@Repository("ReviewDAO")
public class ReviewDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewAll(int tcode) {
		return (List<Map<String, Object>>)selectList("review.selectReviewAll", tcode);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewWriteCheck(ApplyDTO applyDto) {
		return (List<Map<String, Object>>)selectList("review.selectReviewWriteCheck", applyDto);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewWrite(ApplyDTO applyDto) {
		return (List<Map<String, Object>>)selectList("review.selectReviewWrite", applyDto);
	}

	public void insertReview(ReviewDTO review) {
		insert("review.insertReview", review);
	}

}
