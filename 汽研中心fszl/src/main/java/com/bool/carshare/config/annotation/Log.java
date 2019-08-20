/**
 * 
 */
package com.bool.carshare.config.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * 日志
 * @author wangw
 */
public @interface Log {
	/**
	 * 操作类型
	 * @return
	 */
	OperationType operation();
	
	/**
	 * 模块类型
	 * @return
	 */
	ModuleType module();
}