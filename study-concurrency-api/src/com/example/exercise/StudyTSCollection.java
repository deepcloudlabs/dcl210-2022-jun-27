package com.example.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StudyTSCollection {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		// 1. Collections
		numbers = Collections.synchronizedList(numbers);
		// 2. CopyOnWriteArrayList
		numbers = new CopyOnWriteArrayList<>();
		// 3. Multi-core Ready TS Collection
		// Warning: Collections.synchronizedMap(map)
		Map<String,Integer> map = new ConcurrentHashMap<>(200_000,1);
	}

}
