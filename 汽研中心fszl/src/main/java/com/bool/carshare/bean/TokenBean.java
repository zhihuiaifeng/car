/**
 * 
 */
package com.bool.carshare.bean;

/**
 * TokenBean
 * @author wangw
 */
public class TokenBean extends ResultBean{
	public static final String TOKEN_ID = "tokenID";
	public static final String SUCCEED = "成功";
	public static final String FAIL = "失败";
	public static final String INVALID = "无效";
	
	/**
	 * ID
	 */
	private String ID;
	
	/**
	 * 用户信息
	 */
	private Object userInfo;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Object getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
}