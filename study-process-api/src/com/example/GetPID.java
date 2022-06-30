package com.example;

public class GetPID {

	public static void main(String[] args) {
		System.err.println("PID: " + ProcessHandle.current().pid());

	}

}
