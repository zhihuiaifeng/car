package com.bool.carshare.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class PayInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4935367047646861212L;

	//流水id（主键）
	private String pid;
	//用户id
	private Integer uid; 
	//充值消费标识（0-充值 1-消费 2-押金 3-退押金）
	private String rechargeORconsume;
	//订单金额
	private Double fictitiousFund;
	//实收金额(平台收到的钱)
	private Double actualFund;
	//付款金额(用户花的钱)
	private Double payment;
	//成交状态（0-已成交 1-未成交,2-申请退款）
	private String transactionStatus;
	//支付id
	private String payId;
	//支付类型（哪种三方）0-余额 1-微信 2-支付宝
	private String payStatus;
	//优惠类型
	private String discountTypes;
	//订单创建时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	//订单修改时间
	private String modifyTime;
	
	

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}


	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getRechargeORconsume() {
		return rechargeORconsume;
	}

	public void setRechargeORconsume(String rechargeORconsume) {
		this.rechargeORconsume = rechargeORconsume;
	}

	public Double getFictitiousFund() {
		return fictitiousFund;
	}

	public void setFictitiousFund(Double fictitiousFund) {
		this.fictitiousFund = fictitiousFund;
	}

	public Double getActualFund() {
		return actualFund;
	}

	public void setActualFund(Double actualFund) {
		this.actualFund = actualFund;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
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

	public String getDiscountTypes() {
		return discountTypes;
	}

	public void setDiscountTypes(String discountTypes) {
		this.discountTypes = discountTypes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	
}
