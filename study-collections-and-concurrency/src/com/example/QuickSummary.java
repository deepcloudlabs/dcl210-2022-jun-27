package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class QuickSummary {

	public static void main(String[] args) {
		// List -> Value, Ordered, Duplicate
		List<Integer> numbers= new ArrayList<>();
		numbers.add(4);
		numbers.add(4);
		numbers.add(4);
		numbers.add(4);
		numbers.add(4);
		System.out.println(numbers);
		System.out.println(numbers.size());
		numbers.add(3,8);
		numbers.sort(Integer::compareTo);
		System.out.println(numbers.contains(42));
		// Set  -> Value: Unordered, Unique
		Set<Integer> sayilar= new TreeSet<>((x,y)->y-x);
		sayilar.add(4);
		sayilar.add(8);
		sayilar.add(15);
		sayilar.add(42);
		sayilar.add(16);
		sayilar.add(23);
		System.out.println(sayilar);
		System.out.println(sayilar.size());
		
		
		// Map  -> (Key -> Value)
		var areaCodes = Map.of(
				   "ankara"			 , 312,
				   "istanbul"        , 216,
				   "istanbul-avrupa" , 212
				);	
		System.out.println(areaCodes.containsKey("ankara"));
		System.out.println(areaCodes.get("ankara"));
		System.out.println(areaCodes.get("ancara")); // null
		System.out.println(areaCodes.getOrDefault("ancara",0));
		var alanKodlari = new HashMap<String,Integer>();
		alanKodlari.put("istanbul", 212);
		alanKodlari.put("istanbul", 216);
		System.out.println(alanKodlari); // Multi-Map yok!
	}

}
