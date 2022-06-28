package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomNumberGenerator;

@Service
public class StandardLotteryService implements LotteryService {
	private RandomNumberGenerator randomNumberGenerator;
	
	@Autowired
	//@ServiceQuality(QualityLevel.CHEAP)
	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate( () -> randomNumberGenerator.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .toList();
	}

}
