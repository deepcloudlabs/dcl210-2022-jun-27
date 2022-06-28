package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.lottery.aspect.Audited;
import com.example.lottery.aspect.Profiled;
import com.example.random.service.RandomNumberGenerator;

//@ServiceQuality(QualityLevel.FAST)
@Service
@ConditionalOnProperty(name="generator",havingValue = "FAST")
public class FastRandomNumberGenerator implements RandomNumberGenerator {

	@Override
	@Audited
	@Profiled(TimeUnit.NANOSECONDS)
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberGenerator::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
