package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BusinessService {
	public int doBusiness() {
		try { TimeUnit.SECONDS.sleep(5); }catch (Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer>  doAsyncBusiness() {
		return CompletableFuture.supplyAsync(() -> {
			try { TimeUnit.SECONDS.sleep(5); }catch (Exception e) {}
			return 42;		
		}, Executors.newCachedThreadPool());
	}
}
