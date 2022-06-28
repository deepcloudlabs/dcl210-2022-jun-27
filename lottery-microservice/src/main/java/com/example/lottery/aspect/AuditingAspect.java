package com.example.lottery.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditingAspect  {

	@Around("execution (* com.example..*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		var methodName = pjp.getSignature().getName();
		System.err.println("%s is called at %s.".formatted(methodName,new Date()));
		System.err.println("%s has the following parameters: %s.".formatted(methodName, Arrays.toString(pjp.getArgs()) ));
		var result = pjp.proceed();
		return result;
	}

}
