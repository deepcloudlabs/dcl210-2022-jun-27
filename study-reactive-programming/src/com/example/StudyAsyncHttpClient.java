package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class StudyAsyncHttpClient {
	private static AtomicInteger count = new AtomicInteger(0);
	private static int counter[] = {0} ;
	private static int sayac = 0 ;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT")).build();
		long start = System.currentTimeMillis();
		for (var i=0;i<50;++i) {
			httpClient.sendAsync(request, BodyHandlers.ofString())
			          .thenAcceptAsync(response -> {
			        	  System.out.println("[%s] %s".formatted(Thread.currentThread().getName(),response.body()));
			        	  synchronized(int.class) {
			        		  if (++counter[0] == 50) {
			        			  long stop = System.currentTimeMillis();
			        			  System.out.println("[%s] Duration: %d ms.".formatted(Thread.currentThread().getName(),(stop-start)));			        		  			        			  
			        		  }
			        		  sayac++;
			        	  }
			        	  if (count.incrementAndGet() == 50) {
			        		  long stop = System.currentTimeMillis();
			        		  System.out.println("[%s] Duration: %d ms.".formatted(Thread.currentThread().getName(),(stop-start)));			        		  
			        	  }
			             }
			          );	
		}
		
		TimeUnit.SECONDS.sleep(10);
	}

}
