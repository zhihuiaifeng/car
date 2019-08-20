package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.validate.entities.ValidateHolder;

public class RunTimeInterceptor implements Interceptor {

	public ValidateHolder interceptor(ValidateHolder holder) {
		if(holder.getFiledValue().toString().equals("error")){
			return holder;
		}
		if(holder.getFiledValue().toString().equals("timeError")){
			return holder;
		}
		return null;
	}

}
