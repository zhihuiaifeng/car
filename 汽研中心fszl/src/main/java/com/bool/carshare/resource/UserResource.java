package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.service.UserInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class UserResource {

	@Autowired
	private UserInfoService userInfoService;

	
	//新建用户信息
	@RequestMapping(value = "newuser", method = { RequestMethod.POST })
	public Result newuser(UserInfo userInfo) {
		Result result = userInfoService.saveUser(userInfo);
		return result;
	}

	
	//完善用户信息
	@RequestMapping(value = "pftuser", method = { RequestMethod.POST })
	public Result user(UserInfo user) {
		return userInfoService.updateUser(user);
	}

	//通过用户名密码查用户信息
	@RequestMapping(value = "user", method = { RequestMethod.POST })
	public Result user(String uname, String upwd) {
		return userInfoService.login(uname, upwd);
	}

	
	//查询所有用户信息
	@RequestMapping(value = "alluser", method = { RequestMethod.POST })
	public Result users(Integer row, Integer page, UserInfo condition) {
		PageRequest<UserInfo> pageRequest = new PageRequest<UserInfo>(row, page, condition);
		Result result = userInfoService.getUser(pageRequest);
		return result;
	}

	//审核成功
	@RequestMapping(value="user_state_ok",method={RequestMethod.POST})
	public Result userstate_ok(Integer uid){
		return userInfoService.updateUserState(uid, 2);
	}
	//审核驳回
	@RequestMapping(value="user_state_no",method={RequestMethod.POST})
	public Result userstate_no(Integer uid){
		return userInfoService.updateUserState(uid, 3);
	}
	//撤销授权
	@RequestMapping(value="user_state_init",method={RequestMethod.POST})
	public Result userstate_init(Integer userId){
		return userInfoService.updateUserState(userId, 1);
	}
	//上传照片
//	@RequestMapping(value = "user_idcard_a", method = { RequestMethod.POST })
//	public Result user_idcard_a(MultipartFile file, HttpServletRequest request) {
//		//测试时使用
//		String ip = request.getLocalAddr();    //取得服务器IP 
//		int port = request.getLocalPort();		//取得服务器端口
//		String path = "http://"+ip+":"+port;
//		//正式时使用
////		String path = "http://139.196.86.35:8080";
//		String path2 = request.getServletContext().getRealPath("upload");
//		return userInfoService.uploadIdcard_a(file, path2, path);
//	}
	//通过UserID查询用户信息
	@RequestMapping(value = "userById", method = { RequestMethod.POST })
	public Result getUserInfoById(Integer uid){
		return userInfoService.getUserInfoById(uid);
	}
	//登出清理缓存
	@RequestMapping(value = "outuser", method = { RequestMethod.POST })
	public Result outUserInfo(String uname){
		return userInfoService.outUserInfo(uname);
	}
	//修改登陆状态
	@RequestMapping(value = "uplogin", method = { RequestMethod.POST })
	public Result upLoginState(String uname, String loginState){
		return userInfoService.upLoginState(uname, loginState);
	}
	

}
