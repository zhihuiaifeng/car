/**
 * 
 */
package com.bool.carshare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.bean.UserBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.LogInfo;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.mapper.LogInfoMapper;
import com.bool.carshare.service.LogInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.DateUtil;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.UserUtil;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;

/**
 * LogInfoServiceImpl
 * @author wangw
 */
@Service
public class LogInfoServiceImpl implements LogInfoService{
	@Autowired
	private LogInfoMapper logInfoMapper;
	
	/**
	 * 保存日志
	 * @param logInfo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional
	public Result saveLogInfo(LogInfo logInfo, WebObject webObject) {
		String sessionID = webObject.getSessionID();
		
		Object userInfo = UserUtil.getUserInfo(sessionID);
		ManageInfo manageInfo = (ManageInfo) userInfo;
		
		UserBean operator = new UserBean();
		operator.setID(manageInfo.getManageId().toString());
		operator.setName(manageInfo.getManageAllName());
		
		logInfo.setOperator(operator);
		logInfo.setOperateDateTime(DateUtil.getCurrentDate());
		
		int saveCount = this.logInfoMapper.saveLogInfo(logInfo);
		if(saveCount > 0) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	/**
	 * 查询日志
	 * @param pageRequest
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Result getLogList(PageRequest<LogInfo> pageRequest) {
		//判断条件是否为空
		if(!Assert.isNull(pageRequest)){
			//创建验证器
			Validator validator = new Validator();
			//添加拦截条数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), Message.PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			//添加拦截行数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), Message.ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateResult = null;
			if (Assert.isNull((validateResult = validator.fristError()))) {
				List<LogInfo> List = this.logInfoMapper.getLogListOnSpecificPage(pageRequest);
				Integer total = this.logInfoMapper.getLogListTotalCount(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, List);
				return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.PAGE_PARAM_INVALID, null);
	}
}