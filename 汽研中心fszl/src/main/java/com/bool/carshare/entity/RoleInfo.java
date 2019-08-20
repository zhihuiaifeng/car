/**
 * 
 */
package com.bool.carshare.entity;

/**
 * RoleInfoEntity
 * @author wangw
 */
public class RoleInfo {
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 权力
	 */
	private Integer power;
	
	/**
	 * 版本号
	 */
	private Integer ver;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}
}