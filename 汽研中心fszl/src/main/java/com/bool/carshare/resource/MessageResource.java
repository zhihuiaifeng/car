package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.service.MessageInfoService;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class MessageResource {

	@Autowired
	private MessageInfoService messageInfoService;
	//web查询报文信息
	@RequestMapping(value = "findNowLL", method = { RequestMethod.POST})
	public Result findNowLL(String clicense, String startTime, String endTime){
		
		return messageInfoService.findMessage(clicense, startTime, endTime);
		
	}
}
