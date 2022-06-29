package com.example.hr.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReactiveHrClientService {
	private final WebClient webClient = 
			WebClient.builder().baseUrl("http://localhost:7200/employees").build();
	
	@Scheduled(fixedRate = 3_000)
	public void callRestApi() {
		List.of("1", "2", "3", "4")
		    .forEach(identity ->
				webClient.get()
				         .uri(uriBuilder -> uriBuilder.path("/".concat(identity))
				        		                      .build())
				         .retrieve()
				         .bodyToMono(String.class)
				         .subscribe(System.err::println)
		);
	}
}
