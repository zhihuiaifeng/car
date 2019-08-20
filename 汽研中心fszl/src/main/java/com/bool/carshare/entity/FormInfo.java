/**
 * 
 */
package com.bool.carshare.entity;

import com.bool.carshare.bean.UserBean;

/**
 * FormInfo
 * @author wangw
 */
public class FormInfo extends RecordInfo{
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	/**
	 * 责任人
	 */
	private UserBean charger;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 位置1
	 */
	private String location1;
	
	/**
	 * 位置2
	 */
	private String location2;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 排序的列名
	 */
	private String sortName;
	
	/**
	 * 排序的顺序
	 */
	private String sortOrder;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public UserBean getCharger() {
		return charger;
	}

	public void setCharger(UserBean charger) {
		this.charger = charger;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSortName() {
		if("recordDate".equals(this.sortName)) {
			this.sortName = "record_date_time";
		}
		
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}