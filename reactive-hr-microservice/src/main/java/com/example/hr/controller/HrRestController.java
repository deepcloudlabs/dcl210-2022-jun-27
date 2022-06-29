package com.example.hr.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.service.HrReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@CrossOrigin
@Validated
public class HrRestController {
	private final HrReactiveService hrService;
	
	public HrRestController(HrReactiveService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("{identity}")
	public Mono<EmployeeDocument> getEmployeeByIdentity(@PathVariable String identity){
		return hrService.getEmployeeById(identity);
	}

	@GetMapping(params = {"pageNo", "pageSize"})
	public Flux<EmployeeDocument> getEmployeesByPage(@RequestParam @Min(0) int pageNo,
			@RequestParam @Min(10) @Max(50) int pageSize){
		return hrService.getEmployees(pageNo,pageSize);
	}
	
	@PostMapping
	public Mono<EmployeeDocument> hireEmployee(@RequestBody @Validated EmployeeDocument document){
		return hrService.hireEmployee(document);
	}
	
	@DeleteMapping("{identity}")
	public Mono<EmployeeDocument> fireEmployee(@PathVariable String identity){
		return hrService.fireEmployee(identity);
	}

}
