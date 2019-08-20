/**
 * 
 */
package com.bool.carshare.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.bool.carshare.bean.UserBean;

/**
 * LogoInfo
 * @author wangw
 */
public class LogoInfo extends RecordInfo{
	/**
	 * 平台名称
	 */
	private String platformName;
	
	/**
	 * 图片
	 */
	private String picture;
	
	/**
	 * 用户
	 */
	private UserBean user;
	
	/**
	 * 上传时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}