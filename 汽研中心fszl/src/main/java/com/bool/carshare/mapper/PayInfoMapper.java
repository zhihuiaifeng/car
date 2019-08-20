package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.PayInfo;
import com.bool.carshare.util.PageRequest;
@Repository("payInfoMapper")
@Mapper
public interface PayInfoMapper {

	//新建
	int savePayInfo(PayInfo payInfo);
	//通过用户id查所有账单
	List<PayInfo> findAllPayInfoByUid(PageRequest<PayInfo> pageRequest);
	//通过流水单号查询账单
	PayInfo findOnePayById(@Param("pid")String pid);
	//修改流水状态
	int updateSuccessState(PayInfo payInfo);
	//用户账单个数
	int findCountByUid(PayInfo payInfo);
	//查询充值账单
	List<PayInfo> findChange(PageRequest<PayInfo> pageRequest);
	//充值账单个数
	int findChangeCount(PayInfo payInfo);
	//查看交押金的账单
	PayInfo findCountByUidRC(@Param("uid")Integer uid,@Param("rechargeORconsume")String rechargeORconsume);
}
