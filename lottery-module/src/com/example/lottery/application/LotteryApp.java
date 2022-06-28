package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

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
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
