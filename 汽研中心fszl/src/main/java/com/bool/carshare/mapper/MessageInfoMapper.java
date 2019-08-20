package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bool.carshare.entity.MessageInfo;
@Mapper
@Repository("messageInfo")
public interface MessageInfoMapper {

	//web查询报文以便连线
	List<MessageInfo> findloAndla(@Param("clicense")String clicense,@Param("startTime")String startTime,@Param("endTime")String endTime);
}
