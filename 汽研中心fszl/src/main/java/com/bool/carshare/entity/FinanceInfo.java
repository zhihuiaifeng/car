package com.bool.carshare.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 财务管理
 */
public class FinanceInfo {
	
	//账户订单号
	private String pid;
	//退款单号
	private String payId;
	//账户类型（1-微信，2-支付宝）
	private String payStatus;
	//退款金额
	private Double payment;
	//处理状态(1-未处理，2-已受理，3已完成)
	private Integer status;
	//申请时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date applyTime;
	//完成时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date finishTime;
	//用户编号
	private String other;
	//退款流水单号
	private String newPayId;
	
	
	
	public String getNewPayId() {
		return newPayId;
	}
	public void setNewPayId(String newPayId) {
		this.newPayId = newPayId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
	
	
	
	
}
