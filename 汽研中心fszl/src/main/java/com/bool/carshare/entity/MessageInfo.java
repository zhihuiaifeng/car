package com.bool.carshare.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author yxy
 *
 */
public class MessageInfo {

	//报文id
	private Integer mid;
	//经度
	private String longitude;
	//纬度
	private String latitude;
	//车门
	private String carDoorState;
	//左车窗
	private String lcarWinState;
	//右车窗
	private String rcarWinState;
	//授权
	private String startupState;
	//更新时间
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp modifyTime;
	//终端编号
	private String cterminal;
	//里程
	private Double ctotalMileage;
	//车速
	private Double speed;
	//剩余电量
	private Double soc;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
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
	public String getCarDoorState() {
		return carDoorState;
	}
	public void setCarDoorState(String carDoorState) {
		this.carDoorState = carDoorState;
	}
	public String getLcarWinState() {
		return lcarWinState;
	}
	public void setLcarWinState(String lcarWinState) {
		this.lcarWinState = lcarWinState;
	}
	public String getRcarWinState() {
		return rcarWinState;
	}
	public void setRcarWinState(String rcarWinState) {
		this.rcarWinState = rcarWinState;
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
	public String getCterminal() {
		return cterminal;
	}
	public void setCterminal(String cterminal) {
		this.cterminal = cterminal;
	}
	public Double getCtotalMileage() {
		return ctotalMileage;
	}
	public void setCtotalMileage(Double ctotalMileage) {
		this.ctotalMileage = ctotalMileage;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getSoc() {
		return soc;
	}
	public void setSoc(Double soc) {
		this.soc = soc;
	}
	
	
}
