package com.bool.carshare.util.auth;

import com.bool.carshare.cache.EhcacheManager;
/**
 * 经纬度缓存
 * @author yxy
 *
 */
public final class CarCacheManager {

	//经度
	private static final String CAR_LONGITUDE = "CAR_LONGITUDE";
	//纬度
	private static final String CAR_LATITUDE= "CAR_LATITUDE";
	
	/**
	 * 获取经度缓存
	 * @return 经度
	 */
	public static String getLongitude(){
		if(EhcacheManager.get(CAR_LONGITUDE,"longitude") != null){
			return (String) EhcacheManager.get(CAR_LONGITUDE,"longitude");
		}
		return null;
	}
	/**
	 * 获取纬度缓存
	 * @return 纬度
	 */
	public static String getLatitude(){
		if(EhcacheManager.get(CAR_LATITUDE,"latitude") != null){
			return (String) EhcacheManager.get(CAR_LATITUDE,"latitude");
		}
		return null;
	}
	/**
	 * 插入经度缓存
	 * @param longitude
	 */
	public static void putLongitude(String longitude){
		EhcacheManager.put(CAR_LONGITUDE, "longitude", longitude);
	}
	/**
	 * 插入纬度缓存
	 * @param latitude
	 */
	public static void putLatitude(String latitude){
		EhcacheManager.put(CAR_LATITUDE, "latitude", latitude);
	}
	/**
	 * 删除经度缓存
	 * @return 是否删除成功
	 */
	public static boolean removeLongitude(){
		return EhcacheManager.remove(CAR_LONGITUDE, "longitude");
	}
	/**
	 * 删除纬度缓存
	 * @return 是否成功删除
	 */
	public static boolean removeLatitude(){
		return EhcacheManager.remove(CAR_LATITUDE, "latitude");
	}
}
