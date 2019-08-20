/**
 * 
 */
package com.bool.carshare.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.bool.carshare.bean.UserBean;

/**
 * RecordInfo
 * @author wangw
 */
public class RecordInfo {
	/**
	 * ID
	 */
	private String ID;
	
	/**
	 * 记录人
	 */
	private UserBean recorder;
	
	/**
	 * 记录时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date recordDate;
	
	/**
	 * 版本号
	 */
	private int ver;
	
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public UserBean getRecorder() {
		return recorder;
	}

	public void setRecorder(UserBean recorder) {
		this.recorder = recorder;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}