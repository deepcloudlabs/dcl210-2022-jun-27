package com.example.exercises;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercise03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Functions in FP -> immutability
		// 1. Higher-order Function: filter/map/reduce
		// 2. Pure Function        : immutable
		//    i) lambda expression
		//   ii) method reference
		var numbers = List.of(4,8,15,16,23,42);
		// functional interface, build-in functional interface
		// single abstract method (sam), @FunctionalInterface
		int []state= {42};
		Predicate<Integer> ifEven = value -> value%2 == 0;
		//Function<Integer,Integer> toCube = n -> n*n*n* ++state[0] ;
		Function<Integer,Integer> toCube = n -> n*n*n ;
		BinaryOperator<Integer> sum = Integer::sum;
		numbers.stream().peek(System.out::println)
		                .filter( ifEven ) // intermediary functions
		                .peek(System.out::println)
		                .map( toCube ) // intermediary functions
		                .peek(System.out::println)
		                .reduce( 0, sum ); // terminal methods: sum, average, count, collect, min, max, ...
		
	}

}

record UserRecord(String firstName, String lastName) {} // immutable class

final class User {
	private final String firstName;
	private final String lastName;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String firstName() {
		return firstName;
	}

	public String lastName() {
		return lastName;
	}

		@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
