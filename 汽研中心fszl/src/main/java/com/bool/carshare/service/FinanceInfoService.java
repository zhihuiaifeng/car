package com.bool.carshare.service;

import com.bool.carshare.entity.FinanceInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface FinanceInfoService {

	public Result saveFinanceInfo(Integer uid);

	public Result findFinanceInfo(PageRequest<FinanceInfo> pageRequest);
	
	public Result upFinanceInfoStatus(Integer status,String newPayId);
}
