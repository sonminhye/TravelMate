package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
	
	@Resource(name="ReviewDAO")
	private ReviewDAO reviewDAO;
	
	@Resource(name="ApplyDAO")
	private ApplyDAO applyDAO;
	
	@Resource(name="UserDAO")
	private UserDAO userDAO;
	
	@Autowired
	PlatformTransactionManager transactionManager;

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
	public void insertReview(ApplyDTO applyDto, String content, int point) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			List<ApplyDTO> applys = applyDto.getAlist();
			if ((null != applys && applys.size() > 0)) {
				for (ApplyDTO apply : applys) {
					// applyCode 얻어와서 리뷰에 넣는다
					List<Map<String, Object>> applyCodeList = applyDAO.selectApply(apply);
	//				System.out.println(applyCode);
					Map<String, Object> aCode = applyCodeList.get(0);
	//				System.out.println(aCode);
					// java.lang.Integer cannot be cast to java.lang.String (캐스팅 불가 -> 메소드 사용)
					String stringCode = String.valueOf(aCode.get("applyCode"));
	//				System.out.println(stringCode);
					int applyCode = Integer.parseInt(stringCode);
					ReviewDTO reviewDto = new ReviewDTO();
					reviewDto.setApplyCode(applyCode);
					reviewDto.setContent(content);
					
					TravelEvalDTO travelEvalDto = new TravelEvalDTO();
					travelEvalDto.setApplyCode(applyCode);
					travelEvalDto.setPoint(point);
					
					reviewDAO.insertReview(reviewDto);
					reviewDAO.insertPoint(travelEvalDto);
					// meanpoint update
					System.out.println(applyCode);
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
						// 가중 평균 더하기
						meanPoint += avgPoint * (people / allPeople);
					}
					// userDetail table update
					UserDetailDTO userDetailDto = new UserDetailDTO();
					userDetailDto.setMeanPoint(meanPoint);
					userDetailDto.setUserCode(writerCode);
					userDAO.updateUserMeanPoint(userDetailDto);
				}
			}
			// 이상 없으면 commit
			transactionManager.commit(status);
		}
		catch (Exception e) {
			// rollback
			transactionManager.rollback(status);
			throw e;
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
