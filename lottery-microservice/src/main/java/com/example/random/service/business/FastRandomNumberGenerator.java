package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberGenerator;
import com.example.random.service.ServiceQuality;

//@ServiceQuality(QualityLevel.FAST)
@Service
@ConditionalOnProperty(name="generator",havingValue = "FAST")
public class FastRandomNumberGenerator implements RandomNumberGenerator {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberGenerator::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
