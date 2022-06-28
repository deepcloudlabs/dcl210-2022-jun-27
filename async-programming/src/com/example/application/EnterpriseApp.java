package com.example.application;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import com.example.service.BusinessService;

public class EnterpriseApp {

	public static void main(String[] args) {
		System.err.println("Application is just started!");
		var businessService = new BusinessService();
		AtomicBoolean isResultReady = new AtomicBoolean(false);
		businessService.doAsyncBusiness()
		               .thenAcceptAsync( result -> {
		            	      System.err.println(result);
		            	      isResultReady.set(true);
		            	   } );
		IntStream.range(1, 100)
		         .takeWhile( i -> {
		        	 return !isResultReady.get();
		         }).forEach( i -> {
		        	 try { TimeUnit.MILLISECONDS.sleep(80); }catch (Exception e) {}
		        	 System.err.println("Working hard... %d".formatted(i));		        	 
		         });
		System.err.println("Application is just ended!");
	}

}
