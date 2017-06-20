/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 05. 26
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

package com.travel.mate.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.travel.mate.common.file.CommonUtil;
import com.travel.mate.dao.TravelDAO;
import com.travel.mate.dto.ApplyDTO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

@Service("TravelService")
public class TravelServiceImpl implements TravelService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	private static final String filepath = "/var/webapps/userimg/";
	
	@Resource(name="TravelDAO")
	private TravelDAO travelDAO;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	/*
	 * Method	: insertTravel
	 * Summary	: 각 DTO에 데이터를 저장하여 테이블 하나씩 insert, logic에 이상 있을 시 rollback
	 * @param	: TravelDTO(for insert travel)
	 * @param	: TravelDetailDTO(for insert travelDetail)
	 * @param	: TravelRouteDTO(for insert travelRoute)
	 * @param	: MultipartHttpServletRequest(for insert travelImage)
	 * @Return	: void
	 */
	@Override
	public void insertTravel(TravelDTO travelDto, TravelDetailDTO travelDetailDto, TravelRouteDTO travelRouteDto, MultipartHttpServletRequest request) throws Exception {
		List<TravelRouteDTO> routes = travelRouteDto.getTrlist();
		int travelCode = 0;
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			File file = new File(filepath);
			if (file.exists() == false) {
				file.mkdirs();
			}
			
			MultipartFile f = request.getFile("image");
			String filename = f.getOriginalFilename();
			
			String storedFileName = null;
			String temp = null;
			
			temp = filename.substring(filename.lastIndexOf("."));
			temp = temp.toLowerCase();
			
			// File size Check
			if (f.getSize() > (2 * 1024 * 1024)) {
				log.error("File size is big");
				throw new Exception();
			}
			
			if ((temp.equals(".jpg") || temp.equals(".gif") ||
						temp.equals(".png") || temp.equals(".jpeg") ||
						temp.equals(".bmp"))
					&& (null != request)) {
				travelDAO.insertTravel(travelDto);
				
				// travelCode general
				travelCode = travelDto.getTravelCode();
				
				/* 작성자도 신청한 것으로 처리 */
				ApplyDTO apply = new ApplyDTO();
				apply.setTravelCode(travelCode);
				apply.setUserCode(travelDto.getUserCode());
				travelDAO.insertTravelApply(apply);

				travelDetailDto.setTravelCode(travelCode);
				int dateCompare = 0;
				int min = travelDetailDto.getMinPeople();
				int max = travelDetailDto.getMaxPeople();
				String startDate = travelDetailDto.getStartDate();
				String endDate = travelDetailDto.getEndDate();
				dateCompare = startDate.compareTo(endDate);
					
				if ((dateCompare >= 0) || (min > max)) {
					log.error("startDate > EndDate OR minPeople > maxPeople");
					throw new Exception();
				}
				travelDAO.insertTravelDetail(travelDetailDto);

				storedFileName = CommonUtil.getRandomString() + temp;
				
				file = new File(filepath + storedFileName);
				f.transferTo(file);
				TravelImageDTO travelImage = new TravelImageDTO();
				travelImage.setImage(storedFileName);
				travelImage.setTravelCode(travelCode);
				travelDAO.insertTravelImage(travelImage);
			}
			else {
				log.error("Not image file!! OR Format empty!! ");
				throw new Exception();
			}
			
			if (null != routes && routes.size() > 0) {
				travelRouteDto.setTravelCode(travelCode);
				travelDAO.insertTravelRoute(travelRouteDto);
			} 
			else {
				;
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

	/*
	 * Method	: selectTravel(view list)
	 * Summary	: 최근 6개의 여행 정보를 반환
	 * @param	: Notting
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectTravel() {
		return travelDAO.selectTravel();
	}
	
	/*
	 * Method	: selectTravelDetail(view details)
	 * Summary	: 해당 여행 정보(시간 등)를 반환
	 * @param	: TravelDTO(for get travelCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectTravelDetail(TravelDTO travelDto) {
		// get travelCode
		int tcode = travelDto.getTravelCode();
		return travelDAO.selectTravelDetail(tcode);
	}
	
	/*
	 * Method	: selectTravelRoute(view routes)
	 * Summary	: 해당 여행 정보(여행 경로)를 반환
	 * @param	: TravelDTO(for get travelCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectTravelRoute(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		return travelDAO.selectTravelRoute(tcode);
	}
	
	/*
	 * Method	: scrollDown(Scroll)
	 * Summary	: 스크롤이 최하단에 도착 시, 다음 3개의 여행 정보를 반환
	 * @param	: String
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> scrollDown(String keys) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		// put JSON Data -> return JSON Object
		JSONObject jsonObject = (JSONObject) jsonParser.parse(keys);
		int code = Integer.parseInt((String) jsonObject.get("travelCode"));
		
		return travelDAO.scrollDown(code-1);
	}

	/*
	 * Method	: selectTravelApply(check apply)
	 * Summary	: 현재 글을 보고 있는 사람이 신청한 여행인지 확인 후 확인했다면 결과 반환
	 * @param	: TravelDTO(for get travelCode, userCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectTravelApply(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		int ucode = travelDto.getUserCode();
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return travelDAO.selectTravelApply(applyDto);
	}

	/*
	 * Method	: selectTravelApplyCount(check applyPeople(max))
	 * Summary	: 현재 보고 있는 여행의 신청자를 확인 후 신청 리스트 반환
	 * @param	: TravelDTO(for get travelCode, userCode)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectTravelApplyCount(TravelDTO travelDto) {
		int tcode = travelDto.getTravelCode();
		int ucode = travelDto.getUserCode();
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return travelDAO.selectTravelApplyCount(applyDto);
	}
	
	/*
	 * Method	: insertTravelApply
	 * Summary	: 여행 신청
	 * @param	: ApplyDTO(for get applyInfo)
	 * @Return	: void
	 */
	@Override
	public void insertTravelApply(ApplyDTO applyDto) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			travelDAO.insertTravelApply(applyDto);
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
	 * Method	: deleteTravelApply
	 * Summary	: 여행 신청 취소
	 * @param	: ApplyDTO(for get applyInfo)
	 * @Return	: void
	 */
	@Override
	public void deleteTravelApply(ApplyDTO applyDto) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			travelDAO.deleteTravelApply(applyDto);
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
	 * Method	: selectUserInfo(show writerInfo)
	 * Summary	: 글쓴이의 정보를 반환
	 * @param	: TravelDTO(for get applyInfo)
	 * @Return	: List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectUserInfo(TravelDTO travelDto) {
		int tCode = travelDto.getTravelCode();
		return travelDAO.selectUserInfo(tCode);
	}
	
}