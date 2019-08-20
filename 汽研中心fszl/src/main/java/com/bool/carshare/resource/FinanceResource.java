package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.entity.FinanceInfo;
import com.bool.carshare.service.FinanceInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class FinanceResource {

	@Autowired
	private FinanceInfoService financeInfoService;
	
	@RequestMapping(value="newfin",method={RequestMethod.POST})
	public Result saveFinanceInfo(Integer uid) {
		return financeInfoService.saveFinanceInfo(uid);
	}
	
	@RequestMapping(value="getfins",method={RequestMethod.POST})
	public Result findFinanceInfo(Integer row, Integer page, FinanceInfo condition){
		PageRequest<FinanceInfo> pageRequest = new PageRequest<FinanceInfo>(row, page, condition);
		return financeInfoService.findFinanceInfo(pageRequest);
	}
	
	@RequestMapping(value="upfin",method={RequestMethod.POST})
	public Result upFinanceInfoStatus(Integer status, String newPayId) {
		return financeInfoService.upFinanceInfoStatus(status, newPayId);
	}
}
