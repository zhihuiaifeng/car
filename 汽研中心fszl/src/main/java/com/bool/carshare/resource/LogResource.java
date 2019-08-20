/**
 * 
 */
package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.entity.LogInfo;
import com.bool.carshare.service.LogInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * LogResource
 * @author wangw
 */
@RestController
@RequestMapping("carshare")
public class LogResource {
	@Autowired
	private LogInfoService logInfoService;
	
	/**
	 * 查询日志
	 * @param row
	 * @param page
	 * @param condition
	 * @return
	 */
	@RequestMapping(value="getLogList",method={RequestMethod.POST})
	public Result getLogList(Integer row, Integer page, LogInfo condition){
		PageRequest<LogInfo> pageRequest = new PageRequest<LogInfo>(row, page, condition);
		
		return this.logInfoService.getLogList(pageRequest);
	}
}