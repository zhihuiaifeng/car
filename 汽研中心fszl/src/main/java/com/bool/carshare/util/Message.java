package com.bool.carshare.util;
/**
 * Message ->
 * create date 2017/6/12
 * @author tzw
 */
public final class Message {
	private Message() {
		
	}
	
	/*
	 * ------------------------------------
	 * 
	 * System MessageCode
	 * 
	 * ------------------------------------
	 */
	
	public static final String OK = "成功";
	public static final String ERROR = "异常";
	
	/*
	 * -------------------------------------
	 * 
	 * Page MessageCode
	 * 
	 * -------------------------------------
	 */
	
	public static final String ROW_INVALID = "无效的行数";
	public static final String PAGE_INVALID = "无效的页数";
	public static final String PAGE_PARAM_INVALID = "无效的分页参数";
	
	/*
	 * -------------------------------------
	 * 
	 * Upload File Message
	 * 
	 * -------------------------------------
	 */
	
	public static final String UPLOAD_FILE_INVALID = "无效的上传文件";
	public static final String UPLOAD_FILE_NAME_FORMAT_ERROR = "文件名格式异常";
	
	/*
	 * -------------------------------------
	 * 
	 * UserInfo MessageCode
	 * 
	 * -------------------------------------
	 */
	
	public static final String DRIVER_FORMAT_ERROR = "驾照号格式异常";
	public static final String UNAME_FORMAT_ERROR = "用户名格式异常";
	public static final String UPWD_FORMAT_ERROR = "密码格式异常";
	public static final String SEX_FORMAT_ERROR = "性别格式异常";
	public static final String AGE_FORMAT_ERROR = "年龄格式异常";
	public static final String IDCARD_FORMAT_ERROR = "身份证号格式异常";
	public static final String NAME_OR_PWD_ERROR = "账号或密码异常";
	public static final String USER_EXISTS = "用户已存在";
	public static final String USER_TIME_OUT = "登陆超时";
	public static final String USER_ID_INVALID = "用户ID异常";
	public static final String USER_NOT_FOUND = "用户不存在";
	public static final String USER_STATE_FORMAT_ERROR = "用户状态格式异常";
	public static final String USER_DOUBLE_ERROR = "用户已登录";
	public static final String REALNAME_NULL = "用户姓名为空";
	public static final String PASSWORD_NULL = "密码为空";
	
	/*
	 * -------------------------------------
	 * 
	 * Dot MessageCode
	 * 
	 * -------------------------------------
	 */
	
	public static final String DOT_GPS_X_FORMAT_ERROR = "网点的经度格式异常";
	public static final String DOT_GPS_Y_FORMAT_ERROR = "网点的纬度格式异常";
	public static final String DOT_ADDRESS_FORMAT_ERROR = "网点地址格式异常";
	public static final String DOT_NAME_FORMAT_ERROR = "网点名字格式异常";
	public static final String DOT_CHARGE_NUMBER_INVALID = "无效的充电桩个数";
	public static final String DOT_PARK_LOT_INVALID = "无效的车位";
	public static final String DOT_ID_NULL = "网点ID为空";
	public static final String DOT_CITY_NULL = "所属城市为空";
	public static final String DOT_PHOTO_NULL = "网点图片为空";
	
	public static final String CAR_MODEL_FORMAT_ERROR = "车型名称格式异常";
	public static final String CAR_CLICENSE_FORMAT_ERROR = "牌照格式异常";
	public static final String CAR_STATE_FORMAT_ERROR = "租用状态格式异常";
	public static final String CAR_VIN_FORMAT_ERROR = "vin号格式异常";
	public static final String CAR_TERMINAL_FORMAT_ERROR = "终端编号格式异常";
	public static final String CAR_TYPE_FORMAT_ERROR = "车辆类型格式异常";
	public static final String CAR_SOC_FORMAT_ERROR = "电池剩余电量格式异常";
	public static final String CAR_RUN_MILEAGE_FORMAT_ERROR = "运营阶段总里程格式异常";
	public static final String CAR_TOTAL_MILEAGE_FORMAT_ERROR = "车辆总里程格式异常";
	public static final String CAR_SEAT_NUMBER_FORMAT_ERROR = "车辆座位数格式异常";
	public static final String CAR_AGE_FORMAT_ERROR = "车龄格式异常";
	public static final String RUN_STATE_FORMAT_ERROR = "运营状态格式异常";
	public static final String INSURANCE_DATE_NULL = "投保日期为空";
	public static final String INSURANCE_COMPANY_NULL = "投保公司为空";
	public static final String CAR_PHOTO_NULL = "车辆图片为空";
	public static final String CAR_PERKM_NULL = "车型公里单价为空";
	public static final String CAR_PERMIN_NULL = "车型分钟单价为空";
	public static final String CMODEL_ID_NULL = "车型id为空";
	
	/*
	 * 
	 * 
	 * 订单相关
	 * 
	 * 
	 */
	public static final String ORDER_NUMBER_NULL = "订单编号为空";
	public static final String ORDER_STATE_ERROR = "订单状态异常";
	public static final String STASR_SITE_NULL = "起始地点为空";
	public static final String END_SITE_NULL = "结束地点为空";
	public static final String UID_NULL = "登陆用户为空";
	public static final String TERMINAL_ID_NULL = "终端ID为空";
	public static final String CAR_ID_NULL = "车辆ID为空";
	public static final String MODIFY_ORDER_ERROR = "修改订单数据有误";
	public static final String RUNTIME_ERROR = "行驶时间异常";
	public static final String ORDER_COUNT_ERROR = "用户有未完成的订单";
	public static final String EXPENSES_NULL ="订单费用为空";
	public static final String ERROR_MESSAGE_NULL = "异常原因为空";
	public static final String REPLY_ERROR = "客服回复有误";
	public static final String SUBMIT_TIME_ERROR = "上报时间有误";
	/*
	 * 
	 * 
	 * 管理员相关
	 * 
	 * 
	 * 
	 */
	public static final String ADMIN_NAME_NULL = "用户名为空";
	public static final String ADMIN_ALLNAME_NULL = "用户全名为空";
	public static final String PHONE_ERROR = "电话格式异常";
	public static final String MAILBOX_ERROR = "邮箱格式异常";
	public static final String DEPARTMENT_NULL = "部门为空";
	public static final String USER_STATUS_ERROR = "用户状态异常";
	public static final String USER_LEVEL_ERROR = "用户级别异常";
	public static final String ADMIN_ID_ERROR = "管理员ID为空";
	
	/*
	 * 
	 * 
	 * 支付相关
	 * 
	 * 
	 */
	
	//押金修改类型
	public static final String CASH_PLEDGE_ERROR = "押金状态异常";
	//最后一笔流水
	public static final String LAST_CONSUMPTION_ERROR = "最后一笔流水异常";
	//充值消费标识（0-充值 1-消费 2-押金）
	public static final String RECHARGE_OR_CONSUME = "充值消费标识异常";
	//订单金额
	public static final String FICTITIOUS_FUND_NULL = "订单金额为空";
	//实收金额
	public static final String ACTUAL_FUND_NULL = "实收金额为空";
	//成交状态（0-已成交 1-未成交）
	public static final String TRANSACTION_STATUS_NULL = "成交状态为空";
	//支付id
	public static final String PAY_ID_NULL = "支付ID为空";
	//支付类型（哪种三方）0-余额 1-微信 2-支付宝
	public static final String PAY_STATUS_NULL = "支付类型为空";
	//时间
	public static final String CREATE_TIME_NULL = "时间为空";
	//支付订单异常
	public static final String PAY_ORDER_ERROR = "支付订单异常";
	//付款金额
	public static final String PAYMENT_NULL = "付款金额为空";
	//余额不足
	public static final String BALANCE_NOT_FUND = "余额不足";
	//财务处理状态
	public static final String FINANCE_STATUS_ERROR = "财务处理状态异常";
	
	/*
	 * 车辆维护
	 */
	
	public static final String CAR_CLICENSE_NULL = "车牌号为空";
	public static final String FORM_CHARGER_NULL = "责任人为空";
	
	/*
	 * 终端管理
	 */
	public static final String TERMINAL_CHANNEL_NULL="汽车终端连接故障";
	public static final String TERMINAL_CODE_NULL = "终端编号为空";
	public static final String TERMINAL_SIM_NULL = "sim卡号为空";
	
	/*
	 * 
	 * 优惠券相关
	 * 
	 */
	public static final String DISCOUNT_ID_ERROR = "优惠券id异常";
	public static final String DISCOUNT_NAME_ERROR = "优惠名称异常";
	public static final String DISCOUNT_MONEY_ERROR = "优惠金额异常";
	public static final String DISCOUNT_MEMBER_ERROR = "优惠对象异常";
	public static final String DISCOUNT_STATUS_ERROR = "优惠状态异常";
	public static final String DISCOUNT_ISSUETIME_ERROR = "发放时间异常";
	public static final String DISCOUNT_STARTTIME_ERROR = "开始时间异常";
	public static final String DISCOUNT_ENDTIME_ERROR = "结束时间异常";
	public static final String DISCOUNT_UDSTATE_ERROR = "是否使用标识异常";
}