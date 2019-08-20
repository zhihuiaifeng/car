/**
 * 
 */
package com.bool.carshare.entity;

/**
 * NodeInfoEntity
 * @author wangw
 */
public class NodeInfo {
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 顺序
	 */
	private Integer order;
	
	/**
	 * 父ID
	 */
	private Integer parent_id;
	
	/**
	 * 版本号
	 */
	private Integer ver;
	
	/**
	 * 操作类型
	 */
	private Integer operate_type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Integer getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(Integer operate_type) {
		this.operate_type = operate_type;
	}
}