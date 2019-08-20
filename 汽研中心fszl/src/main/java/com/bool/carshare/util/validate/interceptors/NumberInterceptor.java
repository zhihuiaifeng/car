package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.validate.entities.ValidateHolder;

public class NumberInterceptor implements Interceptor{
	
	private double maxValue;
	
	private double minValue;
	

	public NumberInterceptor(double maxValue, double minValue) {
		super();
		this.maxValue = maxValue;
		this.minValue = minValue;
	}



	public ValidateHolder interceptor(ValidateHolder holder) {
		try{
			if(Assert.isNull(holder.getFiledValue())){
				return null;
			}
			double number = 0;
			if(holder.getFiledValue() instanceof Integer){
				number = Integer.parseInt(holder.getFiledValue().toString());
			}else if(holder.getFiledValue() instanceof Double){
				number = Double.parseDouble(holder.getFiledValue().toString());
			}
			
			
			return number <= maxValue && number >= minValue ? null : holder;
		}catch(Exception e){
			return holder;
		}
	}

}
