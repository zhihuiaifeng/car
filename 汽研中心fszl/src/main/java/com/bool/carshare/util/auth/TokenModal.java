package com.bool.carshare.util.auth;

import java.io.Serializable;

import com.bool.carshare.entity.UserInfo;

/**
 * user token modal 
 * @author tzw
 */
public class TokenModal implements Serializable{

	/**
	 *  serial id 
	 */
	private static final long serialVersionUID = -3386068332210175360L;

	// user
	private UserInfo userInfo;

	private long lastTime;

	private String token;
	
	

	public TokenModal() {
		super();
	}

	public TokenModal(UserInfo userInfo, long lastTime, String token) {
		super();
		this.userInfo = userInfo;
		this.lastTime = lastTime;
		this.token = token;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
