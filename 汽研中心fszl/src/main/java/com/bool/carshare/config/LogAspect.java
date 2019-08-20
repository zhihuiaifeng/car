/**
 * 
 */
package com.bool.carshare.config;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bool.carshare.bean.ModuleType;
import com.bool.carshare.bean.OperationType;
import com.bool.carshare.bean.WebObject;
import com.bool.carshare.config.annotation.Log;
import com.bool.carshare.entity.LogInfo;
import com.bool.carshare.service.LogInfoService;

/**
 * 日志切面
 * @author wangw
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private LogInfoService logInfoService;
	
	@Pointcut("@annotation(com.bool.carshare.config.annotation.Log)")
	public void logPointcut() {
		
	}
	
	@Around("logPointcut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object result = null;
		
		try {
			result = joinPoint.proceed();
			
			this.saveLog(joinPoint);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void saveLog(ProceedingJoinPoint joinPoint) {
		Log annotation = getAnnotation(joinPoint);
		OperationType operation = annotation.operation();
		ModuleType module = annotation.module();
		WebObject webObject = getWebObject(joinPoint);
		
		LogInfo logInfo = new LogInfo();
		logInfo.setModuleName(module.toString());
		logInfo.setOperation(operation.toString());
		
		this.logInfoService.saveLogInfo(logInfo, webObject);
	}
	
	private static Log getAnnotation(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		Log annotation = method.getAnnotation(Log.class);
		
		return annotation;
	}
	
	private static WebObject getWebObject(ProceedingJoinPoint joinPoint) {
		WebObject webObject = null;
		
		Object[] args = joinPoint.getArgs();
		if(args != null) {
			for(int a=0; a<args.length; a++) {
				Object arg = args[a];
				
				if(arg instanceof WebObject) {
					webObject = (WebObject) arg;
					
					break;
				}
			}
		}
		
		return webObject;
	}

	public static Logger getLogger() {
		return logger;
	}
}