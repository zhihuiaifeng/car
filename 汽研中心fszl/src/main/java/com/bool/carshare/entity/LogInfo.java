/**
 * 
 */
package com.bool.carshare.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.bool.carshare.bean.UserBean;

/**
 * LogInfo
 * @author wangw
 */
public class LogInfo extends RecordInfo{
	/**
	 * 操作人
	 */
	private UserBean operator;
	
	/**
	 * 操作时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date operateDateTime;
	
	/**
	 * 模块名称
	 */
	private String moduleName;
	
	/**
	 * 操作
	 */
	private String operation;
	
	public UserBean getOperator() {
		return operator;
	}
	public void setOperator(UserBean operator) {
		this.operator = operator;
	}
	public Date getOperateDateTime() {
		return operateDateTime;
	}
	public void setOperateDateTime(Date operateDateTime) {
		this.operateDateTime = operateDateTime;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}