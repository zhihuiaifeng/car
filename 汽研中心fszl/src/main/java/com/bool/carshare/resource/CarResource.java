package com.bool.carshare.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.service.CarInfoService;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.ResponseUtil;
import com.bool.carshare.util.Result;

@RestController
@RequestMapping("/carshare")
public class CarResource {
	private static final Logger logger = LoggerFactory.getLogger(CarResource.class);
	
	@Autowired
	private CarInfoService carInfoService;
	
	//查询网点所有车辆
	@RequestMapping(value = "cars", method = { RequestMethod.POST })
	public Result cars(CarInfo carInfo) {
		return carInfoService.getCarInfos(carInfo);
	}

	//新建车辆信息
	@Log(module=ModuleType.CAR,operation=OperationType.ADD)
	@RequestMapping(value = "newcar", method = { RequestMethod.POST })
	public Result car(CarInfo carInfo, WebObject webObject) {
		return carInfoService.saveCarInfo(carInfo, webObject);
	}
	//通过车辆id查询车辆信息
	@RequestMapping(value = "carid", method = { RequestMethod.POST })
	public Result carId(Integer cid) {
		return carInfoService.getCarInfoById(cid);
	}
	//通过网点Id查询网点包含的车辆总数
	@RequestMapping(value="dotCars", method ={RequestMethod.POST})
	public Result dotCars(Integer dotId){
		return carInfoService.findCarsByDotId(dotId);
	}
	//分页查询所有车辆
	@RequestMapping(value="allCars", method ={RequestMethod.POST})
	public Result allCars(Integer row, Integer page,CarInfo condition) {
		PageRequest<CarInfo> pageRequest = new PageRequest<CarInfo>(row, page, condition);
		return carInfoService.findCarInfoAll(pageRequest);
	}
	//查车型
	@RequestMapping(value="cmodels", method ={RequestMethod.POST})
	public Result cmodels() {
		return carInfoService.findCarModel();
	}
	
	//删除车辆信息
	@Log(module=ModuleType.CAR,operation=OperationType.DELETE)
	@RequestMapping(value="delcar", method ={RequestMethod.POST})
	public Result delcar(@RequestParam("cids[]")int[] cids, WebObject webObject){
		return carInfoService.deleteCarInfo(cids, webObject);
	}
	
	//修改车辆信息
	@Log(module=ModuleType.CAR,operation=OperationType.UPDATE)
	@RequestMapping(value="upcar", method ={RequestMethod.POST})
	public Result upcar(CarInfo carInfo, WebObject webObject){
		return carInfoService.updateCarInfo(carInfo, webObject);
	}
	
	//实时调用里程
	@RequestMapping(value="nowcar", method ={RequestMethod.POST})
	public Result nowcar(Double crunMileage,Integer cid,OrderInfo orderInfo){
		return carInfoService.nowcar(crunMileage,cid,orderInfo);
	}
	
	//发送数据接口
	@RequestMapping(value="send", method ={RequestMethod.POST})
	public Result send(CarInfo carInfo){
		return carInfoService.sendData(carInfo);
	}
	
	//通过vin扫码查询车辆信息
	@RequestMapping(value="carvin", method ={RequestMethod.POST})
	public Result findcar(String cvin){
		return carInfoService.getCarInfoByVin(cvin);
	}
	
	//通过终端编号
	@RequestMapping(value="findChannel", method ={RequestMethod.POST})
	public Result findChannel(String cterminal){
		return carInfoService.findChannel(cterminal);
	}
	
	/**
	 * 上传附件
	 * @param carID 车辆ID
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@RequestMapping(value="uploadCarAttachment",method={RequestMethod.POST})
	public Result uploadCarAttachment(@RequestParam("carID")Integer carID, @RequestParam("file")MultipartFile file, HttpServletRequest request){
		return this.carInfoService.uploadAttachment(carID, file, request);
	}
	
	/**
	 * 下载附件
	 * @param carIDString 车辆ID字符串
	 * @param response 响应
	 */
	@RequestMapping(value="downloadCarAttachment",method={RequestMethod.GET})
	public void downloadCarAttachment(@RequestParam("carIDString")String carIDString, HttpServletResponse response) {
		String[] carIDStringArray = carIDString.split(";");
		
		int[] carIDArray = new int[carIDStringArray.length];
		for(int i=0; i<carIDStringArray.length; i++) {
			carIDArray[i] = Integer.valueOf(carIDStringArray[i]).intValue();
		}
		
		try {
			this.carInfoService.downloadCarAttachment(carIDArray, response);
		} catch (IOException e) {
			e.printStackTrace();
			
			ResponseUtil.alert(response, "下载附件失败");
			logger.error("下载附件失败");
		}
	}
}