package com.travel.mate.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
	
	@Resource(name="TravelDAO")
	private TravelDAO travelDAO;

	// 글쓰기
	@Override
	public void insertTravel(TravelDTO travelDto, TravelDetailDTO travelDetailDto, TravelImageDTO travelImageDto, TravelRouteDTO travelRouteDto) {
		// 좌표 여러개(리스트) 얻은 후
		// DTO의 method를 콜하는 것에서 주의! jsp파일의 list명과 DTO 내의 객체이름이 같아야함
		List<TravelDTO> travels = travelDto.getTlist();
		List<TravelDetailDTO> travelDetails = travelDetailDto.getTdlist();
		List<TravelImageDTO> travelImages = travelImageDto.getTilist();
		List<TravelRouteDTO> routes = travelRouteDto.getTrlist();
		
		int travelCode = 0;
		
		if ((null != travels && travels.size() > 0)
				&& (null != travelDetails && travelDetails.size() > 0)
				&& (null != travelImages && travelImages.size() > 0)) {
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
			// insert.. travelImage table
			for (TravelImageDTO travelImage : travelImages) {
				// travelCode setting
				travelImage.setTravelCode(travelCode);
				travelDAO.insertTravelImage(travelImage);
			}
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
	}

	/* 다음 3개의 method는 글 읽기의 기능 */
	@Override
	public List<Map<String, Object>> selectTravel(Map<String, Object> map) {
		return travelDAO.selectTravel(map);
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
	public List<Map<String, Object>> scrollDown(Integer code) {
		return travelDAO.scrollDown(code);
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
	public void insertTravelApply(ApplyDTO applyDto) {
		List<ApplyDTO> applys = applyDto.getAlist();
		if ((null != applys && applys.size() > 0)) {
			for (ApplyDTO apply : applys) {
				travelDAO.insertTravelApply(apply);
			}
		}
	}
	
	// delete.. apply table
	@Override
	public void deleteTravelApply(ApplyDTO applyDto) {
		List<ApplyDTO> applys = applyDto.getAlist();
		if ((null != applys && applys.size() > 0)) {
			for (ApplyDTO apply : applys) {
				travelDAO.deleteTravelApply(apply);
			}
		}
	}
}