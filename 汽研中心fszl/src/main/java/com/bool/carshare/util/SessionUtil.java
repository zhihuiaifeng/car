/**
 * 
 */
package com.bool.carshare.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bool.carshare.consts.IUserConsts;

/**
 * SessionUtil
 * @author wangw
 */
public class SessionUtil {
	/**
	 * Session池
	 */
	private static Map<String, HttpSession> sessionPool = new HashMap<String, HttpSession>();
	
	/**
	 * 保存Session
	 * @param session
	 */
	public static void saveSession(HttpSession session) {
		if(session != null) {
			String sessionID = session.getId();
			
			sessionPool.put(sessionID, session);
		}
	}
	
	/**
	 * 移除Session
	 * @param session
	 */
	public static void removeSession(HttpSession session) {
		if(session != null) {
			String sessionID = session.getId();
			
			sessionPool.remove(sessionID);
		}
	}
	
	/**
	 * 获得Session
	 * @param sessionID
	 * @return
	 */
	public static HttpSession getSession(String sessionID) {
		HttpSession session = sessionPool.get(sessionID);
		
		return session;
	}
	
	/**
	 * 获得Session
	 * @param request
	 * @param createNew
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request, Boolean createNew) {
		if(request == null) {
			return null;
		}
		
		HttpSession session = request.getSession(createNew);
		
		return session;
	}
	
	/**
	 * 销毁Session
	 * @param sessionID
	 */
	public static void destroySession(String sessionID) {
		HttpSession session = getSession(sessionID);
		
		if(session != null) {
			try {
				session.invalidate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 向Session中放入对象
	 * @param session
	 * @param key
	 * @param object
	 */
	public static void setObject(HttpSession session, String key, Object object) {
		if(session == null) {
			return;
		}
		
		session.setAttribute(key, object);
	}
	
	/**
	 * 从Session中获得对象
	 * @param session
	 * @param key
	 * @return
	 */
	public static Object getObject(HttpSession session, String key) {
		if(session == null) {
			return null;
		}
		
		try {
			Object object = session.getAttribute(key);
			
			return object;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 向Session中放入用户信息
	 * @param session
	 * @param userInfo
	 */
	public static void setUserInfo(HttpSession session, Object userInfo) {
		setObject(session, IUserConsts.USER_INFO, userInfo);
	}
	
	/**
	 * 从Session中获得用户信息
	 * @param session
	 * @return
	 */
	public static Object getUserInfo(HttpSession session) {
		Object userInfo = getObject(session, IUserConsts.USER_INFO);
		
		return userInfo;
	}
}