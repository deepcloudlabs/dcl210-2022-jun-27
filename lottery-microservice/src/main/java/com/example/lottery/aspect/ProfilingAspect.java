package com.example.lottery.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {

	@Around("""
			  examplePackage() &&  
			     ( 
			       methodIsProfiledAnnotated() || 
			       classIsProfiledAnnotated() 
			     )
			""")
			public Object profile(ProceedingJoinPoint pjp) throws Throwable {
				var start = System.nanoTime();
				var result = pjp.proceed();
				var stop = System.nanoTime();
				var duration = stop - start;
				System.out.println(String.format("%s runs %d ns.", pjp.getSignature().getName(), duration));
				return result; 
			}
			
	@Pointcut("@annotation(com.example.lottery.aspect.Profiled)")
	public void methodIsProfiledAnnotated() {}
	
	@Pointcut("within(@com.example.lottery.aspect.Profiled *)")
	public void classIsProfiledAnnotated() {}

	@Pointcut("execution(* com.example..*(..))")
	public void examplePackage() {}

}
