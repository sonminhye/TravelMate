package com.travel.mate.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.travel.mate.dto.TravelDTO;
import com.travel.mate.dto.TravelDetailDTO;
import com.travel.mate.dto.TravelImageDTO;
import com.travel.mate.dto.TravelRouteDTO;

public interface TravelService {
	void insertTravel(List<TravelDTO> travelDto, List<TravelDetailDTO> travelDetailDto,
			List<TravelImageDTO> travelImageDto, List<TravelRouteDTO> travelRouteDto);
}
