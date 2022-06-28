package com.example.lottery.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.aspect.Audited;
import com.example.lottery.aspect.Profiled;
import com.example.lottery.service.LotteryService;

// Declarative/Descriptive Programming
@RestController
@RequestScope
@RequestMapping("/numbers")
@CrossOrigin
@Validated
@Audited
public class LotteryController {

	private final LotteryService lotteryService;
	
	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
		System.err.println(lotteryService.getClass().getName());
	}

	@GetMapping(params="column")
	@Profiled
	public List<List<Integer>> getLotteryNumbers(@RequestParam @Min(3) @Max(25) int column){
		return lotteryService.draw(60,6,column);
	}
}
