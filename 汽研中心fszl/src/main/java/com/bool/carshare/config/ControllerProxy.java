package com.bool.carshare.config;

import static com.bool.carshare.util.Message.ERROR;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.bool.carshare.util.Result;
/**
 * controllerProxy -> create date 2017/6/13
 * 
 * @author tzw
 *
 */
@Configuration
@Aspect
public class ControllerProxy {

	private static final Logger log = Logger.getLogger(ControllerProxy.class);

	// 
	@Pointcut("execution(* com.bool.carshare.resource.*Resource.*(..))")
	public void excudeService() {
	}

	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		try {
			Object obj = pjp.proceed();
			return obj;
		} catch (Exception e) {
			log.error("#------------------------------------------#");
			log.error("execute method params:"+pjp.getArgs());
			log.error("execute method name:"+pjp.getSignature().getName());
			log.error("exception:"+e.getMessage());
			log.error("exception class:"+e.getClass().getName());
			log.error("#------------------------------------------#");
			return Result.ResultBuilder.buildFailerResult(ERROR, null);
		}
	}

}
