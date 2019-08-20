package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.entity.DotInfo;
import com.bool.carshare.service.DotInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class DotResource {
	
	@Autowired
	private DotInfoService dotInfoService;
	
	
	@RequestMapping(value="dots",method={RequestMethod.POST})
	public Result dots(Integer row, Integer page,DotInfo condition){
		PageRequest<DotInfo> pageRequest = new PageRequest<DotInfo>(row, page, condition);
		return dotInfoService.getDotInfos(pageRequest);
	}
	
	@Log(module=ModuleType.DOT,operation=OperationType.ADD)
	@RequestMapping(value="newdot",method={RequestMethod.POST})
	public Result newdot(DotInfo dot, WebObject webObject){
		return dotInfoService.saveDotInfo(dot, webObject);
	}
	
	@Log(module=ModuleType.DOT,operation=OperationType.UPDATE)
	@RequestMapping(value="dotup",method={RequestMethod.POST})
	public Result dot(DotInfo dot, WebObject webObject){
		return dotInfoService.updateDotInfo(dot, webObject);
	}
	
	@RequestMapping(value="dot",method={RequestMethod.POST})
	public Result dot(){
		return dotInfoService.getDotInfosAll();
	}
	
	@Log(module=ModuleType.DOT,operation=OperationType.DELETE)
	@RequestMapping(value="deletedot",method={RequestMethod.POST})
	public Result deleteDot(Integer dot_id, WebObject webObject){
		return dotInfoService.deleteDotInfo(dot_id, webObject);
	}
	
	@RequestMapping(value="appDotUp",method={RequestMethod.POST})
	public Result appDotUp(Integer dot_id){
		return dotInfoService.appUpdateDot(dot_id);
	}
}
