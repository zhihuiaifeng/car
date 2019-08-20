package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.StringUtils;
import com.bool.carshare.util.validate.entities.ValidateHolder;

public class ValueLengthInterceptor implements Interceptor {
	
	private int maxLength;
	
	private int minLength;
	
	

	public ValueLengthInterceptor(int maxLength,int minLength) {
		super();
		this.minLength = minLength;
		this.maxLength = maxLength;
	}



	public ValidateHolder interceptor(ValidateHolder holder) {
		if(Assert.isNull(holder.getFiledValue())){
			return null;
		}
		return StringUtils.isLength(holder.getFiledValue().toString(), maxLength, minLength) ? null : holder;
	}
	
	

}
