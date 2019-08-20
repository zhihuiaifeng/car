/**
 * 
 */
package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bool.carshare.entity.FormInfo;
import com.bool.carshare.util.PageRequest;

/**
 * FormInfoMapper
 * @author wangw
 */
@Mapper
public interface FormInfoMapper {
	/**
	 * 保存工单
	 * @param formInfo
	 * @return
	 */
	public int saveFormInfo(FormInfo formInfo);
	
	/**
	 * 获得指定页面的工单列表
	 * @param pageRequest
	 * @return
	 */
	public List<FormInfo> getFormListOnSpecificPage(PageRequest<FormInfo> pageRequest);
	
	/**
	 * 获得某一条件下所有工单的数量
	 * @param condition
	 * @return
	 */
	public int getFormListTotalCount(FormInfo condition);
	
	/**
	 * 更新工单
	 * @param formInfo
	 * @return
	 */
	public int updateFormInfo(FormInfo formInfo);
	
	/**
	 * 删除工单
	 * @param formID
	 * @return
	 */
	public int deleteFormInfo(@Param("formIDList")int[] formIDList);
}