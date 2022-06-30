package com.example.lottery.application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.example.lottery.service.business.StandardLotteryService;

public class LotteryAppServer {
	// JDK = JRE + Monitoring Tools + Development Tools
	// rmiregistry -> JDK/bin, not in JRE
    // rmiregistry 8800
	public static void main(String[] args) throws RemoteException {
		var lotteryService = new StandardLotteryService();
		var registry = LocateRegistry.getRegistry(8800);
        registry.rebind("LotteryService", lotteryService);
        System.err.println("Lottery RMI Service is running.");
	}

}
