/**
 * 
 */
package com.bool.carshare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bool.carshare.interceptor.LoginInterceptor;

/**
 * WebMVCConfig
 * @author wangw
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.loginInterceptor);
		
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//外部访问路径映射到本地磁盘路径
		registry.addResourceHandler("/carshare_res/upload/images/**").addResourceLocations("file:C:\\File\\upload\\images\\");
	}
}