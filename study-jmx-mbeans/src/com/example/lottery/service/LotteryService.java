package com.example.lottery.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService
public interface LotteryService {
	@WebMethod(operationName = "cekilis")
	List<Integer> draw(@XmlElement(name = "max") int max,@XmlElement(name = "size") int size);
}
