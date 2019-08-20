package com.bool.carshare.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  user info enity 
 * @author tzw
 */

public class UserInfo implements Serializable{

	/**
	 * serializable id .
	 */
	private static final long serialVersionUID = 689545920944131738L;

	// 用户id
	private Integer uid; 
	// 用户名称 
	private String uname;
	// 用户身份证号
	private String idcard;
	// 用户性别
	private String sex;
	// 用户年龄
	private Integer age;
	// 驾照编号
	private String driverNo;
	// 身份证照片正面路径
	private String idcard_photo_a;
	// 身份证照片反面路径
	private String idcard_photo_b;
	// 驾照照片正面路径
	private String driver_photo_a;
	// 驾照照片反面路径
	private String driver_photo_b;
	// 用户状态1:未审核 2:通过 3:驳回
	private Integer ustate;
	// 手机号
	private String photo;
	// 密码
	private String upwd;
	// 创建日期
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date create_date;
	//用户姓名
	private String realName;
	//用户余额
	private Double balance;
	//押金状态 0-未支付  1-已支付
	private Double cashPledge;
	//累计充值
	private Double recharges;
	//累计消费
	private Double expenses;
	//累计优惠
	private Double favorables;
	//最后一笔消费ID
	private String lastConsumption;
	//登录标识 0-正在用 1-没用
	private String loginState;
	//邀请码
	private String iCode;
	//资料状态 0-未完善 1-已完善
	private String dataState;
	
	
	
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginSt) {
		this.loginState = loginSt;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public String getIdcard_photo_a() {
		return idcard_photo_a;
	}
	public void setIdcard_photo_a(String idcard_photo_a) {
		this.idcard_photo_a = idcard_photo_a;
	}
	public String getIdcard_photo_b() {
		return idcard_photo_b;
	}
	public void setIdcard_photo_b(String idcard_photo_b) {
		this.idcard_photo_b = idcard_photo_b;
	}
	public String getDriver_photo_a() {
		return driver_photo_a;
	}
	public void setDriver_photo_a(String driver_photo_a) {
		this.driver_photo_a = driver_photo_a;
	}
	public String getDriver_photo_b() {
		return driver_photo_b;
	}
	public void setDriver_photo_b(String driver_photo_b) {
		this.driver_photo_b = driver_photo_b;
	}
	public Integer getUstate() {
		return ustate;
	}
	public void setUstate(Integer ustate) {
		this.ustate = ustate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getCashPledge() {
		return cashPledge;
	}
	public void setCashPledge(Double cashPledge) {
		this.cashPledge = cashPledge;
	}
	public Double getRecharges() {
		return recharges;
	}
	public void setRecharges(Double recharges) {
		this.recharges = recharges;
	}
	public Double getExpenses() {
		return expenses;
	}
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}
	public Double getFavorables() {
		return favorables;
	}
	public void setFavorables(Double favorables) {
		this.favorables = favorables;
	}
	public String getLastConsumption() {
		return lastConsumption;
	}
	public void setLastConsumption(String lastConsumption) {
		this.lastConsumption = lastConsumption;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
