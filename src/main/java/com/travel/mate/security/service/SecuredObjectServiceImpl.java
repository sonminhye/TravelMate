package com.travel.mate.security.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.travel.mate.security.service.SecuredObjectDao;

public class SecuredObjectServiceImpl implements SecuredObjectService {
	
	private SecuredObjectDao securedObjectDao;
	
	public SecuredObjectDao getSecuredObjectDao() {
	  return securedObjectDao;
	}
	
	public void setSecureObjectDao(SecuredObjectDao secureObjectDao) {
	  this.securedObjectDao = secureObjectDao;
	}
	
	/*url 형식의 리소스를 얻어올 때*/
	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
	  // TODO Auto-generated method stub
	  LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
	  LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDao.getRolesAndUrl();

	  Set<Object> keys = data.keySet();
	  for(Object key : keys){
	  requestMap.put((AntPathRequestMatcher)key, data.get(key));
	  }
	  return requestMap;
	}
	
	/*method 형식의 리소스를 얻어올 때*/
	@Override
	public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
	  // TODO Auto-generated method stub
	  LinkedHashMap<String, List<ConfigAttribute>> requestMap = new LinkedHashMap<String, List<ConfigAttribute>>();
	  LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDao.getRolesAndMethod();
	  Set<Object> keys = data.keySet();
	  for(Object key : keys){
	  requestMap.put((String)key, data.get(key));
	  }
	  return requestMap;
	}

}
