/**
 * 
 */
package com.bool.carshare.bean;

import com.bool.carshare.util.JSONInteractive;

/**
 * ResultBean
 * @author wangw
 */
public class ResultBean extends JSONInteractive{
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