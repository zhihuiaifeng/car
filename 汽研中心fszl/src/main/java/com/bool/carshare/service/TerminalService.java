/**
 * 
 */
package com.bool.carshare.service;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.TerminalInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * TerminalService
 * @author wangw
 */
public interface TerminalService {
	/**
	 * 保存终端
	 * @param terminalInfo
	 * @param webObject
	 * @return
	 */
	public Result saveTerminalInfo(TerminalInfo terminalInfo, WebObject webObject);
	
	/**
	 * 查询终端
	 * @param pageRequest
	 * @return
	 */
	public Result getTerminalList(PageRequest<TerminalInfo> pageRequest);
	
	/**
	 * 更新终端
	 * @param terminalInfo
	 * @return
	 */
	public Result updateTerminalInfo(TerminalInfo terminalInfo, WebObject webObject);
	
	/**
	 * 删除终端
	 * @param terminalIDList
	 * @return
	 */
	public Result deleteTerminalInfo(int[] terminalIDList, WebObject webObject);
	
	/**
	 * 获得某一终端的信息
	 * @param terminalID
	 * @return
	 */
	public Result getTerminalInfo(int terminalID);
}