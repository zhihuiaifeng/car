package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.*;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bool.carshare.bean.ResultBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.CarInfo;
import com.bool.carshare.entity.OUCInfo;
import com.bool.carshare.entity.OrderInfo;
import com.bool.carshare.mapper.CarInfoMapper;
import com.bool.carshare.mapper.OrderInfoMapper;
import com.bool.carshare.service.CarInfoService;
import com.bool.carshare.service.CommonService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.FileUtil;
import com.bool.carshare.util.FileUtilForCar;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.auth.CarCacheManager;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueLengthInterceptor;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用户服务类
 * @author tzw
 *
 */
@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService {
	@Autowired
	private CarInfoMapper carInfoDao;
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 保存车辆信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result saveCarInfo(CarInfo carInfo, WebObject webObject) {
		Validator validator = new Validator();
		//终端编号
		validator.addInterceptor(new ValidateHolder(carInfo.getCterminal(), CAR_TERMINAL_FORMAT_ERROR),
				new NotNullInterceptor());
		// 车型名称长度必须大于等于1，小于等于20
		validator.addInterceptor(new ValidateHolder(carInfo.getCmodel(), CAR_MODEL_FORMAT_ERROR),
				new ValueLengthInterceptor(20, 1), new NotNullInterceptor());
		// 车辆牌照长度必须是7
		validator.addInterceptor(new ValidateHolder(carInfo.getClicense(), CAR_CLICENSE_FORMAT_ERROR),
				new ValueLengthInterceptor(7, 7), new NotNullInterceptor());
		// 车辆vin码长度必须是17
		validator.addInterceptor(new ValidateHolder(carInfo.getCvin(), CAR_VIN_FORMAT_ERROR),
				new NotNullInterceptor());
		//投保日期不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getInsuranceDate(), INSURANCE_DATE_NULL),
				new NotNullInterceptor());
		//投保公司不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getInsuranceCompany(), INSURANCE_COMPANY_NULL),
				new NotNullInterceptor());
		//车辆图片不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getCphoto(), CAR_PHOTO_NULL),
				new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		// 获取第一个错误
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			carInfoDao.saveCarInfo(carInfo);
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		} else {
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}

	}

	/**
	 * 获取网点所有车辆
	 */
	@Transactional(readOnly=true)
	public Result getCarInfos(CarInfo carInfo) {
		return Result.ResultBuilder.buildSuccessResult(OK, carInfoDao.getCarInfos(carInfo));
	}

	/**
	 * 修改车辆信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateCarInfo(CarInfo carInfo, WebObject webObject) {
		Validator validator = new Validator();
		//车辆ID
		validator.addInterceptor(new ValidateHolder(carInfo.getCid(), CAR_ID_NULL),
				new NotNullInterceptor());
		//终端编号
		validator.addInterceptor(new ValidateHolder(carInfo.getCterminal(), CAR_TERMINAL_FORMAT_ERROR),
				new NotNullInterceptor());
		// 车型名称长度必须大于等于1，小于等于20
		validator.addInterceptor(new ValidateHolder(carInfo.getCmodel(), CAR_MODEL_FORMAT_ERROR),
				new ValueLengthInterceptor(20, 1), new NotNullInterceptor());
		// 车辆牌照长度必须是7
		validator.addInterceptor(new ValidateHolder(carInfo.getClicense(), CAR_CLICENSE_FORMAT_ERROR),
				new ValueLengthInterceptor(7, 7), new NotNullInterceptor());
		// 车辆vin码长度必须是17
		validator.addInterceptor(new ValidateHolder(carInfo.getCvin(), CAR_VIN_FORMAT_ERROR),
				new NotNullInterceptor());
		//投保日期不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getInsuranceDate(), INSURANCE_DATE_NULL),
				new NotNullInterceptor());
		//投保公司不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getInsuranceCompany(), INSURANCE_COMPANY_NULL),
				new NotNullInterceptor());
		//车辆图片不为空
		validator.addInterceptor(new ValidateHolder(carInfo.getCphoto(), CAR_PHOTO_NULL),
				new NotNullInterceptor());
		carInfo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			carInfoDao.updateCarInfoById(carInfo);
			return Result.ResultBuilder.buildSuccessResult(OK, carInfo);
		}else {
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}

	/**
	 * 通过车辆id获取车辆信息
	 */
	@Transactional(readOnly=true)
	public Result getCarInfoById(Integer cid){
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(cid, CAR_ID_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			CarInfo carInfo = carInfoDao.getCarInfoById(cid);
			return Result.ResultBuilder.buildSuccessResult(OK, carInfo);
		}else {
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}

	/**
	 * 根据网点id获取网点车辆个数
	 */
	@Transactional(readOnly=true)
	public Result findCarsByDotId(Integer dotId) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(dotId, Message.DOT_ID_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			int cars = carInfoDao.findCarsByDotId(dotId);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, cars);
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 获取全部车辆信息
	 */
	@Transactional(readOnly=true)
	public Result findCarInfoAll(PageRequest<CarInfo> pageRequest){
		if(!Assert.isNull(pageRequest)){
			//创建验证器
			Validator validator = new Validator();
			//添加拦截条数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			//添加拦截行数非空且不能超过最大
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateHolder = null;
			if (Assert.isNull((validateHolder = validator.fristError()))) {
				List<CarInfo> cars = carInfoDao.findCarInfoAll(pageRequest);
				List<CarInfo> cars2 = new ArrayList<CarInfo>();
				for (CarInfo carInfo : cars) {
					if(carInfo.getLastMaintainDate() != null){
						//计算保养时间
						long lastMaintainDatel = carInfo.getLastMaintainDate().getTime();
						lastMaintainDatel = lastMaintainDatel + 90*24*60*60*1000L;
						//当前时间与保养时间相比较
						if(System.currentTimeMillis() > lastMaintainDatel){
							carInfo.setEnsureWarn("0");
							cars2.add(carInfo);
						}else{
							carInfo.setEnsureWarn("1");
							cars2.add(carInfo);
						}
					}else{
						cars2.add(carInfo);
					}
				}
				int total = carInfoDao.findCarInfoAllNum(pageRequest.getCondition());
				PageResponse rp = new PageResponse(total, cars2);
				return Result.ResultBuilder.buildSuccessResult(OK, rp);
			}
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}
		return Result.ResultBuilder.buildFailerResult(PAGE_PARAM_INVALID, null);
	}

	/**
	 * 查询车型
	 */
	@Transactional(readOnly=true)
	public Result findCarModel() {
		try{
			List<String> cmodels = carInfoDao.findCarModel();
			return Result.ResultBuilder.buildSuccessResult(OK, cmodels);
		}catch (Exception e) {
			return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
		}
	}
	
	/**
	 * 删除车辆信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result deleteCarInfo(int[] cids, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(cids, CAR_ID_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			carInfoDao.deleteCarInfo(cids);
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		}else {
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}

	/**
	 * netty传回的数据插入数据库
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result upCarInfo(CarInfo carInfo) {
		try{
			//jdbc 部署时修改
			String usql =null;
			String isql = null;
//			String DB_URL = "jdbc:mysql://192.168.0.136:3306/carshare?characterEncoding=utf8&useSSL=true";
//			String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
//			String USER = "root";
//			String PASS = "";
			String DB_URL = "jdbc:mysql://localhost:3306/carshare?characterEncoding=utf8&useSSL=true";
			String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
			String USER = "root";
			String PASS = "root";
			Class.forName(JDBC_DRIVER);
			Connection conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			//真实
			if(carInfo.getCterminal().equals("61311006548969")||carInfo.getCterminal().equals("61311006539802")||carInfo.getCterminal().equals("61311006832439")){
//			//测试
//			if(carInfo.getCterminal().equals("61311007978421")){
				/**
				 * 经纬度不在天线取
				 */
//				usql = "UPDATE CarInfo SET carDoorState = ?, lcarWinState = ?, rcarWinState = ?, startupState = ?, modifyTime = ?"
//				+ " WHERE cterminal = ?";
//				isql = "INSERT INTO MessageInfo(carDoorState,lcarWinState,rcarWinState,startupState,modifyTime,cterminal) VALUES(?,?,?,?,?,?)";
//				PreparedStatement upStmt = (PreparedStatement) conn.clientPrepareStatement(usql);
//				upStmt.setString(1, carInfo.getCarDoorState()!=null?carInfo.getCarDoorState():null);
//				upStmt.setString(2, carInfo.getLcarWinState()!=null?carInfo.getLcarWinState():null);
//				upStmt.setString(3, carInfo.getRcarWinState()!=null?carInfo.getRcarWinState():null);
//				upStmt.setString(4, carInfo.getStartupState()!=null?carInfo.getStartupState():null);
//				upStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//				upStmt.setString(6, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
//				upStmt.executeUpdate();
//				upStmt.close();
//				PreparedStatement ipStmt = (PreparedStatement) conn.clientPrepareStatement(isql);
//				ipStmt.setString(1, carInfo.getCarDoorState()!=null?carInfo.getCarDoorState():null);
//				ipStmt.setString(2, carInfo.getLcarWinState()!=null?carInfo.getLcarWinState():null);
//				ipStmt.setString(3, carInfo.getRcarWinState()!=null?carInfo.getRcarWinState():null);
//				ipStmt.setString(4, carInfo.getStartupState()!=null?carInfo.getStartupState():null);
//				ipStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//				ipStmt.setString(6, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
//				ipStmt.executeUpdate();
				/**
				 * 经纬度用天线取
				 */
				usql = "UPDATE CarInfo SET longitude = ?,latitude = ?, carDoorState = ?, lcarWinState = ?, rcarWinState = ?, startupState = ?, modifyTime = ?"
				+ " WHERE cterminal = ?";
				isql = "INSERT INTO MessageInfo(longitude,latitude,carDoorState,lcarWinState,rcarWinState,startupState,modifyTime,cterminal) VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement upStmt = (PreparedStatement) conn.clientPrepareStatement(usql);
				upStmt.setString(1, carInfo.getLongitude()!=null?carInfo.getLongitude():CarCacheManager.getLongitude());
				upStmt.setString(2, carInfo.getLatitude()!=null?carInfo.getLatitude():CarCacheManager.getLatitude());
				upStmt.setString(3, carInfo.getCarDoorState()!=null?carInfo.getCarDoorState():null);
				upStmt.setString(4, carInfo.getLcarWinState()!=null?carInfo.getLcarWinState():null);
				upStmt.setString(5, carInfo.getRcarWinState()!=null?carInfo.getRcarWinState():null);
				upStmt.setString(6, carInfo.getStartupState()!=null?carInfo.getStartupState():null);
				upStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				upStmt.setString(8, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
				upStmt.executeUpdate();
				upStmt.close();
				PreparedStatement ipStmt = (PreparedStatement) conn.clientPrepareStatement(isql);
				ipStmt.setString(1, carInfo.getLongitude()!=null?carInfo.getLongitude():CarCacheManager.getLongitude());
				ipStmt.setString(2, carInfo.getLatitude()!=null?carInfo.getLatitude():CarCacheManager.getLatitude());
				ipStmt.setString(3, carInfo.getCarDoorState()!=null?carInfo.getCarDoorState():null);
				ipStmt.setString(4, carInfo.getLcarWinState()!=null?carInfo.getLcarWinState():null);
				ipStmt.setString(5, carInfo.getRcarWinState()!=null?carInfo.getRcarWinState():null);
				ipStmt.setString(6, carInfo.getStartupState()!=null?carInfo.getStartupState():null);
				ipStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				ipStmt.setString(8, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
				ipStmt.executeUpdate();
				System.out.println("插入成功");
				ipStmt.close();
				conn.close();
			}
			if(carInfo.getCterminal().equals("61311008618497")||carInfo.getCterminal().equals("61311007966624")||carInfo.getCterminal().equals("61311007976185")){
			//测试
//			if(carInfo.getCterminal().equals("61311007978429")){
				/**
				 * 不使用终端经纬度
				 */
//				if(carInfo.getCterminal().equals("61311008618497")){
//					usql = "UPDATE CarInfo SET ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006548969'";
//				}else if(carInfo.getCterminal().equals("61311007966624")){
//					usql = "UPDATE CarInfo SET ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006539802'";
//				}else{
//					usql = "UPDATE CarInfo SET ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006832439'";
//				}
//				isql = "INSERT INTO MessageInfo(ctotalMileage,speed,modifyTime,cterminal,soc) VALUES(?,?,?,?,?)";
//				PreparedStatement upStmt = (PreparedStatement) conn.clientPrepareStatement(usql);
//				upStmt.setDouble(1, carInfo.getCtotalMileage()!=null?carInfo.getCtotalMileage():0.00);
//				upStmt.setDouble(2, carInfo.getSpeed()!=null?carInfo.getSpeed():0);
//				upStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//				upStmt.setDouble(4, carInfo.getSoc()!=null?carInfo.getSoc():100.0);
//				upStmt.executeUpdate();
//				upStmt.close();
//				PreparedStatement ipStmt = (PreparedStatement) conn.clientPrepareStatement(isql);
//				ipStmt.setDouble(1, carInfo.getCtotalMileage()!=null?carInfo.getCtotalMileage():0.00);
//				ipStmt.setDouble(2, carInfo.getSpeed()!=null?carInfo.getSpeed():0);
//				ipStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//				ipStmt.setString(4, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
//				ipStmt.setDouble(5, carInfo.getSoc()!=null?carInfo.getSoc():100.0);
//				ipStmt.executeUpdate();
				/**
				 * 终端经纬度使用
				 */
				if(carInfo.getCterminal().equals("61311008618497")){
					usql = "UPDATE CarInfo SET longitude = ?,latitude = ?,ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006548969'";
				}else if(carInfo.getCterminal().equals("61311007966624")){
					usql = "UPDATE CarInfo SET longitude = ?,latitude = ?,ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006539802'";
				}else{
					usql = "UPDATE CarInfo SET longitude = ?,latitude = ?,ctotalMileage = ?, speed = ?, modifyTime = ?, soc = ? WHERE cterminal = '61311006832439'";
				}
				isql = "INSERT INTO MessageInfo(longitude,latitude,ctotalMileage,speed,modifyTime,cterminal,soc) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement upStmt = (PreparedStatement) conn.clientPrepareStatement(usql);
				upStmt.setString(1, carInfo.getLongitude()!=null?carInfo.getLongitude():CarCacheManager.getLongitude());
				upStmt.setString(2, carInfo.getLatitude()!=null?carInfo.getLatitude():CarCacheManager.getLatitude());
				upStmt.setDouble(3, carInfo.getCtotalMileage()!=null?carInfo.getCtotalMileage():0.00);
				upStmt.setDouble(4, carInfo.getSpeed()!=null?carInfo.getSpeed():0);
				upStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				upStmt.setDouble(6, carInfo.getSoc()!=null?carInfo.getSoc():100.0);
				upStmt.executeUpdate();
				upStmt.close();
				PreparedStatement ipStmt = (PreparedStatement) conn.clientPrepareStatement(isql);
				ipStmt.setString(1, carInfo.getLongitude()!=null?carInfo.getLongitude():CarCacheManager.getLongitude());
				ipStmt.setString(2, carInfo.getLatitude()!=null?carInfo.getLatitude():CarCacheManager.getLatitude());
				ipStmt.setDouble(3, carInfo.getCtotalMileage()!=null?carInfo.getCtotalMileage():0.00);
				ipStmt.setDouble(4, carInfo.getSpeed()!=null?carInfo.getSpeed():0);
				ipStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				ipStmt.setString(6, carInfo.getCterminal()!=null?carInfo.getCterminal():null);
				ipStmt.setDouble(7, carInfo.getSoc()!=null?carInfo.getSoc():100.0);
				ipStmt.executeUpdate();
				System.out.println("插入成功");
				ipStmt.close();
				conn.close();
			}

			return Result.ResultBuilder.buildSuccessResult(OK, true);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.ResultBuilder.buildFailerResult(ERROR, false);
		}
	}

	/**
	 * 实时计算行驶里程
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result nowcar(Double crunMileage, Integer cid, OrderInfo orderInfo) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(cid, CAR_ID_NULL),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(crunMileage, Message.CAR_TOTAL_MILEAGE_FORMAT_ERROR),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(orderInfo.getOrderNumber(), Message.ORDER_NUMBER_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			CarInfo carInfo = carInfoDao.getCarInfoById(cid);
			Double runlength =carInfo.getCtotalMileage() - crunMileage;
			orderInfo.setRunLength(runlength);
			orderInfoMapper.modifyOrder(orderInfo);
			OUCInfo oucInfo = new OUCInfo();
			//纬度
			oucInfo.setLatitude(carInfo.getLatitude());
			//经度
			oucInfo.setLongitude(carInfo.getLongitude());
			//里程
			oucInfo.setRun_Length(runlength);
			//车速
			oucInfo.setSpeed(carInfo.getSpeed());
			return Result.ResultBuilder.buildSuccessResult(OK, oucInfo);
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}

	/**
	 * 与netty通道链接发送数据
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result sendData(CarInfo carInfo) {
		Map<String, ChannelHandlerContext> ch = MessageHandler.getClientmap();
		//括号中的内容是获取终端号，传入的是key
		ChannelHandlerContext ctx = ch.get(carInfo.getCterminal());
		byte[] msg = new byte[6];
		msg[0] = 126;
		msg[1] = 125;
		//车门//车门状态是否打开
		if(carInfo.getCarDoorState()!=null){
			if(carInfo.getCarDoorState().equals("1")){
				msg[2] = 1;
			}else if(carInfo.getCarDoorState().equals("2")){
				msg[2] = 2;
			}else{
				msg[2] = 0;
			}
		}else{
			msg[2] = 0;
		}
		//授权
		if(carInfo.getStartupState()!=null){
			if(carInfo.getStartupState().equals("1")){
				msg[3] = 1;
			}else if(carInfo.getStartupState().equals("2")){
				msg[3] = 2;
			}else{
				msg[3] = 0;
			}
		}else{
			msg[3] = 0;
		}
		//左窗
		if(carInfo.getLcarWinState()!=null){
			if(carInfo.getLcarWinState().equals("1")){
				msg[4] = 1;
			}else if(carInfo.getLcarWinState().equals("2")){
				msg[4] = 2;
			}else{
				msg[4] = 0;
			}
		}else{
			msg[4] = 0;
		}
		//右窗
		if(carInfo.getRcarWinState()!=null){
			if(carInfo.getRcarWinState().equals("1")){
				msg[5] = 1;
			}else if(carInfo.getRcarWinState().equals("2")){
				msg[5] = 2;
			}else{
				msg[5] = 0;
			}
		}else{
			msg[5] = 0;
		}
		System.out.println(Arrays.toString(msg));
		ByteBuf bmsg = Unpooled.buffer(256); 
		try{
			bmsg.writeBytes(msg);
			ctx.channel().writeAndFlush(bmsg);
			CarInfo carInfo2 = carInfoDao.getCarInfoById(carInfo.getCid());
			return Result.ResultBuilder.buildSuccessResult(OK, carInfo2);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.ResultBuilder.buildFailerResult(ERROR, false);
		}
	}

	/**
	 * 通过vin查询车辆信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Result getCarInfoByVin(String cvin) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(cvin, Message.CAR_VIN_FORMAT_ERROR),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			CarInfo carInfo = carInfoDao.getCarInfoByVin(cvin);
			return Result.ResultBuilder.buildSuccessResult(OK, carInfo);
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}

	//通过终端编号查询通道
	@Override
	public Result findChannel(String cterminal) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(cterminal, Message.TERMINAL_ID_NULL),
				new NotNullInterceptor());
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			Map<String, ChannelHandlerContext> ch = MessageHandler.getClientmap();
			ChannelHandlerContext ctx = ch.get(cterminal);
			if(ctx != null){
				return Result.ResultBuilder.buildSuccessResult(OK, null);
			}else{
				return Result.ResultBuilder.buildFailerResult(Message.TERMINAL_CHANNEL_NULL, null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	/**
	 * 上传附件
	 * @param carID 车辆ID
	 * @param file 上传文件
	 * @param request 请求
	 * @return
	 */
	@Override
	@Transactional
	public Result uploadAttachment(Integer carID, MultipartFile file, HttpServletRequest request) {
		ResultBean resultBean = this.commonService.uploadFile(file, request);
		
		if(resultBean.getStatus()) {
			int uploadCount = this.carInfoDao.uploadAttachment(carID, resultBean.getData().toString());
			
			if(uploadCount > 0) {
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	/**
	 * 下载附件
	 * @param carIDArray 车辆ID数组
	 * @param response 响应
	 * @throws IOException
	 */
	@Override
	public void downloadCarAttachment(int[] carIDArray, HttpServletResponse response) throws IOException {
		if(carIDArray==null || carIDArray.length==0) {
			return;
		}
		
		if(carIDArray.length == 1) {
			CarInfo carInfo = this.carInfoDao.getCarInfoById(carIDArray[0]);
			
			if(carInfo != null) {
				String attachment = carInfo.getAttachment();
				String downloadAttachmentName = FileUtilForCar.getDownloadAttachmentName(carInfo);
				
				FileUtil.downloadFile(attachment, downloadAttachmentName, response);
			}
		}else {
			List<CarInfo> carInfoList = this.carInfoDao.getCarInfoList(carIDArray);
			
			FileUtilForCar.downloadFiles(carInfoList, "车辆附件", response);
		}
	}
}