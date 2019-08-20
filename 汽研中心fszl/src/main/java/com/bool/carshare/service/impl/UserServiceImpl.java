/**
 * 
 */
package com.bool.carshare.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bool.carshare.bean.UserBean;
import com.bool.carshare.service.IUserService;

/**
 * UserServiceImpl
 * @author wangw
 */
@Service
public class UserServiceImpl implements IUserService{
	@Override
	public UserBean getUser(Object para) {
		
		
		return null;
	}
	
	@Override
	public List<UserBean> getUserList() {
		UserBean user1 = new UserBean();
		user1.setID("001");
		user1.setName("Jack");
		user1.setGender("Male");
		user1.setAge(27);
		
		UserBean user2 = new UserBean();
		user2.setID("002");
		user2.setName("Rose");
		user2.setGender("Female");
		user2.setAge(24);
		
		List<UserBean> userList = new ArrayList<UserBean>();
		userList.add(user1);
		userList.add(user2);
		
		return userList;
	}
}