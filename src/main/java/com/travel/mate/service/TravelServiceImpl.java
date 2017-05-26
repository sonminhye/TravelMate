package com.travel.mate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.travel.mate.dao.TravelDAO;
import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

@Service("travelService")
public class TravelServiceImpl implements TravelService {

	@Resource(name="travelDAO")
	private TravelDAO travelDAO;
	
	@Override
	public void insertTravel(List<TravelDTO> travelDto, List<TravelDetailDTO> travelDetailDto, List<TravelImageDTO> travelImageDto,
			List<TravelRouteDTO> travelRouteDto) {
		travelDAO.insertTravel(travelDto, travelDetailDto, travelImageDto, travelRouteDto);
	}

}