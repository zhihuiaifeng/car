package com.bool.carshare.entity;

import java.io.Serializable;

/**
 * orderEntity
 * @author yxy
 *
 */
public class OrderInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6609088239402651172L;
	//Order_Number订单编号
	private String orderNumber;
	//Order_State订单状态：0-订单生产，1-订单计费，2-订单取消，3-订单完成，4-订单未付款 ,5-订单异常
	private String orderState;
	//StartTime开始时间
	private String startTime;
	//StartSite开始地点
	private String startSite;
	//EndTime结束时间
	private String endTime;
	//EndSite结束地点
	private String endSite;
	//Run_Length行驶里程
	private Double runLength;
	//RunTime行驶时间
	private String runTime;
	//Expenses费用
	private Double expenses;
	//Uid登录用户编号
	private Integer uid;
	//Terminal_id终端id
	private String terminalId;
	//carid车辆id
	private Integer carId;
	//备注
	private String remarks;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartSite() {
		return startSite;
	}
	public void setStartSite(String startSite) {
		this.startSite = startSite;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndSite() {
		return endSite;
	}
	public void setEndSite(String endSite) {
		this.endSite = endSite;
	}
	public Double getRunLength() {
		return runLength;
	}
	public void setRunLength(Double runLength) {
		this.runLength = runLength;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public Double getExpenses() {
		return expenses;
	}
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
