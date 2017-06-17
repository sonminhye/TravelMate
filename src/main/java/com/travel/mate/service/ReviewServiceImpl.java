package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.travel.mate.dao.ApplyDAO;
import com.travel.mate.dao.ReviewDAO;
import com.travel.mate.dao.UserDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.ReviewDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelEvalDTO;
import com.travel.mate.dto.UserDetailDTO;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ReviewDAO")
	private ReviewDAO reviewDAO;
	
	@Resource(name="ApplyDAO")
	private ApplyDAO applyDAO;
	
	@Resource(name="UserDAO")
	private UserDAO userDAO;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	/*
	 * Method	: readTravel(show allReview)
	 * Summary	: 해당 여행의 모든 리뷰를 반환
	 * @param	: TravelDTO(for get travelCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectReviewAll(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		return reviewDAO.selectReviewAll(tcode);
	}

	/*
	 * Method	: readTravel(check writeReview, after view reviewWriteForm)
	 * Summary	: 해당 여행에 리뷰를 작성했는 지 확인 후 결과 반환
	 * @param	: TravelDTO(for get travelCode, userCode(login))
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectReviewWriteCheck(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		int ucode = travelDto.getUserCode();
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return reviewDAO.selectReviewWriteCheck(applyDto);
	}

	/*
	 * Method	: writeReview(insert review & update writerMeanPoint)
	 * Summary	: 리뷰 작성, 평점에 따른 여행 작성자의 평점 업데이트
	 * @param	: ApplyDTO(for get applyInfo), String(for get review content), int(for get review point)
	 * @Return	: void
	 */
	@Override
	public void insertReview(ApplyDTO applyDto, String content, int point) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			if (point < 0 || point > 5) {
				log.error("Out of point (required 0~5)");
				throw new Exception();
			}
			
			// get applyCode
			List<Map<String, Object>> applyCodeList = applyDAO.selectApply(applyDto);
			Map<String, Object> aCode = applyCodeList.get(0);
			
			// java.lang.Integer cannot be cast to java.lang.String(casting err solution.. valueOf method)
			String stringCode = String.valueOf(aCode.get("applyCode"));

			int applyCode = Integer.parseInt(stringCode);
			ReviewDTO reviewDto = new ReviewDTO();
			reviewDto.setApplyCode(applyCode);
			reviewDto.setContent(content);
			
			TravelEvalDTO travelEvalDto = new TravelEvalDTO();
			travelEvalDto.setApplyCode(applyCode);
			travelEvalDto.setPoint(point);
			
			reviewDAO.insertReview(reviewDto);
			reviewDAO.insertPoint(travelEvalDto);
			
			// calculate meanPoint update
			int writerCode = reviewDAO.selectUserCode(applyCode);
			List<Map<String, Object>> avgList = reviewDAO.selectAvgPoint(writerCode);
			int allPeople = reviewDAO.selectCountAllPeople(writerCode);
			float meanPoint = 0;
			for (int i = 0; i < avgList.size(); i++) {
				Map<String, Object> list = avgList.get(i);
				String stringPeople = String.valueOf(list.get("people"));
				String stringAvgPoint = String.valueOf(list.get("avgPoint"));
				float people = Float.parseFloat(stringPeople);
				float avgPoint = Float.parseFloat(stringAvgPoint);
				// update weighted average
				meanPoint += avgPoint * (people / allPeople);
			}

			// userDetail table update
			UserDetailDTO userDetailDto = new UserDetailDTO();
			userDetailDto.setMeanPoint(meanPoint);
			userDetailDto.setUserCode(writerCode);
			userDAO.updateUserMeanPoint(userDetailDto);
			// 이상 없으면 commit
			transactionManager.commit(status);
		}
		catch (Exception e) {
			// rollback
			transactionManager.rollback(status);
			throw e;
		}
	}

	/*
	 * Method	: readTravel(check writeReview)
	 * Summary	: 해당 여행에 리뷰를 작성했는 지 확인 후 결과 반환
	 * @param	: TravelDTO(for get travelCode, userCode(login))
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectReviewWrite(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		int ucode = travelDto.getUserCode();

		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return reviewDAO.selectReviewWrite(applyDto);
	}
	
}