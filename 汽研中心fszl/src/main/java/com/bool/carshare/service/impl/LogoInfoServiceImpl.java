/**
 * 
 */
package com.bool.carshare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.bean.UserBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.LogoInfo;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.mapper.LogoInfoMapper;
import com.bool.carshare.service.LogoInfoService;
import com.bool.carshare.util.DateUtil;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.UserUtil;

/**
 * LogoInfoServiceImpl
 * @author wangw
 */
@Service
public class LogoInfoServiceImpl implements LogoInfoService{
	@Autowired
	private LogoInfoMapper logoInfoMapper;
	
	/**
	 * 更新Logo
	 * @param logoInfo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional
	public Result updateLogoInfo(LogoInfo logoInfo, WebObject webObject) {
		String sessionID = webObject.getSessionID();
		
		Object userInfo = UserUtil.getUserInfo(sessionID);
		ManageInfo manageInfo = (ManageInfo) userInfo;
		
		this.logoInfoMapper.deleteLogoInfo(manageInfo.getManageId());
		
		UserBean user = new UserBean();
		user.setID(manageInfo.getManageId().toString());
		user.setName(manageInfo.getManageAllName());
		
		logoInfo.setUser(user);
		logoInfo.setUploadDate(DateUtil.getCurrentDate());
		
		int saveCount = this.logoInfoMapper.saveLogoInfo(logoInfo);
		if(saveCount > 0) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, logoInfo);
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	/**
	 * 查询Logo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Result getLogoInfo(WebObject webObject) {
		String sessionID = webObject.getSessionID();
		
		Object userInfo = UserUtil.getUserInfo(sessionID);
		ManageInfo manageInfo = (ManageInfo) userInfo;
		
		LogoInfo logoInfo = this.logoInfoMapper.getLogoInfo(manageInfo.getManageId());
		
		return Result.ResultBuilder.buildSuccessResult(Message.OK, logoInfo);
	}
	
	/**
	 * 删除Logo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional
	public Result deleteLogoInfo(WebObject webObject) {
		String sessionID = webObject.getSessionID();
		
		Object userInfo = UserUtil.getUserInfo(sessionID);
		ManageInfo manageInfo = (ManageInfo) userInfo;
		
		int deleteCount = this.logoInfoMapper.deleteLogoInfo(manageInfo.getManageId());
		if(deleteCount > 0) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
}