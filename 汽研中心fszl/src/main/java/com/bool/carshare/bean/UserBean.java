/**
 * 
 */
package com.bool.carshare.bean;

/**
 * UserBean
 * @author wangw
 */
public class UserBean {
	/**
	 * ID
	 */
	private String ID;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 年龄
	 */
	private int age;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}