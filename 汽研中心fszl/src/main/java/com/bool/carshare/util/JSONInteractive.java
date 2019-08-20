/**
 * 
 */
package com.bool.carshare.util;

import java.util.List;

/**
 * JSONInteractive
 * @author wangw
 */
public class JSONInteractive {
	/**
	 * 数据
	 */
	private Object data;
	
	/**
	 * 数据列表
	 */
	private List<?> dataList;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
}