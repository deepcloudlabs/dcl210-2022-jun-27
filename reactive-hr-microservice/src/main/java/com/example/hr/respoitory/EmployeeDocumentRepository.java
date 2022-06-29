package com.example.hr.respoitory;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.hr.document.EmployeeDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeDocumentRepository extends ReactiveMongoRepository<EmployeeDocument, String>{
	Flux<EmployeeDocument> findAllByBirthYearBetween(int fromYear,int toYear);
	Mono<EmployeeDocument> findOneByIban(String iban);
	@Query("{iban: ?1}")
	Mono<EmployeeDocument> getir(String iban);
	@Query("{}")
	Flux<EmployeeDocument> findAll(PageRequest page);
}
