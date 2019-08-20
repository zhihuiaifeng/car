package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.DotInfo;
import com.bool.carshare.mapper.CarInfoMapper;
import com.bool.carshare.mapper.DotInfoMapper;
import com.bool.carshare.service.DotInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;

@Service("dotInfoService")
public class DotInfoServiceImpl implements DotInfoService {

	@Autowired
	private DotInfoMapper dotInfoDao;
	
	@Autowired
	private CarInfoMapper carInfoDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Result getDotInfos(PageRequest<DotInfo> pageRequest) {
		//判断条件是否为空
		if(!Assert.isNull(pageRequest)){
			//创建验证器
			Validator validator = new Validator();
			//添加拦截条数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			//添加拦截行数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateResult = null;
			if (Assert.isNull((validateResult = validator.fristError()))) {
				List<DotInfo> List = dotInfoDao.getDotInfos(pageRequest);
				Integer total = dotInfoDao.getDotInfosNum(pageRequest.getCondition());
				PageResponse rp =  new PageResponse(total, List);
				return Result.ResultBuilder.buildSuccessResult(OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(PAGE_PARAM_INVALID, null);
	}

	//web修改网点
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateDotInfo(DotInfo dotInfo, WebObject webObject) {
		Validator validator = new Validator();
		//网点名称
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_name(), DOT_NAME_FORMAT_ERROR),
				new NotNullInterceptor());
		//经度
		validator.addInterceptor(new ValidateHolder(dotInfo.getGps_x(),DOT_GPS_X_FORMAT_ERROR),
				new NumberInterceptor(Integer.MAX_VALUE, Integer.MIN_VALUE),
				new NotNullInterceptor());
		//纬度
		validator.addInterceptor(new ValidateHolder(dotInfo.getGps_y(),DOT_GPS_Y_FORMAT_ERROR),
				new NumberInterceptor(Integer.MAX_VALUE, Integer.MIN_VALUE),
				new NotNullInterceptor());
		//网点地址
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_address(),DOT_ADDRESS_FORMAT_ERROR),
				new NotNullInterceptor());
		//所属城市
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_city(), DOT_CITY_NULL),
				new NotNullInterceptor());
		//充电桩个数
		validator.addInterceptor(new ValidateHolder(dotInfo.getCharge_number(), DOT_CHARGE_NUMBER_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE,1),
				new NotNullInterceptor());
		//停车位个数
		validator.addInterceptor(new ValidateHolder(dotInfo.getParking_lot(),DOT_PARK_LOT_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 1),
				new NotNullInterceptor());
		//网点图片不为空
		validator.addInterceptor(new ValidateHolder(dotInfo.getPhoto(), DOT_PHOTO_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult = validator.fristError()))) {
			int cars = carInfoDao.findCarsByDotId(dotInfo.getDot_id());
			dotInfo.setCars(cars);
			dotInfo.setPark_avaliable((dotInfo.getParking_lot())-cars);
			dotInfoDao.updateDotInfo(dotInfo);
			
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		}else {
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result saveDotInfo(DotInfo dotInfo, WebObject webObject) {
		Validator validator = new Validator();
		//网点名称
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_name(), DOT_NAME_FORMAT_ERROR),
				new NotNullInterceptor());
		//经度
		validator.addInterceptor(new ValidateHolder(dotInfo.getGps_x(),DOT_GPS_X_FORMAT_ERROR),
				new NumberInterceptor(Integer.MAX_VALUE, Integer.MIN_VALUE),
				new NotNullInterceptor());
		//纬度
		validator.addInterceptor(new ValidateHolder(dotInfo.getGps_y(),DOT_GPS_Y_FORMAT_ERROR),
				new NumberInterceptor(Integer.MAX_VALUE, Integer.MIN_VALUE),
				new NotNullInterceptor());
		//网点地址
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_address(),DOT_ADDRESS_FORMAT_ERROR),
				new NotNullInterceptor());
		//所属城市
		validator.addInterceptor(new ValidateHolder(dotInfo.getDot_city(), DOT_CITY_NULL),
				new NotNullInterceptor());
		//充电桩个数
		validator.addInterceptor(new ValidateHolder(dotInfo.getCharge_number(), DOT_CHARGE_NUMBER_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE,1),
				new NotNullInterceptor());
		//停车位个数
		validator.addInterceptor(new ValidateHolder(dotInfo.getParking_lot(),DOT_PARK_LOT_INVALID),
				new NumberInterceptor(Integer.MAX_VALUE, 1),
				new NotNullInterceptor());
		//网点图片不为空
		validator.addInterceptor(new ValidateHolder(dotInfo.getPhoto(), DOT_PHOTO_NULL),
				new NotNullInterceptor());
		int cars = 0;
		dotInfo.setCars(cars);
		dotInfo.setPark_avaliable(dotInfo.getParking_lot() - cars);
		ValidateHolder validateResult = validator.fristError();
		if(validateResult == null){
			dotInfoDao.saveDotInfo(dotInfo);
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result getDotInfosAll() {
		
		return Result.ResultBuilder.buildSuccessResult(OK, dotInfoDao.getDotInfosAll());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result deleteDotInfo(Integer dot_id, WebObject webObject) {
		
		return Result.ResultBuilder.buildSuccessResult(OK, dotInfoDao.deleteDotInfo(dot_id));
	}

	//app修改网点
	@Transactional(propagation = Propagation.REQUIRED)
	public Result appUpdateDot(Integer dot_id) {
		int cars = carInfoDao.findCarsByDotId(dot_id);
		DotInfo dot = dotInfoDao.getDotInfoById(dot_id);
		dot.setCars(cars);
		dot.setPark_avaliable((dot.getParking_lot())-cars);
		dotInfoDao.updateDotInfo(dot);
		return Result.ResultBuilder.buildSuccessResult(OK, null);
		
	}


}
