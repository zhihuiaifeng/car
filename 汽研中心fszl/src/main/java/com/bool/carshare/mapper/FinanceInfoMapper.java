package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.FinanceInfo;
import com.bool.carshare.util.PageRequest;

@Mapper
@Repository("financeInfoMapper")
public interface FinanceInfoMapper {

	public int saveFinanceInfo(FinanceInfo financeInfo);
	
	public int findFinanceInfoCount(FinanceInfo financeInfo);
	
	public List<FinanceInfo> findFinanceInfo(PageRequest<FinanceInfo> pageRequest);
	
	public int upFinanceInfoStatus(@Param("status")Integer status,@Param("newPayId")String newPayId);
}
