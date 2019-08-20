package com.bool.carshare.service;

import com.bool.carshare.entity.OUCInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface OrderInfoService {

		//新建新订单
		public Result confirmOrder(OrderInfo orderInfo,Integer dot_id,String realName, Double crunMileage);
		
		//查看用户所有订单
		public Result getOrderDataByUserID(PageRequest<OrderInfo> pageRequest);
		
		//查看当前订单
		public Result getOrderDataByOrderNum(String orderNumber);
		
		//查看行驶时间
		public Result getOrderDataAndStampByOrderNum(Integer uid);
		
		//修改订单
		public Result modifyOrder(OrderInfo orderInfo,Integer dot_id,String realName);
		
		//查看所有订单
		public Result getAllOrderInfo(PageRequest<OUCInfo> pageRequest);

}
