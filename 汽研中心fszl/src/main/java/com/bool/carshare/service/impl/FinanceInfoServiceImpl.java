package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.PAGE_INVALID;
import static com.bool.carshare.util.Message.ROW_INVALID;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.entity.FinanceInfo;
import com.bool.carshare.entity.PayInfo;
import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.mapper.FinanceInfoMapper;
import com.bool.carshare.mapper.PayInfoMapper;
import com.bool.carshare.mapper.UserInfoMapper;
import com.bool.carshare.service.FinanceInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
@Service("financeInfoService")
public class FinanceInfoServiceImpl implements FinanceInfoService {

	@Autowired
	private PayInfoMapper payInfoMapper;
	@Autowired
	private FinanceInfoMapper financeInfoMapper;
	@Autowired
	private UserInfoMapper userInfoDao;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Result saveFinanceInfo(Integer uid) {
		//查询到交押金的那条账单
		PayInfo payInfo = payInfoMapper.findCountByUidRC(uid, "2");
		if(payInfo != null){
			//创建账单管理数据
			FinanceInfo financeInfo = new FinanceInfo();
			//账户订单号
			financeInfo.setPid(payInfo.getPid());
			//账户类型（1-微信，2-支付宝）
			financeInfo.setPayStatus(payInfo.getPayStatus());
			//退款单号
			financeInfo.setPayId(payInfo.getPayId());
			//退款金额
			financeInfo.setPayment(payInfo.getPayment());
			//处理状态
			financeInfo.setStatus(1);
			//用户编号
			financeInfo.setOther(uid.toString());
			//退款流水单号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String id = sdf.format(new Date()) + payInfo.getUid().toString();
			financeInfo.setNewPayId(id);
			//新建财务单
			financeInfoMapper.saveFinanceInfo(financeInfo);
			//将退款订单状态修改
			payInfo.setTransactionStatus("2");
			payInfoMapper.updateSuccessState(payInfo);
			//新建退款流水单
			payInfo.setRechargeORconsume("3");
			payInfo.setTransactionStatus("1");
			payInfo.setPid(id);
			payInfoMapper.savePayInfo(payInfo);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, payInfo);
		}else{
			return Result.ResultBuilder.buildFailerResult(Message.CASH_PLEDGE_ERROR, null);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result findFinanceInfo(PageRequest<FinanceInfo> pageRequest) {
		Validator validator = new Validator();
		//添加拦截条数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		//添加拦截行数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			List<FinanceInfo> financeInfos = financeInfoMapper.findFinanceInfo(pageRequest);
			int total = financeInfoMapper.findFinanceInfoCount(pageRequest.getCondition());
			PageResponse rp =  new PageResponse(total, financeInfos);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result upFinanceInfoStatus(Integer status, String newPayId) {
		Validator validator = new Validator();
		//添加拦截条数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(status, Message.FINANCE_STATUS_ERROR),
				 new NotNullInterceptor());
		//添加拦截行数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(newPayId, Message.PAY_ID_NULL),
				 new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			financeInfoMapper.upFinanceInfoStatus(status, newPayId);
			if(status == 3){
				PayInfo payInfo = payInfoMapper.findOnePayById(newPayId);
				payInfo.setTransactionStatus("0");
				UserInfo userInfo = userInfoDao.getUserInfoById(payInfo.getUid());
				userInfo.setCashPledge(0.00);
				userInfoDao.updateBalances(userInfo);
				payInfoMapper.updateSuccessState(payInfo);
			}
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}else{
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}
	}

}

	