package com.bool.carshare.util.validate.interceptors;

import com.bool.carshare.util.validate.entities.ValidateHolder;

public interface Interceptor {
	
	
	ValidateHolder interceptor(ValidateHolder holder);

}
