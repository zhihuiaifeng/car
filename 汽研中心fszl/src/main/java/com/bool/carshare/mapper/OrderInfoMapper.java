package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.OUCInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.util.PageRequest;

@Repository("orderInfoMapper")
@Mapper
public interface OrderInfoMapper {
	//新建新订单
	public int confirmOrder(OrderInfo orderInfo);
	
	//查看用户所有订单
	public List<OrderInfo> getOrderDataByUserID(PageRequest<OrderInfo> pageRequest);
	
	//查看当前订单
	public OrderInfo getOrderDataByOrderNum(@Param("orderNumber")String orderNumber);
	
	//查看行驶时间
	public OrderInfo getOrderDataAndStampByOrderNum(@Param("uid")Integer uid);
	
	//查询是否有未完成的订单
	public int getOrderDataCountByOrderNum(@Param("uid")Integer uid);
	//修改订单
	public int modifyOrder(@Param("orderInfo")OrderInfo orderInfo);
	
	//用户订单总数 
	public int getOrderDataCount(OrderInfo condition);
	
	//所有订单
	public List<OUCInfo> getAllOrderInfo(PageRequest<OUCInfo> pageRequest);
	
	//所有订单总数 
	public int getAllOrderInfoNum(OUCInfo condition);
	
	//查询所有异常订单
	public List<OUCInfo> getErrorOrder(PageRequest<OUCInfo> pageRequest);
	
	//异常订单总数
	public int getErrorOrderCount(OUCInfo condition);
}
