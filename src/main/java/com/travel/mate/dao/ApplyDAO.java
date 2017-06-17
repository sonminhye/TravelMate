/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 06. 07
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

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