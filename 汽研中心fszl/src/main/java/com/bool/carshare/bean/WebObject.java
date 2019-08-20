/**
 * 
 */
package com.bool.carshare.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebObject
 * @author wangw
 */
public class WebObject {
	/**
	 * 请求
	 */
	private HttpServletRequest request;
	
	/**
	 * 响应
	 */
	private HttpServletResponse response;
	
	/**
	 * SessionID
	 */
	private String sessionID;
	
	/**
	 * 参数
	 */
	private Object para;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Object getPara() {
		return para;
	}

	public void setPara(Object para) {
		this.para = para;
	}
}