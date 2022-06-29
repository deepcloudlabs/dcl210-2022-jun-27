package com.example.binance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import com.example.binance.dto.Ticker;

@Service
@SuppressWarnings("deprecation")
public class BinanceRestAsyncClientService {
	@Value("${binanceRestApiUrl}") // application.properties: binanceRestApiUrl=https:/api.binance.com/...
	private String binanceRestApiUrl;
	
	@Scheduled(fixedRate = 1_000)
	public void callBinanceRestApi() {
		var restTemplate = new AsyncRestTemplate(); // WebClient
		restTemplate.getForEntity(binanceRestApiUrl, Ticker.class)
		            .addCallback(System.out::println,System.err::println);		
	}
}
