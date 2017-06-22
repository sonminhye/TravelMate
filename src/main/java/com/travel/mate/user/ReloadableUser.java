package com.travel.mate.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.travel.mate.security.CustomJdbcDaoImpl;

@Component
public class ReloadableUser {
	@Autowired
	CustomJdbcDaoImpl dao;
	
	/*SecurityContextHolder 에 새로운 Authentication 객체를 셋팅*/
	public void reloadAuthentication(String userName) {

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,userName));

	}

     /*새로운 Authentication 생성*/
	 protected Authentication createNewAuthentication(Authentication currentAuth, String userName) {

	        UserDetails newPrincipal = dao.loadUserByUsername(userName);

	        UsernamePasswordAuthenticationToken newAuth = 
	        new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	        newAuth.setDetails(currentAuth.getDetails());

	        return newAuth;

	    }
}
