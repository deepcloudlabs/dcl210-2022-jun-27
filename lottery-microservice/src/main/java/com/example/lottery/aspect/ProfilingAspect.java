package com.example.lottery.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {

	@Around("execution (* com.example..*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		var start = System.currentTimeMillis();
		var methodName = pjp.getSignature().getName();
		var result = pjp.proceed();
		var stop = System.currentTimeMillis();
		System.err.println("%s runs %d ms.".formatted(methodName,stop-start));		
		return result;
	}

}
