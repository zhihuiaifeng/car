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
import com.bool.carshare.entity.TerminalInfo;
import com.bool.carshare.service.TerminalService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

/**
 * TerminalResource
 * @author wangw
 */
@RestController
@RequestMapping("carshare")
public class TerminalResource extends UniversalAbstractController{
	@Autowired
	private TerminalService terminalService;
	
	@RequestMapping(value="terminal",method=RequestMethod.POST)
	public void terminal(HttpServletRequest request, HttpServletResponse response) {
		this.initController(request, response);
		
		this.invokeMethod(TerminalService.class, this.terminalService);
	}
	
	/**
	 * 保存终端
	 * @param terminalInfo
	 * @param webObject
	 * @return
	 */
	@Log(module=ModuleType.TERMINAL,operation=OperationType.ADD)
	@RequestMapping(value="saveTerminalInfo",method=RequestMethod.POST)
	public Result saveTerminalInfo(TerminalInfo terminalInfo, WebObject webObject) {
		return this.terminalService.saveTerminalInfo(terminalInfo, webObject);
	}
	
	/**
	 * 查询终端
	 * @param row
	 * @param page
	 * @param condition
	 * @return
	 */
	@RequestMapping(value="getTerminalList",method={RequestMethod.POST})
	public Result getTerminalList(Integer row, Integer page, TerminalInfo condition){
		PageRequest<TerminalInfo> pageRequest = new PageRequest<TerminalInfo>(row, page, condition);
		
		return this.terminalService.getTerminalList(pageRequest);
	}
	
	/**
	 * 更新终端
	 * @param terminalInfo
	 * @return
	 */
	@Log(module=ModuleType.TERMINAL,operation=OperationType.UPDATE)
	@RequestMapping(value="updateTerminalInfo",method=RequestMethod.POST)
	public Result updateTerminalInfo(TerminalInfo terminalInfo, WebObject webObject) {
		return this.terminalService.updateTerminalInfo(terminalInfo, webObject);
	}
	
	/**
	 * 删除终端
	 * @param terminalIDList
	 * @return
	 */
	@Log(module=ModuleType.TERMINAL,operation=OperationType.DELETE)
	@RequestMapping(value="deleteTerminalInfo",method=RequestMethod.POST)
	public Result deleteTerminalInfo(@RequestParam("terminalIDList[]")int[] terminalIDList, WebObject webObject) {
		return this.terminalService.deleteTerminalInfo(terminalIDList, webObject);
	}
	
	/**
	 * 获得某一终端的信息
	 * @param terminalID
	 * @return
	 */
	@RequestMapping(value="getTerminalInfo",method=RequestMethod.POST)
	public Result getTerminalInfo(int terminalID) {
		return this.terminalService.getTerminalInfo(terminalID);
	}
}