package com.travel.mate.service;

import java.io.File;
import java.util.Iterator;
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

// 서비스 선언, controller에서 resource를 통해 접근
@Service("TravelService")
public class TravelServiceImpl implements TravelService {
	Logger log = Logger.getLogger(this.getClass());
	

	private static final String filepath = "/var/webapps/userimg/";

	
	@Resource(name="TravelDAO")
	private TravelDAO travelDAO;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	
	// 글쓰기
	@Override
	public void insertTravel(TravelDTO travelDto, TravelDetailDTO travelDetailDto, TravelRouteDTO travelRouteDto, MultipartHttpServletRequest request) throws Exception {
		// Route가 여러 개일수 있으므로, DTO를 list로 반환(jsp에서 모두 array(name=list[0].field)로 던지기 때문에 list로 써야함)
		List<TravelDTO> travels = travelDto.getTlist();
		List<TravelDetailDTO> travelDetails = travelDetailDto.getTdlist();
		List<TravelRouteDTO> routes = travelRouteDto.getTrlist();
		
		int travelCode = 0;
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			// 첨부한 파일을 얻어온다
			MultipartFile f = request.getFile("image");
			String filename = f.getOriginalFilename();
			
			Iterator<String> iterator = request.getFileNames();
			
			MultipartFile multipartFile = request.getFile(iterator.next());
			
			String storedFileName = null;
			String temp = null;
			
			// 저장할 경로가 없다면 생성
			File file = new File(filepath);
			if (file.exists() == false) {
				file.mkdirs();
			}
			
			temp = filename.substring(filename.lastIndexOf("."));
			temp = temp.toLowerCase();
			
			// 첨부파일 이미지인지 check
			if ((temp.equals(".jpg") || temp.equals(".gif") || temp.equals(".png") || temp.equals(".jpeg") || temp.equals(".bmp"))
					&& (null != travels && travels.size() > 0)
					&& (null != travelDetails && travelDetails.size() > 0)
					&& (null != request)) {
				
				// insert.. travel table & apply table 
				for (TravelDTO travel : travels) {
					travelDAO.insertTravel(travel);
					// travelCode general
					travelCode = travel.getTravelCode();
					/* 작성자도 신청한 것으로 처리 */
					ApplyDTO apply = new ApplyDTO();
					apply.setTravelCode(travelCode);
					apply.setUserCode(travel.getUserCode());
					travelDAO.insertTravelApply(apply);
				}
				// insert.. travelDetail table
				for (TravelDetailDTO travelDetail : travelDetails) {
					// travelCode setting
					travelDetail.setTravelCode(travelCode);
					travelDAO.insertTravelDetail(travelDetail);
				}
				

				storedFileName = CommonUtil.getRandomString() + temp;

				
				file = new File(filepath + storedFileName);
				// 지정한 경로에 파일 저장
				multipartFile.transferTo(file);
				
				// 파일명 db에 insert
				TravelImageDTO travelImage = new TravelImageDTO();
				travelImage.setImage(storedFileName);
				travelImage.setTravelCode(travelCode);
				travelDAO.insertTravelImage(travelImage);
			
			}
			else {
				log.debug("Not image file!! OR Format empty!! ");
				throw new Exception();
			}
			
			// insert.. travelRoute table
			if (null != routes && routes.size() > 0) {
				for (TravelRouteDTO route : routes) {
					// travelCode setting
					route.setTravelCode(travelCode);
					travelDAO.insertTravelRoute(route);
				}
			} 
			// 장소 선택을 하지 않아 리스트가 비어있는 경우, insert 하지 않음
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

	/* 다음 3개의 method는 글 읽기의 기능 */
	@Override
	public List<Map<String, Object>> selectTravel() {
		return travelDAO.selectTravel();
	}
	@Override
	public List<Map<String, Object>> selectTravelDetail(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		return travelDAO.selectTravelDetail(tcode);
	}
	@Override
	public List<Map<String, Object>> selectTravelRoute(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		return travelDAO.selectTravelRoute(tcode);
	}
	
	// scroll
	@Override
	public List<Map<String, Object>> scrollDown(String keys) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		// JSON 데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = (JSONObject) jsonParser.parse(keys);
		int code = Integer.parseInt((String) jsonObject.get("travelCode"));
		
		return travelDAO.scrollDown(code-1);
	}

	// for check applicant(로그인한 회원이 신청한 사람인지 체크하기 위해 신청목록을 가져옴)
	@Override
	public List<Map<String, Object>> selectTravelApply(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		// 유저 코드
		int ucode = travelDto.getUserCode();
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return travelDAO.selectTravelApply(applyDto);
	}

	// for check max(최대인원을 넘었는지 알기 위해 신청목록을 가져옴)
	@Override
	public List<Map<String, Object>> selectTravelApplyCount(TravelDTO travelDto) {
		// 여행(게시물)의 번호를 가져온다
		int tcode = travelDto.getTravelCode();
		// 유저 코드
		int ucode = travelDto.getUserCode();
		
		ApplyDTO applyDto = new ApplyDTO();
		applyDto.setTravelCode(tcode);
		applyDto.setUserCode(ucode);
		return travelDAO.selectTravelApplyCount(applyDto);
	}
	
	// insert.. apply table
	@Override
	public void insertTravelApply(ApplyDTO applyDto) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			List<ApplyDTO> applys = applyDto.getAlist();
			if ((null != applys && applys.size() > 0)) {
				for (ApplyDTO apply : applys) {
					travelDAO.insertTravelApply(apply);
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
	
	// delete.. apply table
	@Override
	public void deleteTravelApply(ApplyDTO applyDto) throws Exception {
		
		// transaction
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			List<ApplyDTO> applys = applyDto.getAlist();
			if ((null != applys && applys.size() > 0)) {
				for (ApplyDTO apply : applys) {
					travelDAO.deleteTravelApply(apply);
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

	// 글쓴이 정보
	@Override
	public List<Map<String, Object>> selectUserInfo(TravelDTO travelDto) {
		int tCode = travelDto.getTravelCode();
		return travelDAO.selectUserInfo(tCode);
	}
}