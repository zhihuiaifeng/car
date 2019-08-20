package com.bool.carshare.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.util.PageRequest;
/**
 *  user mapper .
 * @author tzw
 *
 */
@Repository("userInfoDao")
@Mapper
public interface UserInfoMapper {
	
	// register 
	void saveUser(UserInfo userInfo);
	
	// user login 
	UserInfo login(@Param("username")String username,@Param("password")String password);
	
	// get user by condition .
	List<UserInfo> getUser(PageRequest<UserInfo> pageRequest);
	
	// get user count by condition ..
	int getUserCount(UserInfo userInfo);
	
	// update user -
	void updateUserInfo(UserInfo userInfo);
	
	// getUser by Id
	
	UserInfo getUserInfoById(@Param("userId")Serializable id);
	
	// update idcard_photo_a
	
	void uploadIdcardPhoto_a(UserInfo user);
	
	// update idcard_photo_b
	
	void uploadIdcardPhoto_b(UserInfo user);
	
	// update driverPhoto_a
	
	void uploadDriverPhoto_a(UserInfo user);
	
	// update driverPhoto_b
	
	void uploadDriverPhoto_b(UserInfo user);
	
	//消费
	
	int updateBalances(UserInfo userInfo);
	
	//修改登陆标识
	
	int upLoginState(@Param("uname")String uname,@Param("loginState")String loginState);
	

}
