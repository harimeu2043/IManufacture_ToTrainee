package com.infy.utility;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger=LogManager.getLogger(this.getClass());

	@AfterThrowing(pointcut="execution(* com.infy.repository.*Impl.*(..))", throwing = "exception")
	public void logDaoException(Exception exception){
		logger.error(exception.getMessage(), exception);
	}
	
	@AfterThrowing(pointcut="execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception){
		logger.error(exception.getMessage(), exception);
		
	}


}
