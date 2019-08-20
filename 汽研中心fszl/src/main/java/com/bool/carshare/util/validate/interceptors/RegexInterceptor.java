package com.bool.carshare.util.validate.interceptors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.StringUtils;
import com.bool.carshare.util.validate.entities.ValidateHolder;

public class RegexInterceptor implements Interceptor{
	
	public static final String PHONE = "^[1][3,4,5,7,8][0-9]{9}$";
	
	public static final String IDCARD = "\\d{15}|\\d{17}[0-9Xx]";
	
	public static final String PHTOT = "(?i).+?\\.(jpg|gif|bmp)";
	
	public static final String MAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	
	
	
	private String pat ;

	
	public RegexInterceptor(String pat){
		this.pat = pat;
	}
	
	
	public ValidateHolder interceptor(ValidateHolder holder) {
		
		if(Assert.isNull(holder.getFiledValue()) || 
				StringUtils.isEmpty(holder.getFiledValue().toString())){
			return null;
		}
		
		Pattern p = Pattern.compile(pat);
		
		Matcher matcher = p.matcher(holder.getFiledValue().toString()) ;
		
		return matcher.matches() ? null : holder;
		
	}
	
	

}
