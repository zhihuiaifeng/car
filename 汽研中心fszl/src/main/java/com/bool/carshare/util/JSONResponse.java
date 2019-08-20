/**
 * 
 */
package com.bool.carshare.util;

/**
 * JSONResponse
 * @author wangw
 */
public class JSONResponse extends JSONInteractive{
	public static final String SUCCEED = "succeed";
	public static final String FAIL = "fail";
	
	/**
	 * 状态
	 */
	private Boolean status;
	
	/**
	 * 信息
	 */
	private String info;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}