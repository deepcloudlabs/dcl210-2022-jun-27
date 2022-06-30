package com.example;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class StudyLegacyObserver {

	public static void main(String[] args) {
		System.err.println("Application has just started.");
		var trades = List.of(
				new TradeEvent("orcl", 100, 100),
				new TradeEvent("ibm", 200, 100),
				new TradeEvent("msft", 300, 100),
				new TradeEvent("redhat", 400, 100),
				new TradeEvent("orcl", 500, 100)
		);
		
		var observable = new TradeEventObservable();
		Observer slowObserver = (o,event) -> {
			try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
			System.err.println("Slow observer has processed the event (%s)".formatted(event));
		};
		Observer fastObserver = (o,event) -> {
			System.err.println("Fast observer has processed the event (%s)".formatted(event));
		};
		
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		
		trades.forEach(observable::notifyObservers);

		System.err.println("Application has just completed.");
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}