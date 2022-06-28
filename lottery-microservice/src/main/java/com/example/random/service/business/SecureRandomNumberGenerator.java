package com.example.random.service.business;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberGenerator;
import com.example.random.service.ServiceQuality;

//@ServiceQuality(QualityLevel.SECURE)
@Service
@ConditionalOnProperty(name="generator",havingValue = "SECURE")
public class SecureRandomNumberGenerator implements RandomNumberGenerator {

	private Random random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberGenerator::generate");
		return random.nextInt(min,max);
	}

}
