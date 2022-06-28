package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberGenerator;

public class LotteryApp {

	public static void main(String[] args) {
		var lotteryService = new StandardLotteryService();
		ServiceLoader<RandomNumberGenerator> services = 
				     ServiceLoader.load(RandomNumberGenerator.class);
		lotteryService.setRandomNumberGenerator(services.findFirst().get());
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
