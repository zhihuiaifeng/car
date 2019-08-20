package com.bool.carshare.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.Result;

public interface CarInfoService {
	
	Result saveCarInfo(CarInfo carInfo, WebObject webObject);
	
	Result getCarInfos(CarInfo carInfo);
	
	Result updateCarInfo(CarInfo carInfo, WebObject webObject);

	Result getCarInfoById(Integer cid);
	
	Result findCarsByDotId(Integer dotId);
	
	Result findCarInfoAll(PageRequest<CarInfo> pageRequest);
	
	Result findCarModel();
	
	Result deleteCarInfo(int[] cids, WebObject webObject);
	
	Result upCarInfo(CarInfo carInfo);
	
	Result nowcar(Double ctotalMileage,Integer cid,OrderInfo orderInfo);
	
	Result sendData(CarInfo carInfo);
	
	Result getCarInfoByVin(String cvin);
	
	Result findChannel(String cterminal);
	
	/**
	 * 上传附件
	 * @param carID 车辆ID
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	public Result uploadAttachment(Integer carID, MultipartFile file, HttpServletRequest request);
	
	/**
	 * 下载附件
	 * @param carIDArray 车辆ID数组
	 * @param response 响应
	 * @throws IOException
	 */
	public void downloadCarAttachment(int[] carIDArray, HttpServletResponse response) throws IOException;
}