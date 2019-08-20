package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.PAGE_INVALID;
import static com.bool.carshare.util.Message.ROW_INVALID;
import static com.bool.carshare.util.Message.USER_ID_INVALID;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.entity.PayInfo;
import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.mapper.PayInfoMapper;
import com.bool.carshare.mapper.UserInfoMapper;
import com.bool.carshare.service.PayInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueContaisInterceptor;


@Service("payInfoService")
public class PayInfoServiceImpl implements PayInfoService {

	@Autowired
	private UserInfoMapper userInfoDao;
	@Autowired
	private PayInfoMapper payInfoMapper;
	
	/**
	 * 新建流水
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result savePayInfo(PayInfo payInfo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String id = sdf.format(new Date()) + payInfo.getUid().toString();
		payInfo.setPid(id);
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(payInfo.getPid(), Message.LAST_CONSUMPTION_ERROR),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getUid(), USER_ID_INVALID),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getRechargeORconsume(), Message.RECHARGE_OR_CONSUME),
				new NotNullInterceptor(), new ValueContaisInterceptor("0","1","2"));
		validator.addInterceptor(new ValidateHolder(payInfo.getFictitiousFund(), Message.FICTITIOUS_FUND_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getActualFund(), Message.ACTUAL_FUND_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getTransactionStatus(), Message.TRANSACTION_STATUS_NULL),
				new NotNullInterceptor(), new ValueContaisInterceptor("0","1"));
//		validator.addInterceptor(new ValidateHolder(payInfo.getPayId(), Message.PAY_ID_NULL),
//				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getPayStatus(),Message.PAY_STATUS_NULL),
				new NotNullInterceptor(), new ValueContaisInterceptor("0","1","2"));
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			payInfoMapper.savePayInfo(payInfo);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, payInfo);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}
	/**
	 * 按用户id查询账单
	 */
	@Transactional(readOnly = true)
	public Result findAllPayInfoByUid(PageRequest<PayInfo> pageRequest) {
		Validator validator = new Validator();
		//添加拦截条数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		//添加拦截行数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			List<PayInfo> paylist = payInfoMapper.findAllPayInfoByUid(pageRequest);
			int total = payInfoMapper.findCountByUid(pageRequest.getCondition());
			PageResponse rp =  new PageResponse(total, paylist);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}


	/**
	 * 余额支付
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result nbsavePayInfo(PayInfo payInfo,Integer disid){
		//生成流水id
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String id = sdf.format(new Date()) + payInfo.getUid().toString();
		payInfo.setPid(id);
		//校验
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(payInfo.getPid(), Message.LAST_CONSUMPTION_ERROR),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getUid(), USER_ID_INVALID),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getRechargeORconsume(), Message.RECHARGE_OR_CONSUME),
				new NotNullInterceptor(), new ValueContaisInterceptor("1"));
		validator.addInterceptor(new ValidateHolder(payInfo.getFictitiousFund(), Message.FICTITIOUS_FUND_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getActualFund(), Message.ACTUAL_FUND_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(payInfo.getTransactionStatus(), Message.TRANSACTION_STATUS_NULL),
				new NotNullInterceptor(), new ValueContaisInterceptor("1"));
		validator.addInterceptor(new ValidateHolder(payInfo.getPayStatus(),Message.PAY_STATUS_NULL),
				new NotNullInterceptor(), new ValueContaisInterceptor("0"));
		validator.addInterceptor(new ValidateHolder(payInfo.getPayment(), Message.PAYMENT_NULL), 
				new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull(validateHolder = validator.fristError())) {
			UserInfo userInfo = userInfoDao.getUserInfoById(payInfo.getUid());
			payInfoMapper.savePayInfo(payInfo);
			//余额与订单金额的判断
			if(userInfo.getBalance()-payInfo.getPayment()>0){
				//累计消费
				userInfo.setExpenses(userInfo.getExpenses()+payInfo.getPayment());
				//最后一笔账单id
				userInfo.setLastConsumption(id);
				userInfoDao.updateBalances(userInfo);
				payInfo.setTransactionStatus("0");
				payInfoMapper.updateSuccessState(payInfo);
				return Result.ResultBuilder.buildSuccessResult(Message.OK, payInfo);
			}else{
				return Result.ResultBuilder.buildFailerResult(Message.BALANCE_NOT_FUND, null);
			}
		} else{
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}
		
	}
	
	/**
	 * 查询充值账单
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result findChange(PageRequest<PayInfo> pageRequest) {
		Validator validator = new Validator();
		//添加拦截条数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		//添加拦截行数非空且不能超过最大
		validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			List<PayInfo> paylist = payInfoMapper.findChange(pageRequest);
			int total = payInfoMapper.findChangeCount(pageRequest.getCondition());
			PageResponse rp =  new PageResponse(total, paylist);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}

}
