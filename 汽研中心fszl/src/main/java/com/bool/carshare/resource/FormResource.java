/**
 * 
 */
package com.bool.carshare.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.controller.UniversalAbstractController;
import com.bool.carshare.entity.FormInfo;
import com.bool.carshare.service.FormInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * FormResource
 * @author wangw
 */
@RestController
@RequestMapping("carshare")
public class FormResource extends UniversalAbstractController{
	@Autowired
	private FormInfoService formInfoService;
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public void form(HttpServletRequest request, HttpServletResponse response) {
		this.initController(request, response);
		
		this.invokeMethod(FormInfoService.class, this.formInfoService);
	}
	
	/**
	 * 保存工单
	 * @param formInfo
	 * @param webObject
	 * @return
	 */
	@Log(module=ModuleType.FORM,operation=OperationType.ADD)
	@RequestMapping(value="saveFormInfo",method={RequestMethod.POST})
	public Result saveFormInfo(FormInfo formInfo, WebObject webObject) {
		return this.formInfoService.saveFormInfo(formInfo, webObject);
	}
	
	/**
	 * 查询工单
	 * @param row
	 * @param page
	 * @param condition
	 * @return
	 */
	@RequestMapping(value="getFormList",method={RequestMethod.POST})
	public Result getFormList(Integer row, Integer page, FormInfo condition){
		PageRequest<FormInfo> pageRequest = new PageRequest<FormInfo>(row, page, condition);
		
		return this.formInfoService.getFormList(pageRequest);
	}
	
	/**
	 * 更新工单
	 * @param formInfo
	 * @return
	 */
	@Log(module=ModuleType.FORM,operation=OperationType.UPDATE)
	@RequestMapping(value="updateFormInfo",method={RequestMethod.POST})
	public Result updateFormInfo(FormInfo formInfo, WebObject webObject) {
		return this.formInfoService.updateFormInfo(formInfo, webObject);
	}
	
	/**
	 * 删除工单
	 * @param formIDList
	 * @return
	 */
	@Log(module=ModuleType.FORM,operation=OperationType.DELETE)
	@RequestMapping(value="deleteFormInfo",method={RequestMethod.POST})
	public Result deleteFormInfo(@RequestParam("formIDList[]")int[] formIDList, WebObject webObject) {
		return this.formInfoService.deleteFormInfo(formIDList, webObject);
	}
}