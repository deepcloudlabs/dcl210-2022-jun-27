package com.example.lottery.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

public class AuditingHandler implements InvocationHandler {

	private final Object target;

	public AuditingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var methodName = method.getName();
		System.err.println("%s is called at %s.".formatted(methodName,new Date()));
		System.err.println("%s has the following parameters: %s.".formatted(methodName, Arrays.toString(args) ));
		var result = method.invoke(target, args);
		return result;
	}

}
