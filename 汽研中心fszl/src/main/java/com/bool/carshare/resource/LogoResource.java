/**
 * 
 */
package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.entity.LogoInfo;
import com.bool.carshare.service.LogoInfoService;
import com.bool.carshare.util.Result;

/**
 * LogoResource
 * @author wangw
 */
@RestController
@RequestMapping("carshare")
public class LogoResource {
	@Autowired
	private LogoInfoService logoInfoService;
	
	/**
	 * 更新Logo
	 * @param logoInfo
	 * @param webObject
	 * @return
	 */
	@Log(module=ModuleType.LOGO,operation=OperationType.UPDATE)
	@RequestMapping(value="updateLogoInfo",method={RequestMethod.POST})
	public Result updateLogoInfo(LogoInfo logoInfo, WebObject webObject) {
		return this.logoInfoService.updateLogoInfo(logoInfo, webObject);
	}
	
	/**
	 * 查询Logo
	 * @param webObject
	 * @return
	 */
	@RequestMapping(value="getLogoInfo",method={RequestMethod.POST})
	public Result getLogoInfo(WebObject webObject) {
		return this.logoInfoService.getLogoInfo(webObject);
	}
	
	/**
	 * 删除Logo
	 * @param webObject
	 * @return
	 */
	@Log(module=ModuleType.LOGO,operation=OperationType.DELETE)
	@RequestMapping(value="deleteLogoInfo",method={RequestMethod.POST})
	public Result deleteLogoInfo(WebObject webObject) {
		return this.logoInfoService.deleteLogoInfo(webObject);
	}
}