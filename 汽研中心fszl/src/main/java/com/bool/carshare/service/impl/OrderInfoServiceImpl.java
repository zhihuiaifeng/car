package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.CAR_ID_NULL;
import static com.bool.carshare.util.Message.END_SITE_NULL;
import static com.bool.carshare.util.Message.ERROR;
import static com.bool.carshare.util.Message.MODIFY_ORDER_ERROR;
import static com.bool.carshare.util.Message.OK;
import static com.bool.carshare.util.Message.ORDER_NUMBER_NULL;
import static com.bool.carshare.util.Message.ORDER_STATE_ERROR;
import static com.bool.carshare.util.Message.PAGE_INVALID;
import static com.bool.carshare.util.Message.PAGE_PARAM_INVALID;
import static com.bool.carshare.util.Message.ROW_INVALID;
import static com.bool.carshare.util.Message.RUNTIME_ERROR;
import static com.bool.carshare.util.Message.STASR_SITE_NULL;
import static com.bool.carshare.util.Message.UID_NULL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.entity.DotInfo;
import com.bool.carshare.entity.OUCInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.mapper.CarInfoMapper;
import com.bool.carshare.mapper.DotInfoMapper;
import com.bool.carshare.mapper.OrderInfoMapper;
import com.bool.carshare.service.OrderInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.RunTimeByOrder;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
import com.bool.carshare.util.validate.interceptors.RunTimeInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueContaisInterceptor;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Autowired
	private DotInfoMapper dotInfoDao;
	
	@Autowired
	private CarInfoMapper carInfoDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Result confirmOrder(OrderInfo orderInfo,Integer dot_id,String realName, Double crunMileage) {
		//查询是否有未完成的订单
		int orders = orderInfoMapper.getOrderDataCountByOrderNum(orderInfo.getUid());
		if(orders != 0){
			return Result.ResultBuilder.buildFailerResult(Message.ORDER_COUNT_ERROR,false);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			//创建用户订单号
			//String orferNum = sdf.format(orderInfo.getStartTime()) + orderInfo.getUid().toString();
			String orferNum = sdf.format(new Date()) + orderInfo.getUid().toString();
			
			orderInfo.setOrderNumber(orferNum);
			//订单状态
			orderInfo.setOrderState("0");
			
			//创建验证器
			Validator validator = new Validator();
			//添加拦截订单编号不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getOrderNumber(), ORDER_NUMBER_NULL),
					new NotNullInterceptor());  
			//添加拦截订单状态0
			validator.addInterceptor(new ValidateHolder(orderInfo.getOrderState(), ORDER_STATE_ERROR),
					new ValueContaisInterceptor("0"),new NotNullInterceptor());
			//添加拦截开始地点不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getStartSite(), STASR_SITE_NULL),
					new NotNullInterceptor());
			//添加拦截停车地点不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getEndSite(), END_SITE_NULL),
					new NotNullInterceptor());
			//添加拦截用户登录不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getUid(), UID_NULL),new NotNullInterceptor());
			
			//添加拦截车辆id不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getCarId(), CAR_ID_NULL),
					new NotNullInterceptor());
			ValidateHolder validateResult = null;
			if (Assert.isNull((validateResult = validator.fristError()))) {
				//将数据插入数据库
				orderInfoMapper.confirmOrder(orderInfo);
				//同步修改车辆表数据（已预约）
				CarInfo carInfo = new CarInfo();
				DotInfo dotInfo = new DotInfo();
				dotInfo.setDot_id(dot_id);
				carInfo.setDotInfo(dotInfo);
				carInfo.setCid(orderInfo.getCarId());
				//将车辆状态改为已预约状态
				carInfo.setCstate(1);
				//修改使用人
				carInfo.setRealName(realName);
				carInfo.setCrunMileage(crunMileage);
				carInfoDao.updateCarInfoById(carInfo);
				//同步修改网点状态
				int cars = carInfoDao.findCarsByDotId(dot_id);
				DotInfo dot = dotInfoDao.getDotInfoById(dot_id);
				dot.setCars(cars);
				dot.setPark_avaliable((dot.getParking_lot())-cars);
				dotInfoDao.updateDotInfo(dot);
				return Result.ResultBuilder.buildSuccessResult(OK, orderInfo);
			}
			else{
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), false);
			}
		}
	}

	@Transactional(readOnly=true)
	public Result getOrderDataByUserID(PageRequest<OrderInfo> pageRequest) {
		//判断条件是否为空
		if(!Assert.isNull(pageRequest)){
			//创建验证器
			Validator validator = new Validator();
			//添加拦截条数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			//添加拦截行数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateResult = null;
			if (Assert.isNull((validateResult = validator.fristError()))) {
				List<OrderInfo> orderList = orderInfoMapper.getOrderDataByUserID(pageRequest);
				int total = orderInfoMapper.getOrderDataCount(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, orderList);
				return Result.ResultBuilder.buildSuccessResult(OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(PAGE_PARAM_INVALID, null);
	}

	@Transactional(readOnly = true)
	public Result getOrderDataByOrderNum(String orderNumber) {
		Validator validator = new Validator();
		//添加拦截订单编号不为空
		validator.addInterceptor(new ValidateHolder(orderNumber,ORDER_NUMBER_NULL), 
				new NotNullInterceptor());
		//判断是否出错
		ValidateHolder validateHolder = null;
		if(Assert.isNull((validateHolder = validator.fristError()))){
			//执行查询
			OrderInfo orderInfo = orderInfoMapper.getOrderDataByOrderNum(orderNumber);
			return Result.ResultBuilder.buildSuccessResult(OK, orderInfo);
		}else{
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(),null );
		}
		
	}

	@Transactional(readOnly = true)
	public Result getOrderDataAndStampByOrderNum(Integer uid) {
		Validator validator = new Validator();
		//添加拦截用户登录不为空
		validator.addInterceptor(new ValidateHolder(uid, UID_NULL),new NotNullInterceptor());
		//判断是否出错
		ValidateHolder validateHolder = null;
		if(Assert.isNull((validateHolder = validator.fristError()))){
			//执行查询
			OrderInfo orderInfo = orderInfoMapper.getOrderDataAndStampByOrderNum(uid);
			return Result.ResultBuilder.buildSuccessResult(OK, orderInfo);
		}else{
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(),null );
		}
	}

	//修改订单
	@Transactional(propagation = Propagation.REQUIRED)
	public Result modifyOrder(OrderInfo orderInfo,Integer dot_id,String realName) {
		 if(!Assert.isNull(orderInfo)){
			 if(orderInfo.getStartTime() != null && orderInfo.getEndTime() != null){
				 String runTime = RunTimeByOrder.getTimeDifference(orderInfo.getStartTime(), orderInfo.getEndTime());
				 orderInfo.setRunTime(runTime);
			 }
			 //创建验证器
			Validator validator = new Validator();
			//添加拦截订单编号不为空
			validator.addInterceptor(new ValidateHolder(orderInfo.getOrderNumber(), ORDER_NUMBER_NULL),
					new NotNullInterceptor());
			//添加拦截订单状态 1 2 3
			validator.addInterceptor(new ValidateHolder(orderInfo.getOrderState(), ORDER_STATE_ERROR),
					new ValueContaisInterceptor("1","2","3","4","5"),new NotNullInterceptor());
			//runtime
			if(orderInfo.getRunTime() != null){
				validator.addInterceptor(new ValidateHolder(orderInfo.getRunTime(), RUNTIME_ERROR),
						new RunTimeInterceptor());
			}
			//判断是否出错
			ValidateHolder validateHolder = null;
			if(Assert.isNull((validateHolder = validator.fristError()))){
				//执行修改
				int modify = orderInfoMapper.modifyOrder(orderInfo);
				if(modify != -1){
					//若订单状态为取消订单或结束订单时
					if(orderInfo.getOrderState().equals("2") || orderInfo.getOrderState().equals("4")){
						//同步修改车辆状态（未租用）
						CarInfo carInfo = new CarInfo();
						DotInfo dotInfo = new DotInfo();
						dotInfo.setDot_id(dot_id);
						carInfo.setDotInfo(dotInfo);
						carInfo.setCid(orderInfo.getCarId());
						//修改使用人
						carInfo.setRealName(realName);
						carInfo.setCstate(2);
						carInfoDao.updateCarInfoById(carInfo);
						System.out.println("车辆修改成功");
						int cars = carInfoDao.findCarsByDotId(dot_id);
						DotInfo dot = dotInfoDao.getDotInfoById(dot_id);
						dot.setCars(cars);
						dot.setPark_avaliable((dot.getParking_lot())-cars);
						dotInfoDao.updateDotInfo(dot);
						System.out.println("网点修改成功");
					}else if(orderInfo.getOrderState().equals("1")){//订单状态为正在用车时
						//同步修改车辆状态
						CarInfo carInfo = new CarInfo();
						DotInfo dotInfo = new DotInfo();
						dotInfo.setDot_id(dot_id);
						carInfo.setDotInfo(dotInfo);
						carInfo.setCid(orderInfo.getCarId());
						//修改使用人
						carInfo.setRealName(realName);
						carInfo.setCstate(3);
						carInfoDao.updateCarInfoById(carInfo);
						System.out.println("车辆修改成功");
					}
					return Result.ResultBuilder.buildSuccessResult(OK, true);
				}else {
					return Result.ResultBuilder.buildFailerResult(ERROR, false);
				}
			}else {
				return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), false);
			}
		 }else {
			 return Result.ResultBuilder.buildFailerResult(MODIFY_ORDER_ERROR,false);
		 }
	}

	/**
	 * 查询所有订单
	 */
	@Transactional(readOnly = true)
	public Result getAllOrderInfo(PageRequest<OUCInfo> pageRequest) {
		//判断条件是否为空
		if(!Assert.isNull(pageRequest)){
			//创建验证器
			Validator validator = new Validator();
			//添加拦截条数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			//添加拦截行数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateResult = null;
			if (Assert.isNull((validateResult = validator.fristError()))) {
				List<OUCInfo> orderList = orderInfoMapper.getAllOrderInfo(pageRequest);
				int total = orderInfoMapper.getAllOrderInfoNum(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, orderList);
				return Result.ResultBuilder.buildSuccessResult(OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(PAGE_PARAM_INVALID, null);
	}

}
