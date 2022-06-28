package com.example.random.service.business;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.random.service.RandomNumberGenerator;

//@ServiceQuality(QualityLevel.CHEAP)
@Service
@ConditionalOnProperty(name="generator",havingValue = "CHEAP")
public class CheapRandomNumberGenerator implements RandomNumberGenerator {
	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("CheapRandomNumberGenerator::generate");
		return random.nextInt(min, max);
	}

}
