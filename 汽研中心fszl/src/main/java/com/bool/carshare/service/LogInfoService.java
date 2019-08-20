/**
 * 
 */
package com.bool.carshare.service;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.LogInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * LogInfoService
 * @author wangw
 */
public interface LogInfoService {
	/**
	 * 保存日志
	 * @param logInfo
	 * @param webObject
	 * @return
	 */
	public Result saveLogInfo(LogInfo logInfo, WebObject webObject);
	
	/**
	 * 查询日志
	 * @param pageRequest
	 * @return
	 */
	public Result getLogList(PageRequest<LogInfo> pageRequest);
}