package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.lottery.handler.AuditingHandler;
import com.example.lottery.handler.ProfilingHandler;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberGenerator;
import com.example.random.service.ServiceQuality;

public class LotteryApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File("src","application.properties")));
		var qualityLevel = QualityLevel.valueOf(props.getProperty("random.number.service"));
		var lotteryService = new StandardLotteryService();
		var lotteryClazz = lotteryService.getClass();
	
		var auditingHandler = new AuditingHandler(lotteryService);
		var proxy =	(LotteryService)Proxy.newProxyInstance(
				lotteryClazz.getClassLoader(), 
				lotteryClazz.getInterfaces(), 
				auditingHandler);
		var profilingHandler = new ProfilingHandler(proxy);
		var proxy2 =	(LotteryService)Proxy.newProxyInstance(
				lotteryClazz.getClassLoader(), 
				lotteryClazz.getInterfaces(), 
				profilingHandler);
		
		ServiceLoader<RandomNumberGenerator> services = 
				     ServiceLoader.load(RandomNumberGenerator.class);
		for (var service : services) {
			var clazz = service.getClass();
			if (clazz.isAnnotationPresent(ServiceQuality.class)) {
				var serviceQuality = clazz.getAnnotation(ServiceQuality.class);
				if (serviceQuality.value() == qualityLevel) {
					lotteryService.setRandomNumberGenerator(service);
					break;
				}
			}
		}
		System.err.println(proxy.getClass());
		System.err.println(proxy2.getClass());
		proxy2.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
