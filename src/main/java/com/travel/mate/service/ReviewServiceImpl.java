package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.travel.mate.dao.ApplyDAO;
import com.travel.mate.dao.ReviewDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.ReviewDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelEvalDTO;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Resource(name="ReviewDAO")
	private ReviewDAO reviewDAO;
	
	@Resource(name="ApplyDAO")
	private ApplyDAO applyDAO;

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
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return reviewDAO.selectReviewWriteCheck(applyDto);
	}

	@Override
	public void insertReview(ApplyDTO applyDto, String content, int point) {
		List<ApplyDTO> applys = applyDto.getAlist();
		if ((null != applys && applys.size() > 0)) {
			for (ApplyDTO apply : applys) {
				// applyCode 얻어와서 리뷰에 넣는다
				List<Map<String, Object>> applyCode = applyDAO.selectApply(apply);
//				System.out.println(applyCode);
				Map<String, Object> aCode = applyCode.get(0);
//				System.out.println(aCode);
				// java.lang.Integer cannot be cast to java.lang.String (캐스팅 불가 -> 메소드 사용)
				String stringCode = String.valueOf(aCode.get("applyCode"));
//				System.out.println(stringCode);
				int code = Integer.parseInt(stringCode);
				ReviewDTO reviewDto = new ReviewDTO();
				reviewDto.setApplyCode(code);
				reviewDto.setContent(content);
				
				TravelEvalDTO travelEvalDto = new TravelEvalDTO();
				travelEvalDto.setApplyCode(code);
				travelEvalDto.setPoint(point);
				
				reviewDAO.insertReview(reviewDto);
				reviewDAO.insertPoint(travelEvalDto);
			}
		}
	}

	@Override
	public List<Map<String, Object>> selectReviewWrite(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		// 로그인한 사람 코드
		int ucode = travelDto.getUserCode();

		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return reviewDAO.selectReviewWrite(applyDto);
	}

}
