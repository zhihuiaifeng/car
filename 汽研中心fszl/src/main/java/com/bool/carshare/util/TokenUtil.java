/**
 * 
 */
package com.bool.carshare.util;

import java.util.HashMap;
import java.util.Map;

import com.bool.carshare.bean.TokenBean;

/**
 * TokenUtil
 * @author wangw
 */
public class TokenUtil {
	/**
	 * Token池
	 */
	private static Map<String, TokenBean> tokenPool = new HashMap<String, TokenBean>();
	
	/**
	 * 保存Token
	 * @param token
	 */
	public static void saveToken(TokenBean token) {
		if(token != null) {
			String tokenID = token.getID();
			
			tokenPool.put(tokenID, token);
		}
	}
	
	/**
	 * 移除Token
	 * @param token
	 */
	public static void removeToken(TokenBean token) {
		if(token != null) {
			String tokenID = token.getID();
			
			tokenPool.remove(tokenID);
		}
	}
	
	/**
	 * 获得Token
	 * @param tokenID
	 * @return
	 */
	public static TokenBean getToken(String tokenID) {
		TokenBean token = tokenPool.get(tokenID);
		
		return token;
	}
}