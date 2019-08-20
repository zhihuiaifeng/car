package com.bool.carshare.util;

public final class StringUtils {

	
	public static boolean isEmpty(String val){
		return val == null || val.toString().trim().length() == 0;
	}
	
	public static boolean isLength(String val,int maxLength,int minLength){
		return isMaxLength(val, maxLength) && isMinLength(val, minLength);
	}
	
	public static boolean isMinLength(String val,int minLength){
		return !isEmpty(val) && val.length() >= minLength;
	}
	
	public static boolean isMaxLength(String val,int maxLength){
		return !isEmpty(val) && val.length() <= maxLength;
	}
}
