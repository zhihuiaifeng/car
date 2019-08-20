/**
 * 
 */
package com.bool.carshare.bean;

/**
 * 模块类型
 * @author wangw
 */
public enum ModuleType {
	/**
	 * 车辆管理
	 */
	CAR("车辆管理"),
	
	/**
	 * 车辆维护
	 */
	FORM("车辆维护"),
	
	/**
	 * 终端管理
	 */
	TERMINAL("终端管理"),
	
	/**
	 * 网点管理
	 */
	DOT("网点管理"),
	
	/**
	 * 用户管理
	 */
	MANAGE("用户管理"),
	
	/**
	 * Logo管理
	 */
	LOGO("Logo管理");
	
	private String moduleName;
	
	private ModuleType(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Override
	public String toString() {
		return this.moduleName;
	}
}