package com.bool.carshare.util.validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bool.carshare.util.Assert;
import com.bool.carshare.util.validate.entities.ValidateHolder;
import com.bool.carshare.util.validate.interceptors.Interceptor;

/**
 * validate utils . -> create date 2017/6/5 author tzw
 */
public class Validator {

	private Map<ValidateHolder, Interceptor[]> validatorHolderInterceptors =

			new LinkedHashMap<ValidateHolder, Interceptor[]>();

	public Validator addInterceptor(ValidateHolder ValidatorHolder, Interceptor... interceptors) {
		Assert.notNull(interceptors, "interceptor is null");
		Assert.notNull(ValidatorHolder, "validatorHolder is null");
		validatorHolderInterceptors.put(ValidatorHolder, interceptors);
		return this;
	}

	public ValidateHolder fristError() {
		List<ValidateHolder> holders = errors();

		return holders.size() > 0 ? holders.get(0) : null;
	}

	public List<ValidateHolder> errors() {
		List<ValidateHolder> holders = new ArrayList<ValidateHolder>();
		ValidateHolder tempValidatorHolder = null;
		for (ValidateHolder ValidatorHolder : validatorHolderInterceptors.keySet()) {

			Interceptor[] interceptors = validatorHolderInterceptors.get(ValidatorHolder);
			for (Interceptor interceptor : interceptors) {
				if (!Assert.isNull(tempValidatorHolder = interceptor.interceptor(ValidatorHolder))) {
					holders.add(tempValidatorHolder);
					break;														
				}
			}
		}
		return holders;
	}

}
