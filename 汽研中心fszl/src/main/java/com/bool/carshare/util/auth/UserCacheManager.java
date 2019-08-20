package com.bool.carshare.util.auth;

import com.bool.carshare.cache.EhcacheManager;
import com.bool.carshare.entity.UserInfo;
import com.bool.carshare.util.Assert;
import com.bool.carshare.util.UUIDUtils;
/**
 * user token mananger -> create date 2017/6/7 
 * this is ehcache util 
 * use ehcache 2.x
 * 
 * @author tzw
 */
public final class UserCacheManager {

	// token cache key 
	private static final String TOKEN_CACHE_KEY = "USER_TOKEN_CACHE";
	
	private static final String USER_LOGIN_FLAG = "USER_LOGIN_FLAG";
	

	// get token  
	public static TokenModal getToken(String token) {

		Object tokenModal = null;
		if (!Assert.isNull((tokenModal = EhcacheManager.get(TOKEN_CACHE_KEY,token)))) {
			return (TokenModal) tokenModal;
		}
		return null;
	}
	
	
	// create token 
	// param userInfo
	public static TokenModal createToken(UserInfo userInfo){
		TokenModal token = new TokenModal(userInfo,System.currentTimeMillis(),UUIDUtils.uuid());
		EhcacheManager.put(TOKEN_CACHE_KEY,token.getToken(), token);
		return token;
	}
	
	public static boolean userExists(String userName){
		return EhcacheManager.get(USER_LOGIN_FLAG, userName) == null ;
		// cache -> cacheName -> map.get(key)
	}
	
	public static void putUser(UserInfo userInfo){
		EhcacheManager.put(USER_LOGIN_FLAG, userInfo.getUname(), userInfo);
	}

	
	public static boolean removeUser(String userName){
		return EhcacheManager.remove(USER_LOGIN_FLAG,userName);
	}
}
