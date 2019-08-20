/**
 * 
 */
package com.bool.carshare.service;

import java.util.List;

import com.bool.carshare.bean.UserBean;

/**
 * IUserService
 * @author wangw
 */
public interface IUserService {
	public UserBean getUser(Object para);
	
	public List<UserBean> getUserList();
}