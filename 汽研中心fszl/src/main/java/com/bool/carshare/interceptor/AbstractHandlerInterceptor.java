package com.bool.carshare.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 *  Interceptor Adapter -> create date 2017/6/7
 * @author tzw
 *
 */
public abstract class AbstractHandlerInterceptor implements HandlerInterceptor{
	
	
	// no 
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception exp)
			throws Exception {
		
	}
	// no 
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView exp)
			throws Exception {
		
	}
}
