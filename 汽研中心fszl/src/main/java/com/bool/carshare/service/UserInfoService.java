package com.bool.carshare.service;


import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface UserInfoService {	
	
	
	Result saveUser(UserInfo user);
	
	Result login(String userName,String password);
	
	Result getUser(PageRequest<UserInfo> pageRequest);
	
//	Result uploadIdcard_a(MultipartFile file,Integer userId,String basePath,String httpPath);
	
	Result updateUser(UserInfo user);
	
	Result updateUserState(int userId,int state);
	
	public Result getUserInfoById(Integer userId);

	public Result outUserInfo(String userName);
	
	public Result upLoginState(String uname,String loginState);
	
	public Result updateMoney(UserInfo userInfo);
	
}
