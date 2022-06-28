package com.example.random.service.business;

import java.util.Random;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberGenerator;
import com.example.random.service.ServiceQuality;

@ServiceQuality(QualityLevel.CHEAP)
public class CheapRandomNumberGenerator implements RandomNumberGenerator {
	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("CheapRandomNumberGenerator::generate");
		return random.nextInt(min, max);
	}

}
