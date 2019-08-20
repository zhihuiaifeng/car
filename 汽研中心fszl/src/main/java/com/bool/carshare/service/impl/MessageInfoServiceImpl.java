package com.bool.carshare.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.entity.MessageInfo;
import com.bool.carshare.mapper.MessageInfoMapper;
import com.bool.carshare.service.MessageInfoService;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.Result;
@Service("messageInfoService")
public class MessageInfoServiceImpl implements MessageInfoService {

	@Autowired
	private MessageInfoMapper messageInfoMapper;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result findMessage(String clicense, String startTime, String endTime) {
		try{
			ArrayList<MessageInfo> msgList = (ArrayList<MessageInfo>) messageInfoMapper.findloAndla(clicense, startTime, endTime);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, msgList);
		}catch (Exception e) {
			return Result.ResultBuilder.buildFailerResult(Message.ERROR, false);
		}
	}

}
