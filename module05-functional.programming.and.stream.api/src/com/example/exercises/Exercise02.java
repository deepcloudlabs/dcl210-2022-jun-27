package com.example.exercises;

import java.math.BigDecimal;
import java.util.List;

public class Exercise02 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		double x = 3.1415; // 8-byte -> Stack
		// 24-byte + 8-byte = 32-byte
		Double y = 3.1415; // Object header: 12-Byte
		// y: Stack -> Heap Object // 4-Byte (padding)
		// double value : 8-Byte
		// List<double> salaries; // 1M -> 8MB vs. 1M -> 32M
		List<Double> salaries; // JLS

		double m = 3.14;
		double n = 6.78;
		double u = m + n;
		// Double u = Double.valueOf(m.doubleValue() + n.doubleValue());
		double z = 2.0 - 1.1;
		System.out.println("%16.16f".formatted(z));
		if (((0.1 + 0.2) + 0.3) == (0.1 + (0.2 + 0.3)))
			System.out.println("They are equal!");
		else
			System.out.println("They are NOT equal!");
		BigDecimal bd = BigDecimal.valueOf(2).subtract(BigDecimal.valueOf(1.1));
		System.out.println("%s".formatted(bd.toString()));
		float money = 1_000_000_000;
		money = money + 50;
		System.out.println("%16.16f".formatted(money));
		
	}

}

class A {
	double x; // instance variable - Heap (8B)
	Double y = 3.14; // instance variable - Heap (8B + 24B)
}
