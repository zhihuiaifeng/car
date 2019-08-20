package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.entity.OUCInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.service.OrderInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class OrderResource {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	/**
	 * 新建
	 */
	@RequestMapping(value="newOrder",method={RequestMethod.POST})
	public Result newOrder(OrderInfo orderInfo,Integer dot_id,String realName, Double crunMileage){
		return orderInfoService.confirmOrder(orderInfo,dot_id,realName, crunMileage);
	}

	/**
	 * 用户查看
	 */
	@RequestMapping(value="userOrders",method={RequestMethod.POST})
	public Result userOrders(Integer row, Integer page, OrderInfo condition){
		PageRequest<OrderInfo> pageRequest = new PageRequest<OrderInfo>(row, page, condition);
		return orderInfoService.getOrderDataByUserID(pageRequest);
	}
	
	/**
	 * 订单编号查询
	 */
	@RequestMapping(value="order",method={RequestMethod.POST})
	public Result findByOrderNumber(String orderNumber){
		return orderInfoService.getOrderDataByOrderNum(orderNumber);
	}
	
	/**
	 * 查看用户未完成订单
	 */
	@RequestMapping(value="orderNoSuccess",method={RequestMethod.POST})
	public Result findRunTime(Integer uid){
		return orderInfoService.getOrderDataAndStampByOrderNum(uid);
	}
	
	/**
	 * 修改订单状态
	 */
	@RequestMapping(value="updateOrder",method={RequestMethod.POST})
	public Result updateOrder(OrderInfo orderInfo,Integer dot_id,String realName){
		return orderInfoService.modifyOrder(orderInfo,dot_id,realName);
	}
	
	/**
	 * 查询所有订单相关数据
	 */
	@RequestMapping(value="allOrders",method={RequestMethod.POST})
	public Result allOrders(Integer row, Integer page, OUCInfo condition){
		PageRequest<OUCInfo> pageRequest = new PageRequest<OUCInfo>(row, page, condition);
		return orderInfoService.getAllOrderInfo(pageRequest);
	}
	
}
