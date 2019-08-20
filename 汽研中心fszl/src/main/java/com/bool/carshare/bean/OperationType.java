/**
 * 
 */
package com.bool.carshare.bean;

/**
 * 操作类型
 * @author wangw
 */
public enum OperationType {
	/**
	 * 新增
	 */
	ADD("新增"),
	
	/**
	 * 修改
	 */
	UPDATE("修改"),
	
	/**
	 * 删除
	 */
	DELETE("删除"),
	
	/**
	 * 查询
	 */
	QUERY("查询"),
	
	/**
	 * 修改密码
	 */
	CHANGE_PASSWORD("修改密码");
	
	private String operationName;
	
	private OperationType(String operationName) {
		this.operationName = operationName;
	}
	
	@Override
	public String toString() {
		return this.operationName;
	}
}