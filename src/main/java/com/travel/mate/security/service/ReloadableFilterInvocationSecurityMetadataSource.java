package com.travel.mate.security.service;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

/*DefaultFilterInvocationSecurityMetadataSource 클래스와 같은 구조를 가지는 커스터마이즈 클래스*/
public class ReloadableFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
		
	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	
	private SecuredObjectService securedObjectService;
	
	public ReloadableFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap){
	  this.requestMap = requestMap; 
	 
	}
	
	public void setSecuredObjectService(SecuredObjectService securedObjectService) {
	  this.securedObjectService = securedObjectService;
	}
	
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	  // TODO Auto-generated method stub

	  HttpServletRequest request = ((FilterInvocation)object).getRequest();
	  Collection<ConfigAttribute> result = null;
	  for(Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()){
		  if(entry.getKey().matches(request)){
		    result = entry.getValue();
		    break;
		  }
	  }
	  return result;
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
	  // TODO Auto-generated method stub

	  Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
	  for(Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()){
	  allAttributes.addAll(entry.getValue());
	  }
	  return allAttributes;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
	  // TODO Auto-generated method stub
	  return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	/*새롭게 추가한 reload 메소드
	 * 권한 정보가 변경되었을 때 requestMap을 재설정함.*/
	public void reload() throws Exception {
		
	  LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedMap = securedObjectService.getRolesAndUrl();
	  Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadedMap.entrySet().iterator();
	
	  // 이전 데이터 삭제
	  requestMap.clear();
	
	    while (iterator.hasNext()) {
	    Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
	        
	        requestMap.put(entry.getKey(), entry.getValue());
	    }
	}
}

