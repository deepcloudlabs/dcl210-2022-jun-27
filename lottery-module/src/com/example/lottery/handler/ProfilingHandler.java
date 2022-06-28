package com.example.lottery.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProfilingHandler implements InvocationHandler {

	private final Object target;

	public ProfilingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var start = System.currentTimeMillis();
		var methodName = method.getName();
		var result = method.invoke(target, args);
		var stop = System.currentTimeMillis();
		System.err.println("%s runs %d ms.".formatted(methodName,stop-start));		
		return result;
	}

}
