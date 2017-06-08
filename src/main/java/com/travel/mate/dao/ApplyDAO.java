package com.travel.mate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractDAO;
import com.travel.mate.dto.ApplyDTO;

@Repository("ApplyDAO")
public class ApplyDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectApply(ApplyDTO applyDto) {
		return (List<Map<String, Object>>)selectList("apply.selectApply", applyDto);
	}
}
