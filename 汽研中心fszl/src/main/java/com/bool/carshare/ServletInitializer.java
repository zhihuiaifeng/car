
package com.bool.carshare;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * ServletInitializer
 * @author wangw
 */
public class ServletInitializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		
		return builder.sources(Application.class);
//		return super.configure(builder);
	}
}