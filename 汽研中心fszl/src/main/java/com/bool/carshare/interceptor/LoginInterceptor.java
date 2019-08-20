/**
 * 
 */
package com.bool.carshare.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bool.carshare.bean.LoginBean;
import com.bool.carshare.util.JSONRequest;
import com.bool.carshare.util.JSONResponse;
import com.bool.carshare.util.UserUtil;
import com.bool.carshare.util.WebUtil;

/**
 * LoginInterceptor
 * @author wangw
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
		JSONRequest jsonRequest = WebUtil.getJSONRequest(request);
		
		String methodName = jsonRequest.getMethod();
		if(!"isLogin".equals(methodName)) {
			return true;
		}
		
		String sessionID = jsonRequest.getSessionID();
		
		Boolean isLogin = UserUtil.isLogin(sessionID);
		if(isLogin) {
			return true;
		}else {
			LoginBean loginBean = new LoginBean();
			loginBean.setStatus(false);
			loginBean.setInfo(LoginBean.TIME_OUT);
			
			JSONResponse jsonResponse = new JSONResponse();
			jsonResponse.setStatus(true);
			jsonResponse.setInfo(JSONResponse.SUCCEED);
			jsonResponse.setData(loginBean);
			
			WebUtil.sendJSONData(jsonResponse, response);
			
			return false;
		}
	}
}