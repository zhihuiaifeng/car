package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.validate.entities.ValidateHolder;

public class ValueContaisInterceptor implements Interceptor{
	
	private Object[] objs ;
	
	public ValueContaisInterceptor(Object ... objs){
		Assert.notNull(objs, "params is null");
		this.objs = objs;
	}

	public ValidateHolder interceptor(ValidateHolder holder) {
		if(Assert.isNull(holder.getFiledValue())){
			return null;
		}
		Object fieldValue = holder.getFiledValue();
		
		for(Object obj : objs){
			if(obj.equals(fieldValue) || obj == fieldValue){
				return null;
			}
		}
		
		return holder;
	}
	

}
