/**
 * 
 */
package com.bool.carshare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bool.carshare.entity.TerminalInfo;
import com.bool.carshare.util.PageRequest;

/**
 * TerminalInfoMapper
 * @author wangw
 */
@Mapper
public interface TerminalInfoMapper {
	/**
	 * 保存终端
	 * @param terminalInfo
	 * @return
	 */
	public int saveTerminalInfo(TerminalInfo terminalInfo);
	
	/**
	 * 获得指定页面的终端列表
	 * @param pageRequest
	 * @return
	 */
	public List<TerminalInfo> getTerminalListOnSpecificPage(PageRequest<TerminalInfo> pageRequest);
	
	/**
	 * 获得某一条件下所有终端的数量
	 * @param condition
	 * @return
	 */
	public int getTerminalListTotalCount(TerminalInfo condition);
	
	/**
	 * 更新终端
	 * @param terminalInfo
	 * @return
	 */
	public int updateTerminalInfo(TerminalInfo terminalInfo);
	
	/**
	 * 删除终端
	 * @param terminalID
	 * @return
	 */
	public int deleteTerminalInfo(@Param("terminalIDList")int[] terminalIDList);
	
	/**
	 * 获得某一终端的信息
	 * @param terminalID
	 * @return
	 */
	public TerminalInfo getTerminalInfo(@Param("terminalID")int terminalID);
}