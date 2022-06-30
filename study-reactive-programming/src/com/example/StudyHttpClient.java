package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class StudyHttpClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT")).build();
		long start = System.currentTimeMillis();
		for (var i=0;i<10;++i) {
			var response = httpClient.send(request, BodyHandlers.ofString()).body();
			System.out.println(response);			
		}
		long stop = System.currentTimeMillis();
		System.out.println("Duration: %d ms.".formatted((stop-start)));
	}

}
