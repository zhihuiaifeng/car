/**
 * 
 */
package com.bool.carshare.util;

/**
 * JSONRequest
 * @author wangw
 */
public class JSONRequest extends JSONInteractive{
	/**
	 * SessionID
	 */
	private String sessionID;
	
	/**
	 * 方法
	 */
	private String method;
	
	/**
	 * 参数
	 */
	private Object para;
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object getPara() {
		return para;
	}

	public void setPara(Object para) {
		this.para = para;
	}
}