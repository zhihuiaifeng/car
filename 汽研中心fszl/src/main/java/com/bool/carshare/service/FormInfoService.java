/**
 * 
 */
package com.bool.carshare.service;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.FormInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * FormInfoService
 * @author wangw
 */
public interface FormInfoService {
	/**
	 * 保存工单
	 * @param formInfo
	 * @param webObject
	 * @return
	 */
	public Result saveFormInfo(FormInfo formInfo, WebObject webObject);
	
	/**
	 * 查询工单
	 * @param pageRequest
	 * @return
	 */
	public Result getFormList(PageRequest<FormInfo> pageRequest);
	
	/**
	 * 更新工单
	 * @param formInfo
	 * @return
	 */
	public Result updateFormInfo(FormInfo formInfo, WebObject webObject);
	
	/**
	 * 删除工单
	 * @param formIDList
	 * @return
	 */
	public Result deleteFormInfo(int[] formIDList, WebObject webObject);
}