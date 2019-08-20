package com.bool.carshare.service;

import java.util.List;

import com.bool.carshare.bean.LoginBean;
import com.bool.carshare.bean.ResultBean;
import com.bool.carshare.bean.TokenBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.entity.NodeInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface ManageInfoService {
	//查看所有管理员
	public Result findAllAdmin(PageRequest<ManageInfo> pageRequest);
	//新建管理员
	public Result confirmAdmin(ManageInfo manageInfo, WebObject webObject);
	//修改管理员信息
	public Result updateAdminInfo(ManageInfo manageInfo, WebObject webObject);
	
	/**
	 * 修改密码
	 * @param manageID
	 * @param newPassword
	 * @return
	 */
	public Result updatePassword(Integer manageID, String newPassword, WebObject webObject);
	
	//删除
	public Result delete(Integer manageId, WebObject webObject);
	//获得管理员的信息
	public ManageInfo getManageInfo(WebObject webObject);
	//获得节点的信息
	public List<NodeInfo> getNodeList(WebObject webObject);
	//获得Token
	public TokenBean getToken(WebObject webObject);
	//登陆
	public LoginBean login(WebObject webObject);
	//退出
	public LoginBean logout(WebObject webObject);
	//是否登录
	public LoginBean isLogin(WebObject webObject);
	//是否可编辑
	public ResultBean isEditable(WebObject webObject);
}
