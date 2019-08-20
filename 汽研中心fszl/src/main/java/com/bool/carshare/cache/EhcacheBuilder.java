package com.bool.carshare.cache;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

/**
 * EhcacheManager -> create date 2017/6/7
 * this is CacheManager util
 * create singel CacheManager 
 * @author tzw
 *
 */
public final class EhcacheBuilder {
	
	private static final Logger log = Logger.getLogger(EhcacheBuilder.class);
	
	private static final String EHCACHE_CONFIG_PATH = "/ehcache.xml";
	
	public static CacheManager build(){
		return EhcacheBuilder0.cacheManager;
	}
	
	private static class EhcacheBuilder0{
		
		
		public static CacheManager cacheManager;
		
		static{
			
			try {
				cacheManager = CacheManager.create(
							new ClassPathResource(EHCACHE_CONFIG_PATH).getURL());
			} catch (Exception e) {
				
				log.info("create cache failer -> ");
				log.info(e.getMessage());
				
				
			}
			
		}
		
		
		
		
	}
	
	


}
