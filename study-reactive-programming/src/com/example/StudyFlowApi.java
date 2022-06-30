package com.example;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class StudyFlowApi {

	public static void main(String[] args) {
		System.err.println("Application has just started.");
		try(
				SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>()
				){
			var trades = List.of(
					new TradeEvent("orcl", 100, 100),
					new TradeEvent("ibm", 200, 100),
					new TradeEvent("msft", 300, 100),
					new TradeEvent("redhat", 400, 100),
					new TradeEvent("orcl", 500, 100)
					);		
			publisher.subscribe(new SlowSubscriber());
			publisher.subscribe(new FastSubscriber());
			trades.forEach(publisher::submit);
			System.err.println("Application has just completed.");
			
			try {TimeUnit.SECONDS.sleep(30);}catch (Exception e) {}			
		}

	}

}

class SlowSubscriber implements Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SlowSubscriber has just subscribed!");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
		System.err.println("SlowSubscriber has processed the event (%s)".formatted(event));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		System.err.println("SlowSubscriber has an exception: %s".formatted(t.getMessage()));
		
	}

	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has just completed!");		
	}
	
}

class FastSubscriber implements Subscriber<TradeEvent> {
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("FastSubscriber has just subscribed!");
		this.subscription = subscription;
		this.subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("FastSubscriber has processed the event (%s)".formatted(event));
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable t) {
		System.err.println("FastSubscriber has an exception: %s".formatted(t.getMessage()));
		
	}
	
	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has just completed!");		
	}
	
}
