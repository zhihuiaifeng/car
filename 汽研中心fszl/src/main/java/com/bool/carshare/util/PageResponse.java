package com.bool.carshare.util;

import java.io.Serializable;
import java.util.List;
/**
 * page响应对象
 * @author tzw
 *
 */
public class PageResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6406441563948989456L;
	// 数据总条数
	private int total;
	
	// 查询到的数据
	private List<?> rows;
	
	

	public PageResponse() {
		super();
	}

	public PageResponse(int total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	

}
