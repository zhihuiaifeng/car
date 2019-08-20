package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.validate.entities.ValidateHolder;

public class NotNullInterceptor implements Interceptor{

	public ValidateHolder interceptor(ValidateHolder holder) {
		return Assert.isNull(holder.getFiledValue()) ? holder : null;
	}

}
