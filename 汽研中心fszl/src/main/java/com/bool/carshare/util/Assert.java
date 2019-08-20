package com.bool.carshare.util;

/**
 * assert  create date 2017/6/5 
 * @author tzw
 *
 */
public final class Assert {
	
	
	private Assert(){}
	
	
	
	public static boolean isNull(Object val){
		if(val==null || "".equals(val)) {
			return true;
		}
		
		return false;
	}
	
	
	public static void notNull(Object val,String message){
		if(isNull(val)){
			throw new NullPointerException(message);
		}
	}
	
	


}
