package com.bool.carshare.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.controller.UniversalAbstractController;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.service.ManageInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class ManageResource extends UniversalAbstractController{
	@Autowired
	private ManageInfoService manageInfoService;
	
	@RequestMapping(value="manage",method=RequestMethod.POST)
	public void manage(HttpServletRequest request, HttpServletResponse response) {
		this.initController(request, response);
		
		this.invokeMethod(ManageInfoService.class, this.manageInfoService);
	}
	
	//新建管理员
	@Log(module=ModuleType.MANAGE,operation=OperationType.ADD)
	@RequestMapping(value = "newmanage",method = {RequestMethod.POST})
	public Result newAdmin(ManageInfo manageInfo, WebObject webObject){
		return manageInfoService.confirmAdmin(manageInfo, webObject);
	}
	//查询所有管理员
	@RequestMapping(value="allmanage" ,method={RequestMethod.POST})
	public Result allAdmin(Integer row, Integer page, ManageInfo condition){
		PageRequest<ManageInfo> pageRequest = new PageRequest<ManageInfo>(row, page, condition);
		return manageInfoService.findAllAdmin(pageRequest);
	}
	//修改管理员信息
	@Log(module=ModuleType.MANAGE,operation=OperationType.UPDATE)
	@RequestMapping(value ="upmanage",method={RequestMethod.POST})
	public Result updateAdmin(ManageInfo manageInfo, WebObject webObject){
		return manageInfoService.updateAdminInfo(manageInfo, webObject);
	}
	//删除管理员
	@Log(module=ModuleType.MANAGE,operation=OperationType.DELETE)
	@RequestMapping(value="dmanage",method={RequestMethod.POST})
	public Result delete(Integer manageId, WebObject webObject){
		return manageInfoService.delete(manageId, webObject);
	}
	
	/**
	 * 修改密码
	 * @param manageID
	 * @param newPassword
	 * @return
	 */
	@Log(module=ModuleType.MANAGE,operation=OperationType.CHANGE_PASSWORD)
	@RequestMapping(value="updatePassword",method={RequestMethod.POST})
	public Result updatePassword(Integer manageID, String newPassword, WebObject webObject) {
		return this.manageInfoService.updatePassword(manageID, newPassword, webObject);
	}
}
