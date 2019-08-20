package com.bool.carshare.service;

import com.bool.carshare.entity.PayInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface PayInfoService {

	public Result savePayInfo(PayInfo payInfo);
	
	public Result findAllPayInfoByUid(PageRequest<PayInfo> pageRequest);  
	
	public Result nbsavePayInfo(PayInfo payInfo,Integer disid);
	
	public Result findChange(PageRequest<PayInfo> pageRequest);
}
