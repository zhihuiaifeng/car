package com.bool.carshare.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bool.carshare.bean.LoginBean;
import com.bool.carshare.bean.ResultBean;
import com.bool.carshare.bean.TokenBean;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.entity.ManageInfo;
import com.bool.carshare.entity.NodeInfo;
import com.bool.carshare.entity.RoleInfo;
import com.bool.carshare.mapper.ManageInfoMapper;
import com.bool.carshare.service.ManageInfoService;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.Message;
import com.bool.carshare.util.PageRequest;
import com.bool.carshare.util.PageResponse;
import com.bool.carshare.util.Result;
import com.bool.carshare.util.SessionUtil;
import com.bool.carshare.util.TokenUtil;
import com.bool.carshare.util.UserUtil;
import com.bool.carshare.util.WebUtil;
import com.bool.carshare.util.encryption.Encryption;
import com.bool.carshare.util.validate.Validator;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.NotNullInterceptor;
import com.bool.carshare.util.validate.interceptors.NumberInterceptor;
import com.bool.carshare.util.validate.interceptors.RegexInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueContaisInterceptor;
import com.bool.carshare.util.validate.interceptors.ValueLengthInterceptor;


@Service("manageInfoService")
public class ManageInfoServiceImpl implements ManageInfoService {
	@Autowired
	private ManageInfoMapper manageInfoMapper;
	
	@Autowired
	private Encryption encryption;
	
	//查看所有管理员
	@Transactional(readOnly=true)
	public Result findAllAdmin(PageRequest<ManageInfo> pageRequest) {
		if (!Assert.isNull(pageRequest)) {
			Validator validator = new Validator();
			validator.addInterceptor(new ValidateHolder(pageRequest.getRow(), Message.ROW_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			validator.addInterceptor(new ValidateHolder(pageRequest.getPage(), Message.PAGE_INVALID),
					new NumberInterceptor(Integer.MAX_VALUE, 0), new NotNullInterceptor());
			ValidateHolder validateHolder = null;
			if (Assert.isNull((validateHolder = validator.fristError()))) {
				List<ManageInfo> admins = manageInfoMapper.getAllAdminInfo(pageRequest);
				for(int m=0; m<admins.size(); m++) {
					ManageInfo manageInfo = admins.get(m);
					
					List<NodeInfo> nodeList = this.manageInfoMapper.getNodeList(manageInfo.getManageId());
					manageInfo.setNodeList(nodeList);
					manageInfo.setNode(manageInfo.generateNode());
				}
				int total = this.manageInfoMapper.getAllAdminInfoNum(pageRequest.getCondition());
				PageResponse rp = new PageResponse(total, admins);
				
				return Result.ResultBuilder.buildSuccessResult(Message.OK, rp);
			}else {
				return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
	}
	
	//新建管理员
	@Transactional(propagation = Propagation.REQUIRED)
	public Result confirmAdmin(ManageInfo manageInfo, WebObject webObject) {
		Validator validator = new Validator();
		//用户姓名
		validator.addInterceptor(new ValidateHolder(manageInfo.getManageAllName(),Message.ADMIN_ALLNAME_NULL)
				, new NotNullInterceptor());
		//手机号
		validator.addInterceptor(new ValidateHolder(manageInfo.getPhone(),Message.PHONE_ERROR)
				, new NotNullInterceptor(), new RegexInterceptor(RegexInterceptor.PHONE));
		//邮箱
		validator.addInterceptor(new ValidateHolder(manageInfo.getMailbox(),Message.MAILBOX_ERROR)
				, new RegexInterceptor(RegexInterceptor.MAIL));
		//部门
		validator.addInterceptor(new ValidateHolder(manageInfo.getDepartment(),Message.DEPARTMENT_NULL)
				, new NotNullInterceptor());
		//用户名
		validator.addInterceptor(new ValidateHolder(manageInfo.getManageLogin(), Message.UNAME_FORMAT_ERROR)
				, new NotNullInterceptor(), new ValueLengthInterceptor(20, 1));
		//密码
		validator.addInterceptor(new ValidateHolder(manageInfo.getPassword(), Message.UPWD_FORMAT_ERROR)
				, new NotNullInterceptor(), new ValueLengthInterceptor(20, 6));
		//状态
		validator.addInterceptor(new ValidateHolder(manageInfo.getUserStatus(),Message.USER_STATUS_ERROR)
				, new NotNullInterceptor(), new ValueContaisInterceptor("1", "0"));
		
		ValidateHolder validateResult = null;
		if (Assert.isNull((validateResult = validator.fristError()))) {
			//验证登陆名是否重复
			if((manageInfoMapper.getEqualUser(manageInfo.getManageLogin()))>0){
				return Result.ResultBuilder.buildFailerResult(Message.USER_EXISTS, null);
			}else {
				//将密码MD5加密
				manageInfo.setPassword(encryption.encryption(manageInfo.getPassword()));
				this.manageInfoMapper.saveAdminInfo(manageInfo);
				this.manageInfoMapper.saveManageNode(manageInfo.getManageId(), manageInfo.generateNodeList());
				return Result.ResultBuilder.buildSuccessResult(Message.OK , null);
			}
		}
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	//修改管理员信息
	@Transactional(propagation=Propagation.REQUIRED)
	public Result updateAdminInfo(ManageInfo manageInfo, WebObject webObject) {
		Validator validator = new Validator();
		//管理员ID
		validator.addInterceptor(new ValidateHolder(manageInfo.getManageId(),Message.ADMIN_ID_ERROR)
				, new NotNullInterceptor());
		//用户姓名
		validator.addInterceptor(new ValidateHolder(manageInfo.getManageAllName(),Message.ADMIN_ALLNAME_NULL)
				, new NotNullInterceptor());
		//手机号
		validator.addInterceptor(new ValidateHolder(manageInfo.getPhone(),Message.PHONE_ERROR)
				, new NotNullInterceptor(), new RegexInterceptor(RegexInterceptor.PHONE));
		//邮箱
		validator.addInterceptor(new ValidateHolder(manageInfo.getMailbox(),Message.MAILBOX_ERROR)
				, new RegexInterceptor(RegexInterceptor.MAIL));
		//部门
		validator.addInterceptor(new ValidateHolder(manageInfo.getDepartment(),Message.DEPARTMENT_NULL)
				, new NotNullInterceptor());
		//用户名
		validator.addInterceptor(new ValidateHolder(manageInfo.getManageLogin(), Message.UNAME_FORMAT_ERROR)
				, new NotNullInterceptor(), new ValueLengthInterceptor(20, 1));
		//密码
		validator.addInterceptor(new ValidateHolder(manageInfo.getPassword(), Message.PASSWORD_NULL)
				, new NotNullInterceptor());
		//状态
		validator.addInterceptor(new ValidateHolder(manageInfo.getUserStatus(),Message.USER_STATUS_ERROR)
				, new NotNullInterceptor(), new ValueContaisInterceptor("1", "0"));
		
		ValidateHolder validateHolder =null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			this.manageInfoMapper.updateAdminInfo(manageInfo);
			this.manageInfoMapper.deleteManageNode(manageInfo.getManageId());
			this.manageInfoMapper.saveManageNode(manageInfo.getManageId(), manageInfo.generateNodeList());
			
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}
		
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}
	
	//删除
	@Transactional(propagation=Propagation.REQUIRED)
	public Result delete(Integer manageId, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(manageId, Message.ADMIN_ID_ERROR),new NotNullInterceptor());
		ValidateHolder validateHolder = null;
		if (Assert.isNull((validateHolder = validator.fristError()))) {
			manageInfoMapper.deleteAdminInfo(manageId);
			return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
		}
		return Result.ResultBuilder.buildFailerResult(validateHolder.getMessageCode(), null);
	}
	
	//获得管理员的信息
	@Override
	@Transactional(readOnly=true)
	public ManageInfo getManageInfo(WebObject webObject) {
		Object para = webObject.getPara();
		
		Object manageIDObject = WebUtil.getParaValue(para, "manageId");
		Integer manageID = Integer.valueOf(String.valueOf(manageIDObject));
		
		ManageInfo manageInfo = this.manageInfoMapper.getManageInfo(manageID);
		if(manageInfo == null) {
			return null;
		}
		
		List<NodeInfo> nodeList = this.manageInfoMapper.getNodeList(manageID);
		manageInfo.setNodeList(nodeList);
		manageInfo.setNode(manageInfo.generateNode());
		
		return manageInfo;
	}
	
	//获得节点的信息
	@Override
	@Transactional(readOnly=true)
	public List<NodeInfo> getNodeList(WebObject webObject) {
		Object para = webObject.getPara();
		
		Object manageIDObject = WebUtil.getParaValue(para, "manageId");
		Integer manageID = Integer.valueOf(String.valueOf(manageIDObject));
		
		List<NodeInfo> nodeList = this.manageInfoMapper.getNodeList(manageID);
		
		return nodeList;
	}
	
	//获得Token
	@Override
	@Transactional(readOnly=true)
	public TokenBean getToken(WebObject webObject) {
		TokenBean tokenBean = new TokenBean();
		Object para = webObject.getPara();
		
		Object manageLoginObject = WebUtil.getParaValue(para, "manageLogin");
		String manageLogin = String.valueOf(manageLoginObject);
		
		Object passwordObject = WebUtil.getParaValue(para, "password");
		String password = String.valueOf(passwordObject);
		
		ManageInfo manageInfo = this.manageInfoMapper.login(manageLogin, this.encryption.encryption(password));
		if(manageInfo == null) {
			tokenBean.setStatus(false);
			tokenBean.setInfo(Message.NAME_OR_PWD_ERROR);
			
			return tokenBean;
		}
		
		List<NodeInfo> nodeList = this.manageInfoMapper.getNodeList(manageInfo.getManageId());
		manageInfo.setNodeList(nodeList);
		manageInfo.setNode(manageInfo.generateNode());
		
		HttpServletRequest request = webObject.getRequest();
		HttpSession session = SessionUtil.getSession(request, true);
		
		tokenBean.setStatus(true);
		tokenBean.setInfo(TokenBean.SUCCEED);
		tokenBean.setID(session.getId());
		tokenBean.setUserInfo(manageInfo);
		
		TokenUtil.saveToken(tokenBean);
		SessionUtil.removeSession(session);
		
		return tokenBean;
	}
	
	//登陆
	@Override
	@Transactional(readOnly=true)
	public LoginBean login(WebObject webObject) {
		LoginBean loginBean = new LoginBean();
		Object para = webObject.getPara();
		
		Object tokenIDObject = WebUtil.getParaValue(para, TokenBean.TOKEN_ID);
		String tokenID = String.valueOf(tokenIDObject);
		
		Boolean verifyToken = UserUtil.verifyToken(tokenID);
		if(!verifyToken) {
			loginBean.setStatus(false);
			loginBean.setInfo(TokenBean.INVALID);
			
			return loginBean;
		}
		
		TokenBean token = TokenUtil.getToken(tokenID);
		Object userInfo = token.getUserInfo();
		TokenUtil.removeToken(token);
		
		HttpServletRequest request = webObject.getRequest();
		HttpSession session = SessionUtil.getSession(request, true);
		String sessionID = session.getId();
//		SessionUtil.setUserInfo(session, userInfo);
		UserUtil.saveUserInfo(sessionID, userInfo);
		
		loginBean.setStatus(true);
		loginBean.setInfo(LoginBean.SUCCEED);
		loginBean.setSessionID(sessionID);
		loginBean.setUserInfo(userInfo);
		
		return loginBean;
	}
	
	//是否登录
	@Override
	@Transactional(readOnly=true)
	public LoginBean isLogin(WebObject webObject) {
		LoginBean loginBean = new LoginBean();
		
		String sessionID = webObject.getSessionID();
		
		Boolean isLogin = UserUtil.isLogin(sessionID);
		if(!isLogin) {
			loginBean.setStatus(false);
			loginBean.setInfo(LoginBean.TIME_OUT);
			
			return loginBean;
		}
		
//		HttpSession session = SessionUtil.getSession(sessionID);
//		Object userInfo = SessionUtil.getUserInfo(session);
		Object userInfo = UserUtil.getUserInfo(sessionID);
		
		loginBean.setStatus(true);
		loginBean.setInfo(LoginBean.SUCCEED);
		loginBean.setSessionID(sessionID);
		loginBean.setUserInfo(userInfo);
		
		return loginBean;
	}
	
	//退出
	@Override
	@Transactional(readOnly=true)
	public LoginBean logout(WebObject webObject) {
		Object para = webObject.getPara();
		
		Object sessionIDObject = WebUtil.getParaValue(para, LoginBean.SESSION_ID);
		String sessionID = String.valueOf(sessionIDObject);
		
		SessionUtil.destroySession(sessionID);
		
		LoginBean loginBean = new LoginBean();
		loginBean.setStatus(false);
		loginBean.setInfo(LoginBean.LOG_OUT);
		
		return loginBean;
	}
	
	/**
	 * 修改密码
	 * @param manageID
	 * @param newPassword
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Result updatePassword(Integer manageID, String newPassword, WebObject webObject) {
		Validator validator = new Validator();
		validator.addInterceptor(new ValidateHolder(newPassword, Message.UPWD_FORMAT_ERROR)
				, new NotNullInterceptor(), new ValueLengthInterceptor(20, 6));
		
		ValidateHolder validateResult = null;
		if(Assert.isNull((validateResult=validator.fristError()))) {
			int updateCount = this.manageInfoMapper.updatePassword(manageID,
					this.encryption.encryption(newPassword));
			
			if(updateCount > 0) {
				return Result.ResultBuilder.buildSuccessResult(Message.OK, null);
			}else {
				return Result.ResultBuilder.buildFailerResult(Message.ERROR, null);
			}
		}
		
		return Result.ResultBuilder.buildFailerResult(validateResult.getMessageCode(), null);
	}
	
	//是否可编辑
	@Override
	@Transactional(readOnly=true)
	public ResultBean isEditable(WebObject webObject) {
		ResultBean resultBean = new ResultBean();
		Object para = webObject.getPara();
		
		Object currentUserIDObject = WebUtil.getParaValue(para, "currentUserID");
		Integer currentUserID = Integer.valueOf(String.valueOf(currentUserIDObject));
		List<RoleInfo> currentUserRoleList = this.manageInfoMapper.getRoleList(currentUserID);
		RoleInfo currentUserMaxRole = null;
		if(currentUserRoleList!=null && currentUserRoleList.size()>0) {
			currentUserMaxRole = currentUserRoleList.get(0);
		}
		
		Object userIDObject = WebUtil.getParaValue(para, "userID");
		Integer userID = Integer.valueOf(String.valueOf(userIDObject));
		List<RoleInfo> userRoleList = this.manageInfoMapper.getRoleList(userID);
		RoleInfo userMaxRole = null;
		if(userRoleList!=null && userRoleList.size()>0) {
			userMaxRole = userRoleList.get(0);
		}
		
		if(currentUserID.equals(userID)) {
			resultBean.setStatus(true);
			
			return resultBean;
		}
		
		if(UserUtil.isAdmin(currentUserMaxRole)) {
			if(!UserUtil.isAdmin(userMaxRole)) {
				resultBean.setStatus(true);
				
				return resultBean;
			}
		}
		
		return resultBean;
	}
}
