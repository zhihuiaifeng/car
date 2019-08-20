package com.bool.carshare.util;

import java.io.Serializable;
/**
 * 分页请求bean
 * @author tzw
 *
 * @param <T>
 */
public class PageRequest<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135761607131976570L;
	
	// 每页多少行
	private Integer row;
	
	// 开始的条数
	private Integer start;
	
	// 当前页数
	private Integer page;
	
	// 查询条件
	private T condition;
	
	

	public PageRequest() {
		super();
	}

	public PageRequest(Integer row, Integer page, T condition) {
		super();
		this.row = row;
		this.page = page;
		this.condition = condition;
		calcStart();
	}
	// 计算开始条数
	private void calcStart(){
		if(page != null && row != null){
			if(page != 0){
				start = (page - 1) * row;
			}else{
				start = 0;
			}
		}
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getStart() {
		return this.start;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}
	
	
	
}
