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
import com.bool.carshare.entity.FormInfo;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.mapper.CarInfoMapper;
import com.bool.carshare.mapper.FormInfoMapper;
import com.bool.carshare.service.FormInfoService;
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
 * FormInfoServiceImpl
 * @author wangw
 */
@Service
public class FormInfoServiceImpl implements FormInfoService{
	@Autowired
	private FormInfoMapper formInfoMapper;
	
	@Autowired
	private CarInfoMapper carInfoDao;
	
	/**
	 * 保存工单
	 * @param formInfo
	 * @param webObject
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result saveFormInfo(FormInfo formInfo, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(formInfo.getPlateNumber(), Message.CAR_CLICENSE_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(formInfo.getCharger().getName(), Message.FORM_CHARGER_NULL),
				new NotNullInterceptor());
		
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult=validator.fristError()))) {
			String sessionID = webObject.getSessionID();
			
			Object userInfo = UserUtil.getUserInfo(sessionID);
			ManageInfo manageInfo = (ManageInfo) userInfo;
			
			UserBean recorder = new UserBean();
			recorder.setID(manageInfo.getManageId().toString());
			recorder.setName(manageInfo.getManageAllName());
			
			formInfo.setRecorder(recorder);
			formInfo.setStatus(0);
			int saveCount = this.formInfoMapper.saveFormInfo(formInfo);
			if(saveCount > 0) {
				this.carInfoDao.setCarRunState(formInfo.getPlateNumber(), "1");
				
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 查询工单
	 * @param pageRequest
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Result getFormList(PageRequest<FormInfo> pageRequest) {
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
				List<FormInfo> List = this.formInfoMapper.getFormListOnSpecificPage(pageRequest);
				Integer total = this.formInfoMapper.getFormListTotalCount(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, List);
				return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(Message.PAGE_PARAM_INVALID, null);
	}
	
	/**
	 * 更新工单
	 * @param formInfo
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result updateFormInfo(FormInfo formInfo, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(formInfo.getPlateNumber(), Message.CAR_CLICENSE_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(formInfo.getCharger().getName(), Message.FORM_CHARGER_NULL),
				new NotNullInterceptor());
		
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult=validator.fristError()))) {
			int updateCount = this.formInfoMapper.updateFormInfo(formInfo);
			
			if(updateCount > 0) {
				Integer status = formInfo.getStatus();
				if(status != null && status == 2) {
					this.carInfoDao.setCarRunState(formInfo.getPlateNumber(), "2");
				}else {
					this.carInfoDao.setCarRunState(formInfo.getPlateNumber(), "1");
				}
				
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 删除工单
	 * @param formIDList
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result deleteFormInfo(int[] formIDList, WebObject webObject) {
		int deleteCount = this.formInfoMapper.deleteFormInfo(formIDList);
		
		if(deleteCount > 0) {
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}else {
			return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
		}
	}
}