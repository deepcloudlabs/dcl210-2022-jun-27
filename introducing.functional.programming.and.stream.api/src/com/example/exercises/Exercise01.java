package com.example.exercises;

import java.util.List;

public class Exercise01 {

	public static void main(String[] args) {
		List<Double> salaries = List.of(10_000.,15_000.,25_000.,17_500.,23_000.);
        var totalSalaries = 0.0;
        var numOfSalaries = 0;
        for (var salary : salaries) { // outer loop
        	if (salary > 15_000) {
        		numOfSalaries++;
        		totalSalaries += salary;
        	}
        }
        var averageSalary = totalSalaries / numOfSalaries;
        System.out.println("Average salary over 15_000: %f".formatted(averageSalary));
        
        averageSalary = 
                 salaries.stream() // internal loop + filter/map/reduce
                 		 .parallel()
                         .filter( salary -> salary > 15_000)
                         .mapToDouble(Double::doubleValue)
                         .average()
                         .orElse(Double.NaN);
	}

}
