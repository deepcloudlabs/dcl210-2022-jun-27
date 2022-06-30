package com.example.lottery.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.example.lottery.dto.LotteryModel;
import com.example.lottery.service.LotteryService;

@SuppressWarnings("serial")
public class StandardLotteryService 
extends UnicastRemoteObject
implements LotteryService {
    
	public StandardLotteryService() throws RemoteException {
	}

	@Override
	public LotteryModel draw(int max, int size) throws RemoteException {
		return new LotteryModel(ThreadLocalRandom.current()
		        .ints(1, max)
		        .distinct()
		        .limit(size)
		        .sorted()
		        .boxed()
		        .collect(Collectors.toList()));
	}

}
