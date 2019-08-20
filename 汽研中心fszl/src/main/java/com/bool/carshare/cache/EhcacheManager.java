package com.bool.carshare.cache;

import com.bool.carshare.util.Assert;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public final class EhcacheManager {
	
	private static final CacheManager cacheManager = EhcacheBuilder.build();
	
	public static void put(String cacheName,Object key,Object value){
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	public static Object get(String cacheName,Object key){
		Cache cache = cacheManager.getCache(cacheName);
		Element retValue = null;
		return Assert.isNull((retValue = cache.get(key))) ? null : retValue.getObjectValue() ;
	}
	
	public static boolean contaisKey(String cacheName,Object key){
		return !Assert.isNull(get(cacheName, key));
	}
	
	
	public static boolean remove(String cacheName,String key){
		Cache cache = cacheManager.getCache(cacheName);
		return cache.remove(key);
	}
}
