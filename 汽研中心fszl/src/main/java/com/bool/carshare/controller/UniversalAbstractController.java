/**
 * 
 */
package com.bool.carshare.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.util.JSONRequest;
import com.bool.carshare.util.JSONResponse;
import com.bool.carshare.util.WebUtil;

/**
 * UniversalAbstractController
 * @author wangw
 */
public abstract class UniversalAbstractController {
	/**
	 * 请求
	 */
	public HttpServletRequest request;
	
	/**
	 * 响应
	 */
	public HttpServletResponse response;
	
	/**
	 * 初始化Controller
	 * @param request
	 * @param response
	 */
	public void initController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		
		this.response = response;
	}
	
	/**
	 * 调用方法
	 * @param serviceClass
	 * @param serviceInstance
	 */
	public void invokeMethod(Class<?> serviceClass, Object serviceInstance) {
		JSONRequest jsonRequest = WebUtil.getJSONRequest(this.request);
		String methodName = jsonRequest.getMethod();
		Object para = jsonRequest.getPara();
		String sessionID = jsonRequest.getSessionID();
		
		WebObject webObject = new WebObject();
		webObject.setRequest(this.request);
		webObject.setResponse(this.response);
		webObject.setSessionID(sessionID);
		webObject.setPara(para);
		
		JSONResponse jsonResponse = new JSONResponse();
		try {
			Method method = serviceClass.getMethod(methodName, WebObject.class);
			Object result = method.invoke(serviceInstance, webObject);
			
			jsonResponse.setStatus(true);
			jsonResponse.setInfo(JSONResponse.SUCCEED);
			jsonResponse.setData(result);
		} catch (NoSuchMethodException e) {
			jsonResponse.setStatus(false);
			jsonResponse.setInfo(JSONResponse.FAIL);
			
			e.printStackTrace();
		} catch (SecurityException e) {
			jsonResponse.setStatus(false);
			jsonResponse.setInfo(JSONResponse.FAIL);
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			jsonResponse.setStatus(false);
			jsonResponse.setInfo(JSONResponse.FAIL);
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			jsonResponse.setStatus(false);
			jsonResponse.setInfo(JSONResponse.FAIL);
			
			e.printStackTrace();
		} finally {
			WebUtil.sendJSONData(jsonResponse, this.response);
		}
	}
}