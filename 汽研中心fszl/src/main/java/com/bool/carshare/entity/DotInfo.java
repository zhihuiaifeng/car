package com.bool.carshare.entity;

import java.io.Serializable;
import java.util.Date;

public class DotInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5851817633845875725L;

	// 网点id
	private Integer dot_id;
	// 经度
	private String gps_x;
	// 纬度
	private String gps_y;
	// 网点名字
	private String dot_name;
	// 网点地址ַ
	private String dot_address;
	// 网点图片
	private String dot_photo;
	// 停车位
	private Integer parking_lot;
	// 充电桩个数
	private Integer charge_number;
	// 运行时间
	private Date operate_time;	
	//车辆数
	private Integer cars;
	//可用车位
	private Integer park_avaliable;
	//所属城市
	private String dot_city;
	//营业开门时间
	private String openDoorTime;
	//营业关门时间
	private String closeDoorTime;
	//照片
	private String photo;
	
	public Integer getDot_id() {
		return dot_id;
	}
	public void setDot_id(Integer dot_id) {
		this.dot_id = dot_id;
	}
	public String getGps_x() {
		return gps_x;
	}
	public void setGps_x(String gps_x) {
		this.gps_x = gps_x;
	}
	public String getGps_y() {
		return gps_y;
	}
	public void setGps_y(String gps_y) {
		this.gps_y = gps_y;
	}
	public String getDot_name() {
		return dot_name;
	}
	public void setDot_name(String dot_name) {
		this.dot_name = dot_name;
	}
	public String getDot_address() {
		return dot_address;
	}
	public void setDot_address(String dot_address) {
		this.dot_address = dot_address;
	}
	public String getDot_photo() {
		return dot_photo;
	}
	public void setDot_photo(String dot_photo) {
		this.dot_photo = dot_photo;
	}
	public Integer getParking_lot() {
		return parking_lot;
	}
	public void setParking_lot(Integer parking_lot) {
		this.parking_lot = parking_lot;
	}
	public Integer getCharge_number() {
		return charge_number;
	}
	public void setCharge_number(Integer charge_number) {
		this.charge_number = charge_number;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	public Integer getCars() {
		return cars;
	}
	public void setCars(Integer cars) {
		this.cars = cars;
	}
	public Integer getPark_avaliable() {
		return park_avaliable;
	}
	public void setPark_avaliable(Integer park_avaliable) {
		this.park_avaliable = park_avaliable;
	}
	public String getDot_city() {
		return dot_city;
	}
	public void setDot_city(String dot_city) {
		this.dot_city = dot_city;
	}
	public String getOpenDoorTime() {
		return openDoorTime;
	}
	public void setOpenDoorTime(String openDoorTime) {
		this.openDoorTime = openDoorTime;
	}
	public String getCloseDoorTime() {
		return closeDoorTime;
	}
	public void setCloseDoorTime(String closeDoorTime) {
		this.closeDoorTime = closeDoorTime;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DotInfo(Integer dot_id, String gps_x, String gps_y, String dot_name, String dot_address, String dot_photo,
			Integer parking_lot, Integer charge_number, Date operate_time, Integer cars, Integer park_avaliable,
			String dot_city, String openDoorTime, String closeDoorTime) {
		super();
		this.dot_id = dot_id;
		this.gps_x = gps_x;
		this.gps_y = gps_y;
		this.dot_name = dot_name;
		this.dot_address = dot_address;
		this.dot_photo = dot_photo;
		this.parking_lot = parking_lot;
		this.charge_number = charge_number;
		this.operate_time = operate_time;
		this.cars = cars;
		this.park_avaliable = park_avaliable;
		this.dot_city = dot_city;
		this.openDoorTime = openDoorTime;
		this.closeDoorTime = closeDoorTime;
	}
	public DotInfo() {
		super();
	}
	
	
	
}
