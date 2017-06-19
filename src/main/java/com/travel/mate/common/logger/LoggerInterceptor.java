/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 05. 31
 * @Modify	: 2017. 06. 09(by Son Min Hye)
 * 			: 2017. 06. 17
 * @Details	: 2017. 06. 09 - message count interceptor 추가
 * 			: 2017. 06. 17 - comment 추가
 */

package com.travel.mate.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.travel.mate.security.MyUser;
import com.travel.mate.service.MongoChatServiceImpl;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
    protected Log log = LogFactory.getLog(LoggerInterceptor.class);
     
    @Autowired
	MongoChatServiceImpl mongoService;
    
    Authentication auth;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("======================================          START         ======================================");
            log.debug(" Request URI \t:  " + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }
     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("======================================           END          ======================================\n");
        }
        
        auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal!=null && principal instanceof MyUser){
    		int userCode =  ((MyUser)principal).getUserCode();
    		int unReadCount = mongoService.checkUnReadMessage(userCode);
            request.setAttribute("unReadCount", unReadCount);
        }
    }
    
}