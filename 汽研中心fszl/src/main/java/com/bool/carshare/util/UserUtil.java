/**
 * 
 */
package com.bool.carshare.util;

import java.util.HashMap;
import java.util.Map;

import com.bool.carshare.bean.TokenBean;
import com.bool.carshare.entity.RoleInfo;

/**
 * UserUtil
 * @author wangw
 */
public class UserUtil {
	/**
	 * UserInfo池
	 */
	private static Map<String, Object> userInfoPool = new HashMap<String, Object>();
	
	/**
	 * 保存UserInfo
	 * @param sessionID
	 * @param userInfo
	 */
	public static void saveUserInfo(String sessionID, Object userInfo) {
		userInfoPool.put(sessionID, userInfo);
	}
	
	/**
	 * 移除UserInfo
	 * @param sessionID
	 */
	public static void removeUserInfo(String sessionID) {
		userInfoPool.remove(sessionID);
	}
	
	/**
	 * 获得UserInfo
	 * @param sessionID
	 * @return
	 */
	public static Object getUserInfo(String sessionID) {
		Object userInfo = userInfoPool.get(sessionID);
		
		return userInfo;
	}
	
	/**
	 * 校验Token
	 * @param tokenID
	 * @return
	 */
	public static Boolean verifyToken(String tokenID) {
		TokenBean token = TokenUtil.getToken(tokenID);
		if(token != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 是否登录
	 * @param sessionID
	 * @return
	 */
	public static Boolean isLogin(String sessionID) {
//		HttpSession session = SessionUtil.getSession(sessionID);
//		
//		Object userInfo = SessionUtil.getUserInfo(session);
//		if(userInfo != null) {
//			return true;
//		}
		
		Object userInfo = getUserInfo(sessionID);
		if(userInfo != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 是否是管理员
	 * @param roleInfo
	 * @return
	 */
	public static Boolean isAdmin(RoleInfo roleInfo) {
		if(roleInfo != null) {
			String roleName = roleInfo.getName();
			
			if("admin".equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}
}