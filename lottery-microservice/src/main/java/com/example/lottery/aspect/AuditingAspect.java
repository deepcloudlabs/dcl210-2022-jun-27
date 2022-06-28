package com.example.lottery.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditingAspect {

	@Around("""
			  examplePackage() &&  
			     ( 
			       methodIsAuditedAnnotated() || 
			       classIsAuditedAnnotated() 
			     )
			""")
	public Object methodAudited(ProceedingJoinPoint pjp) throws Throwable {
		var methodName = pjp.getSignature().getName();
		System.err.println("%s is called at %s.".formatted(methodName,new Date()));
		System.err.println("%s has the following parameters: %s.".formatted(methodName, Arrays.toString(pjp.getArgs()) ));
		var result = pjp.proceed();
		return result;
	}
	
	@Pointcut("@annotation(com.example.lottery.aspect.Audited)")
	public void methodIsAuditedAnnotated() {}
	
	@Pointcut("within(@com.example.lottery.aspect.Audited *)")
	public void classIsAuditedAnnotated() {}

	@Pointcut("execution(* com.example..*(..))")
	public void examplePackage() {}
	
}
