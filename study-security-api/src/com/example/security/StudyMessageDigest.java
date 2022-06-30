package com.example.security;

import java.math.BigInteger;
import java.security.MessageDigest;

public class StudyMessageDigest {

	public static void main(String[] args) throws Exception {
		MessageDigest md= MessageDigest.getInstance("SHA1");
		md.reset();
		// e: 58e6b3a414a1e090dfc6029add0f3555ccba127f
		// f: 4a0a19218e082a343a1b17e5333409af9d98f0f5
		//    e1ae3f64e6b93823694059b8d5051d3ad1c05d35
		md.update("ffffffffffffe".getBytes());
		byte[] bytes= md.digest();
		BigInteger bi= new BigInteger(1,bytes);
		System.out.println(bi.toString(16));
	}

}
