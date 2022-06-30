package com.example.lottery.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.example.lottery.dto.LotteryModel;

public interface LotteryService extends Remote {
	LotteryModel draw(int max, int size) throws RemoteException;
}
