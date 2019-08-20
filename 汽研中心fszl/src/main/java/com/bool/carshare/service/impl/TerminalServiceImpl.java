/**
 * 
 */
package com.bool.carshare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.bean.UserBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.entity.TerminalInfo;
import com.bool.carshare.mapper.TerminalInfoMapper;
import com.bool.carshare.service.TerminalService;
import com.bool.carshare.util.Assert;
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
 * TerminalServiceImpl
 * @author wangw
 */
@Service
public class TerminalServiceImpl implements TerminalService{
	@Autowired
	private TerminalInfoMapper terminalInfoMapper;
	
	/**
	 * 保存终端
	 * @param terminalInfo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result saveTerminalInfo(TerminalInfo terminalInfo, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(terminalInfo.getCode(), Message.TERMINAL_CODE_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(terminalInfo.getSimCardNumber(), Message.TERMINAL_SIM_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(terminalInfo.getPlateNumber(), Message.CAR_CLICENSE_NULL),
				new NotNullInterceptor());
		
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult=validator.fristError()))) {
			String sessionID = webObject.getSessionID();
			
			Object userInfo = UserUtil.getUserInfo(sessionID);
			ManageInfo manageInfo = (ManageInfo) userInfo;
			
			UserBean recorder = new UserBean();
			recorder.setID(manageInfo.getManageId().toString());
			recorder.setName(manageInfo.getManageAllName());
			
			terminalInfo.setRecorder(recorder);
			
			int saveCount = this.terminalInfoMapper.saveTerminalInfo(terminalInfo);
			if(saveCount > 0) {
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 查询终端
	 * @param pageRequest
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Result getTerminalList(PageRequest<TerminalInfo> pageRequest) {
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
				List<TerminalInfo> List = this.terminalInfoMapper.getTerminalListOnSpecificPage(pageRequest);
				Integer total = this.terminalInfoMapper.getTerminalListTotalCount(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, List);
				return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(Message.PAGE_PARAM_INVALID, null);
	}
	
	/**
	 * 更新终端
	 * @param terminalInfo
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result updateTerminalInfo(TerminalInfo terminalInfo, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(terminalInfo.getCode(), Message.TERMINAL_CODE_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(terminalInfo.getSimCardNumber(), Message.TERMINAL_SIM_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(terminalInfo.getPlateNumber(), Message.CAR_CLICENSE_NULL),
				new NotNullInterceptor());
		
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult=validator.fristError()))) {
			int updateCount = this.terminalInfoMapper.updateTerminalInfo(terminalInfo);
			
			if(updateCount > 0) {
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 删除终端
	 * @param terminalIDList
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result deleteTerminalInfo(int[] terminalIDList, WebObject webObject) {
		int deleteCount = this.terminalInfoMapper.deleteTerminalInfo(terminalIDList);
		
		if(deleteCount > 0) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}else {
			return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
		}
	}
	
	/**
	 * 获得某一终端的信息
	 * @param terminalID
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Result getTerminalInfo(int terminalID) {
		Validator validator = new Validator();
		
		validator.addInterceptor(new ValidateHolder(terminalID, Message.CAR_ID_NULL),
				new NotNullInterceptor());
		
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult=validator.fristError()))) {
			TerminalInfo terminalInfo = this.terminalInfoMapper.getTerminalInfo(terminalID);
			
			return Result.ResultBuilder.buildSuccessResult(Message.OK, terminalInfo);
		}else {
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}
}