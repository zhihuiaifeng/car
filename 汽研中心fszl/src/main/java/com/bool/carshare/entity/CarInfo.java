package com.bool.carshare.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * carInfo entity -> create date 2017/6/15
 * 
 * @author tzw
 *
 */
public class CarInfo {

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
	
	//门锁状态(X)
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

	//保养提醒(0-需要保养，1-无需保养)
	private String ensureWarn;
	
	//排序的列名
	private String sortName;
	
	//排序的顺序
	private String sortOrder;
	
	/**
	 * 附件
	 */
	private String attachment;
	
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

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

	//终端号，传入的就是这个数据。
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

	public Date getModifyTime() {
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getEnsureWarn() {
		return ensureWarn;
	}

	public void setEnsureWarn(String ensureWarn) {
		this.ensureWarn = ensureWarn;
	}
	
}