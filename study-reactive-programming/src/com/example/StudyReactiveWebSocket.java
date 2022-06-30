package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class StudyReactiveWebSocket {

	public static void main(String[] args) throws InterruptedException {
		Listener listener = new BinanceWebsocketListener();
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create("wss://stream.binance.com:9443/ws/btcusdt@trade"), listener );
		TimeUnit.SECONDS.sleep(30);
	}

}


class BinanceWebsocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the binance server.");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Connected from the binance server.");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occurred: %s".formatted(error.getMessage()));
		Listener.super.onError(webSocket, error);
	}
	
}