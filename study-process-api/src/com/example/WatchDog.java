package com.example;

import java.util.concurrent.CompletableFuture;

public class WatchDog {

	public static void main(String[] args) throws Exception {
		String cmd = "mspaint";
        do {
            Process process = Runtime.getRuntime().exec(cmd);
            ProcessHandle ph = process.toHandle();
            CompletableFuture<ProcessHandle> onExit = ph.onExit();
            onExit.get();
            System.err.println("Exit value: "+process.exitValue());
        } while (true);
	}

}
