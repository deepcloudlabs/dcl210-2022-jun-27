package com.example.hr.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.respoitory.EmployeeDocumentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HrReactiveService {
	private final EmployeeDocumentRepository employeeRepository;
	
	public HrReactiveService(EmployeeDocumentRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Mono<EmployeeDocument> getEmployeeById(String identity) {
		return employeeRepository.findById(identity);
	}

	public Flux<EmployeeDocument> getEmployees(int pageNo,int pageSize) {
		return employeeRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<EmployeeDocument> hireEmployee(EmployeeDocument document) {
		return employeeRepository.insert(document);
	}

	public Mono<EmployeeDocument> fireEmployee(String identity) {
		return employeeRepository.findById(identity)
		                  .doOnNext( employee -> {
		                	  employeeRepository.delete(employee).subscribe();
		                  });
	}

}
