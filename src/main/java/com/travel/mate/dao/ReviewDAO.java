package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.ReviewDTO;
import com.travel.mate.dto.TravelEvalDTO;

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

	public void insertPoint(TravelEvalDTO travelEvalDto) {
		insert("review.insertTravelEval", travelEvalDto);	
	}
	
	/* 아래 세 개의 메소드는 userDetail meanPoint를 update하기 위함 */
	public int selectUserCode(int applyCode) {
		return (Integer) selectOne("review.selectUserCode", applyCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAvgPoint(int userCode) {
		return (List<Map<String, Object>>) selectList("review.selectAvgPoint", userCode);
	}
	
	public int selectCountAllPeople(int userCode) {
		return (Integer) selectOne("review.selectCountAllPeople", userCode);
	}

}
