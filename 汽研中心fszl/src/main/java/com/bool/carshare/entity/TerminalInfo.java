/**
 * 
 */
package com.bool.carshare.entity;

/**
 * TerminalInfo
 * @author wangw
 */
public class TerminalInfo extends RecordInfo{
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * SIM卡号
	 */
	private String simCardNumber;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	/**
	 * 软件版本
	 */
	private String softwareVersion;
	
	/**
	 * 硬件版本
	 */
	private String hardwareVersion;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSimCardNumber() {
		return simCardNumber;
	}

	public void setSimCardNumber(String simCardNumber) {
		this.simCardNumber = simCardNumber;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
}