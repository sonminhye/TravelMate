package com.travel.mate.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.travel.mate.security.CustomJdbcDaoImpl;

@Component
public class UserEventListener implements ApplicationListener<UserEvent>{
	@Autowired
	CustomJdbcDaoImpl dao;
	
	
	@Override
	@EventListener
	public void onApplicationEvent(UserEvent event) {
		// TODO Auto-generated method stub
		System.out.println("이벤트!!");
		 String userName = event.getUserName();


         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,userName));
 
	        

	}

	 protected Authentication createNewAuthentication(Authentication currentAuth, String userName) {

	        UserDetails newPrincipal = dao.loadUserByUsername(userName);

	        UsernamePasswordAuthenticationToken newAuth = 
	        new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	        newAuth.setDetails(currentAuth.getDetails());

	        return newAuth;

	    }

}
