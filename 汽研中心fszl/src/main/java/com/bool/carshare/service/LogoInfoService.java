/**
 * 
 */
package com.bool.carshare.service;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.LogoInfo;
import com.bool.carshare.util.Result;

/**
 * LogoInfoService
 * @author wangw
 */
public interface LogoInfoService {
	/**
	 * 更新Logo
	 * @param logoInfo
	 * @param webObject
	 * @return
	 */
	public Result updateLogoInfo(LogoInfo logoInfo, WebObject webObject);
	
	/**
	 * 查询Logo
	 * @param webObject
	 * @return
	 */
	public Result getLogoInfo(WebObject webObject);
	
	/**
	 * 删除Logo
	 * @param webObject
	 * @return
	 */
	public Result deleteLogoInfo(WebObject webObject);
}