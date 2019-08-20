/**
 * 
 */
package com.bool.carshare.bean;

/**
 * LoginBean
 * @author wangw
 */
public class LoginBean extends ResultBean{
	public static final String SESSION_ID = "sessionID";
	public static final String SUCCEED = "成功";
	public static final String FAIL = "失败";
	public static final String TIME_OUT = "超时";
	public static final String LOG_OUT = "退出";
	
	/**
	 * SessionID
	 */
	private String sessionID;
	
	/**
	 * 用户信息
	 */
	private Object userInfo;
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Object getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
}