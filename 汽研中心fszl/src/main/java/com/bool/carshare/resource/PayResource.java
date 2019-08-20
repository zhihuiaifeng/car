package com.bool.carshare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.entity.PayInfo;
import com.bool.carshare.service.PayInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class PayResource {
	

	@Autowired
	private PayInfoService payInfoService;
	
	/**
	 * 查看流水单
	 * @param payInfo
	 * @return
	 */
	@RequestMapping(value = "pays", method = { RequestMethod.POST })
	public Result pays(Integer row, Integer page, PayInfo condition){  
		PageRequest<PayInfo> pageRequest = new PageRequest<PayInfo>(row, page, condition);
		return payInfoService.findAllPayInfoByUid(pageRequest);
	}
	
	/**
	 * 查看充值单
	 * @param payInfo
	 * @return
	 */
	@RequestMapping(value = "changepays", method = { RequestMethod.POST })
	public Result changepays(Integer row, Integer page, PayInfo condition){  
		PageRequest<PayInfo> pageRequest = new PageRequest<PayInfo>(row, page, condition);
		return payInfoService.findChange(pageRequest);
	}
	/**
	 * 新建流水单
	 * @param payInfo
	 * @return
	 */
	@RequestMapping(value = "newpay",method ={ RequestMethod.POST })
	public Result newpay(PayInfo payInfo){
		return payInfoService.savePayInfo(payInfo);
	}
	
	/**
	 * 余额支付
	 */
	@RequestMapping(value = "nbpay",method ={RequestMethod.POST})
	public Result nbpay(PayInfo payInfo,Integer disid){
		return payInfoService.nbsavePayInfo(payInfo,disid);
	}
}
