/**
 * 
 */
package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bool.carshare.entity.LogInfo;
import com.bool.carshare.util.PageRequest;

/**
 * LogInfoMapper
 * @author wangw
 */
@Mapper
public interface LogInfoMapper {
	/**
	 * 保存日志
	 * @param logInfo
	 * @return
	 */
	public int saveLogInfo(LogInfo logInfo);
	
	/**
	 * 获得指定页面的日志列表
	 * @param pageRequest
	 * @return
	 */
	public List<LogInfo> getLogListOnSpecificPage(PageRequest<LogInfo> pageRequest);
	
	/**
	 * 获得某一条件下所有日志的数量
	 * @param condition
	 * @return
	 */
	public int getLogListTotalCount(LogInfo condition);
}