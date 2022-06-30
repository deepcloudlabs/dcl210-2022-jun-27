package com.example.lottery.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class LotteryModel implements Serializable {
	private static final long serialVersionUID = 5L;
	private List<Integer> numbers;
	private int x=42;

	public LotteryModel() {
	}

	public LotteryModel(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotteryModel [numbers=" + numbers + "]";
	}

}
