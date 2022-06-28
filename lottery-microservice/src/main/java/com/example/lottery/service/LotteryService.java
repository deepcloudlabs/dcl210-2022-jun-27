package com.example.lottery.service;

import java.util.List;
import java.util.stream.IntStream;

import com.example.lottery.aspect.Audited;
import com.example.lottery.aspect.Profiled;

@Profiled
public interface LotteryService {
	List<Integer> draw(int max, int size);

	@Audited
	default List<List<Integer>> draw(int max, int size, int column){
		return IntStream.range(0, column)
				        .mapToObj(i -> draw(max,size))
				        .toList();
	}
}
