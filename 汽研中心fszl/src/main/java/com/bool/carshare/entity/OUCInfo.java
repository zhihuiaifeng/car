package com.bool.carshare.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class OUCInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4317885904119588078L;

	// id
	private Integer cid;

	// 车型名称
	private String cmodel;

	// 牌照
	private String clicense;

	// 终端编号
	private String cterminal;

	// 车辆照片
	private String cphoto;

	// vin号
	private String cvin;

	// 电池剩余电量
	private Double soc;

	// 租用状态 1-已预约 2-未使用 3-正在租用 
	private Integer cstate;

	// 车辆创建时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date ccreateDate;

	// 车辆类型 1:油 2:电 3:混动
	private Integer ctype;

	// 车辆座位数
	private Integer cseatNumber;
 
	// 车辆总里程
	private Double ctotalMileage;

	// 运营阶段 总里程
	private Double crunMileage;

	// 车龄
	private Integer cage;
	
	//经度
	private String longitude;
	
	//纬度
	private String latitude;
	
	//运营状态(1-未使用 2-正在用)
	private String runState;
	
	//网点信息
	private DotInfo dotInfo;
	
	//续航里程
	private Double socMileage;
	
	//门锁状态
	private String lockState;
	
	//车门状态
	private String carDoorState;
	
	//后备箱状态（X）
	private String trunkState;
	
	//右车窗状态
	private String rcarWinState;
	//左车窗状态
	private String lcarWinState;
	
	//启动（授权）状态
	private String startupState;
	
	//刷新时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp modifyTime;

	//用户姓名
	private String realName;
	
	//车速
	private Double speed;
	
	//清洁时间
	private String clearTime;
	
	//每秒/钱
	private Double timeMoney;
	
	//每公里/钱
	private Double mileageMoney;
	
	//网点名称
	private String dotName;
	
	//投保日期
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date insuranceDate;
	
	//投保公司
	private String insuranceCompany;
	
	//投保电话
	private String insurancePhone;
	
	//上一次保养时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date lastMaintainDate;
	
	//上一次年检时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date lastAnnualCheckDate;
	//Order_Number订单编号
	private String order_Number;
	//Order_State订单状态：0-订单生产，1-订单计费，2-订单取消，3-订单完成，4-订单未付款 
	private String order_State;
	//StartTime开始时间
	private String startTime;
	//StartSite开始地点
	private String startSite;
	//EndTime结束时间
	private String endTime;
	//EndSite结束地点
	private String endSite;
	//Run_Length行驶里程
	private Double run_Length;
	//RunTime行驶时间
	private String runTime;
	//Expenses费用
	private Double expense;
	//Uid登录用户编号
	private Integer uid;
	//Terminal_id终端id
	private String Terminal_id;
	//carid车辆id
	private Integer carId;
	//备注
	private String remarks;
		
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
	
	//用户余额
	private Double balance;
	//押金状态 0-未支付  1-已支付
	private String cashPledge;
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
	//检索时间
	private String zoneTime;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCmodel() {
		return cmodel;
	}
	public void setCmodel(String cmodel) {
		this.cmodel = cmodel;
	}
	public String getClicense() {
		return clicense;
	}
	public void setClicense(String clicense) {
		this.clicense = clicense;
	}
	public String getCterminal() {
		return cterminal;
	}
	public void setCterminal(String cterminal) {
		this.cterminal = cterminal;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
	public String getCvin() {
		return cvin;
	}
	public void setCvin(String cvin) {
		this.cvin = cvin;
	}
	public Double getSoc() {
		return soc;
	}
	public void setSoc(Double soc) {
		this.soc = soc;
	}
	public Integer getCstate() {
		return cstate;
	}
	public void setCstate(Integer cstate) {
		this.cstate = cstate;
	}
	public Date getCcreateDate() {
		return ccreateDate;
	}
	public void setCcreateDate(Date ccreateDate) {
		this.ccreateDate = ccreateDate;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getCseatNumber() {
		return cseatNumber;
	}
	public void setCseatNumber(Integer cseatNumber) {
		this.cseatNumber = cseatNumber;
	}
	public Double getCtotalMileage() {
		return ctotalMileage;
	}
	public void setCtotalMileage(Double ctotalMileage) {
		this.ctotalMileage = ctotalMileage;
	}
	public Double getCrunMileage() {
		return crunMileage;
	}
	public void setCrunMileage(Double crunMileage) {
		this.crunMileage = crunMileage;
	}
	public Integer getCage() {
		return cage;
	}
	public void setCage(Integer cage) {
		this.cage = cage;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getRunState() {
		return runState;
	}
	public void setRunState(String runState) {
		this.runState = runState;
	}
	public DotInfo getDotInfo() {
		return dotInfo;
	}
	public void setDotInfo(DotInfo dotInfo) {
		this.dotInfo = dotInfo;
	}
	public Double getSocMileage() {
		return socMileage;
	}
	public void setSocMileage(Double socMileage) {
		this.socMileage = socMileage;
	}
	public String getLockState() {
		return lockState;
	}
	public void setLockState(String lockState) {
		this.lockState = lockState;
	}
	public String getCarDoorState() {
		return carDoorState;
	}
	public void setCarDoorState(String carDoorState) {
		this.carDoorState = carDoorState;
	}
	public String getTrunkState() {
		return trunkState;
	}
	public void setTrunkState(String trunkState) {
		this.trunkState = trunkState;
	}
	public String getRcarWinState() {
		return rcarWinState;
	}
	public void setRcarWinState(String rcarWinState) {
		this.rcarWinState = rcarWinState;
	}
	public String getLcarWinState() {
		return lcarWinState;
	}
	public void setLcarWinState(String lcarWinState) {
		this.lcarWinState = lcarWinState;
	}
	public String getStartupState() {
		return startupState;
	}
	public void setStartupState(String startupState) {
		this.startupState = startupState;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public Double getTimeMoney() {
		return timeMoney;
	}
	public void setTimeMoney(Double timeMoney) {
		this.timeMoney = timeMoney;
	}
	public Double getMileageMoney() {
		return mileageMoney;
	}
	public void setMileageMoney(Double mileageMoney) {
		this.mileageMoney = mileageMoney;
	}
	public String getDotName() {
		return dotName;
	}
	public void setDotName(String dotName) {
		this.dotName = dotName;
	}
	public Date getInsuranceDate() {
		return insuranceDate;
	}
	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getInsurancePhone() {
		return insurancePhone;
	}
	public void setInsurancePhone(String insurancePhone) {
		this.insurancePhone = insurancePhone;
	}
	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}
	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}
	public Date getLastAnnualCheckDate() {
		return lastAnnualCheckDate;
	}
	public void setLastAnnualCheckDate(Date lastAnnualCheckDate) {
		this.lastAnnualCheckDate = lastAnnualCheckDate;
	}
	public String getOrder_Number() {
		return order_Number;
	}
	public void setOrder_Number(String order_Number) {
		this.order_Number = order_Number;
	}
	public String getOrder_State() {
		return order_State;
	}
	public void setOrder_State(String order_State) {
		this.order_State = order_State;
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
	public Double getRun_Length() {
		return run_Length;
	}
	public void setRun_Length(Double run_Length) {
		this.run_Length = run_Length;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public Double getExpense() {
		return expense;
	}
	public void setExpense(Double expense) {
		this.expense = expense;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getTerminal_id() {
		return Terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		Terminal_id = terminal_id;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCashPledge() {
		return cashPledge;
	}
	public void setCashPledge(String cashPledge) {
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
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	public String getZoneTime() {
		return zoneTime;
	}
	public void setZoneTime(String zoneTime) {
		this.zoneTime = zoneTime;
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