package com.bool.carshare.service;

import com.bool.carshare.util.Result;

public interface MessageInfoService {

	//查询报文的连线
	Result findMessage(String clicense,String startime,String endTime);
}
