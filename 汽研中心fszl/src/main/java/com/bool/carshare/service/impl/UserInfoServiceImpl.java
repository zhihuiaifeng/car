package com.bool.carshare.service.impl;

import static com.bool.carshare.util.Message.AGE_FORMAT_ERROR;
import static com.bool.carshare.util.Message.DRIVER_FORMAT_ERROR;
import static com.bool.carshare.util.Message.ERROR;
import static com.bool.carshare.util.Message.IDCARD_FORMAT_ERROR;
import static com.bool.carshare.util.Message.NAME_OR_PWD_ERROR;
import static com.bool.carshare.util.Message.OK;
import static com.bool.carshare.util.Message.PAGE_INVALID;
import static com.bool.carshare.util.Message.PAGE_PARAM_INVALID;
import static com.bool.carshare.util.Message.ROW_INVALID;
import static com.bool.carshare.util.Message.SEX_FORMAT_ERROR;
import static com.bool.carshare.util.Message.UNAME_FORMAT_ERROR;
import static com.bool.carshare.util.Message.UPWD_FORMAT_ERROR;
import static com.bool.carshare.util.Message.USER_EXISTS;
import static com.bool.carshare.util.Message.USER_ID_INVALID;
import static com.bool.carshare.util.Message.USER_NOT_FOUND;
import static com.bool.carshare.util.Message.USER_STATE_FORMAT_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.mapper.UserInfoMapper;
import com.bool.carshare.service.UserInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.auth.UserCacheManager;
import com.bool.carshare.util.encryption.Encryption;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
import com.bool.carshare.util.validate.interceptors.RegexInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueContaisInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueLengthInterceptor;

/**
 * user info service -> create date 2017/6/6
 * 
 * @author tzw
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoDao;

	// encryption service
	@Autowired
	private Encryption encryption;

	
	

	// add user
	@Transactional(propagation = Propagation.REQUIRED)
	public Result saveUser(UserInfo user) {
		Validator validator = new Validator();
		// 驾照号 长度必须等于12
		validator.addInterceptor(new ValidateHolder(user.getDriverNo(), DRIVER_FORMAT_ERROR),
				new ValueLengthInterceptor(12, 12));
		validator.addInterceptor(new ValidateHolder(user.getUname(), UNAME_FORMAT_ERROR),
				new RegexInterceptor(RegexInterceptor.PHONE), new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(user.getIdcard(), IDCARD_FORMAT_ERROR),
				new RegexInterceptor(RegexInterceptor.IDCARD));
		validator.addInterceptor(new ValidateHolder(user.getAge(), AGE_FORMAT_ERROR), new NumberInterceptor(100, 18));
		validator.addInterceptor(new ValidateHolder(user.getSex(), SEX_FORMAT_ERROR),
				new ValueContaisInterceptor("1", "0"));
		validator.addInterceptor(new ValidateHolder(user.getUpwd(), UPWD_FORMAT_ERROR),
				new ValueLengthInterceptor(12, 6), new NotNullInterceptor());
		// get validate result .
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			// user exists ??????
			UserInfo userInfoCondition = new UserInfo();
			userInfoCondition.setUname(user.getUname());
			// if user exists ,return error
			if (userInfoDao.getUserCount(userInfoCondition) > 0) {
				return Result.ResultBuilder.buildFailerResult(USER_EXISTS, null);
			}

			// password encryption -> save user
			user.setUpwd(encryption.encryption(user.getUpwd()));
			userInfoDao.saveUser(user);
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		} else {
			// validate failer .
			return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
		}
	}

	/**
	 * 登陆
	 */
	public Result login(String userName, String password) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(userName, UNAME_FORMAT_ERROR), new ValueLengthInterceptor(11, 11),
				new RegexInterceptor(RegexInterceptor.PHONE),new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(password, UPWD_FORMAT_ERROR), new ValueLengthInterceptor(20, 6),
				new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			UserInfo userInfo = userInfoDao.login(userName, encryption.encryption(password));
			if (userInfo == null) {
				return Result.ResultBuilder.buildFailerResult(NAME_OR_PWD_ERROR, userInfo);
			}
			if(UserCacheManager.userExists(userName)){
				// put User
				UserCacheManager.putUser(userInfo);
				userInfo.setLoginState("1");
				userInfoDao.upLoginState(userName, userInfo.getLoginState());
				return Result.ResultBuilder.buildSuccessResult(OK, UserCacheManager.createToken(userInfo));
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.USER_DOUBLE_ERROR, userInfo);
			}

		} else {
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}
	}

	@Transactional(readOnly = true)
	public Result getUser(PageRequest<UserInfo> pageRequest) {
		if (!Assert.isNull(pageRequest)) {
			Validator validator = new Validator();
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateHolder = null;
			if (Assert.isNull((validateHolder = validator.fristError()))) {
				List<UserInfo> users = userInfoDao.getUser(pageRequest);
				UserInfo userInfo = pageRequest.getCondition();
				int total = userInfoDao.getUserCount(userInfo);
				PageResponse rp = new PageResponse(total, users);
				return Result.ResultBuilder.buildSuccessResult(OK, rp);
			} else {
				return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
			}

		}
		return Result.ResultBuilder.buildFailerResult(PAGE_PARAM_INVALID, null);
	}

	//上传照片
//	public Result uploadIdcard_a(MultipartFile file, Integer userId, String basePath, String httpPath) {
//		String filePath = null;
//		try {
//			Result result = uploadFileService.upload(file, basePath, httpPath);
//			filePath = result.getResult().toString();
//			return Result.ResultBuilder.buildSuccessResult(OK, filePath);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.ResultBuilder.buildFailerResult(ERROR, null);
//		}
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUid(userId);
//		if(imageCode.equals("1")){
//			userInfo.setIdcard_photo_a(filePath);
//		}else if(imageCode.equals("2")){
//			userInfo.setIdcard_photo_b(filePath);
//		}else if(imageCode.equals("3")){
//			userInfo.setDriver_photo_a(filePath);
//		}else if(imageCode.equals("4")){
//			userInfo.setDriver_photo_b(filePath);
//		}else{
//			userInfo.setPhoto(filePath);
//		}
//		userInfoDao.updateUserInfo(userInfo);
//	}


	//完善用户信息
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateUser(UserInfo user) {
		try{
			UserInfo userInfo = userInfoDao.getUserInfoById(user.getUid());
			return Result.ResultBuilder.buildSuccessResult(OK, userInfo);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateUserState(int userId, int state) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(state, USER_STATE_FORMAT_ERROR),
				new ValueContaisInterceptor(1, 2, 3));
		ValidateHolder validateHolder = null;
		if (!Assert.isNull((validateHolder = validator.fristError()))) {
			return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
		}
		if (userInfoDao.getUserInfoById(userId) == null) {
			return Result.ResultBuilder.buildFailerResult(USER_NOT_FOUND, null);
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUid(userId);
		userInfo.setUstate(state);
		userInfoDao.updateUserInfo(userInfo);
		return Result.ResultBuilder.buildSuccessResult(OK, null);

	}
	/**
	 * 查询用户信息
	 */
	@Transactional(readOnly = true)
	public Result getUserInfoById(Integer userId){
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(userId, USER_ID_INVALID), new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			UserInfo userInfo = userInfoDao.getUserInfoById(userId);
			return Result.ResultBuilder.buildSuccessResult(OK, userInfo);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}

	//退出登录
	public Result outUserInfo(String uname){
		try{
			UserCacheManager.removeUser(uname);
			return Result.ResultBuilder.buildSuccessResult(OK, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ResultBuilder.buildFailerResult(ERROR, null);
		}
		
	}
	
	
	//消费
	public Result updateMoney(UserInfo userInfo){
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(userInfo.getCashPledge(), Message.CASH_PLEDGE_ERROR),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(userInfo.getLastConsumption(), Message.LAST_CONSUMPTION_ERROR),
				new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			userInfoDao.updateBalances(userInfo);
			UserInfo user = userInfoDao.getUserInfoById(userInfo.getUid());
			return Result.ResultBuilder.buildSuccessResult(OK, user);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}

	//登陆状态
	public Result upLoginState(String uname, String loginState) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(uname, UNAME_FORMAT_ERROR),
				new NotNullInterceptor());
		validator.addInterceptor(new ValidateHolder(loginState, USER_ID_INVALID),
				new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			UserCacheManager.removeUser(uname);
			userInfoDao.upLoginState(uname, loginState);
			return Result.ResultBuilder.buildSuccessResult(OK, true);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), false);
	}
	
	
	
	
}
