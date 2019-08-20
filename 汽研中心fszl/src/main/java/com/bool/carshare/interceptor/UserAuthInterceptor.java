package com.bool.carshare.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.auth.TokenModal;
import com.bool.carshare.util.auth.UserCacheManager;
/**
 * user auth interceptor -> create date 2017/6/7
 * 
 * @author tzw
 *
 */
public class UserAuthInterceptor extends AbstractHandlerInterceptor  {
	
	public static final String TOKEN_KEY = "token";
	
	
	public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
	/**
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
									throws Exception {
		// if hanlder is my controller
		if(handler instanceof HandlerMethod){
			
			String token = request.getHeader(TOKEN_KEY);
			
			// if token is null or token time out -> return error message
			TokenModal userToken = null;
			if(StringUtils.isEmpty(token) || Assert.isNull((userToken = UserCacheManager.getToken(token)))){
				PrintWriter res = response.getWriter();
				try{
					//write result -
					res.println(JSON.toJSON(Result.ResultBuilder.
							buildFailerResult(Message.USER_TIME_OUT, null)));
					res.flush();
				}finally{
					res.close();
				}
				return false;
			}
			request.setAttribute(LOGIN_USER_KEY, userToken);
		}
		return true;
		
	}


}
