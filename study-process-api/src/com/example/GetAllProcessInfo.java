package com.example;

public class GetAllProcessInfo {

	public static void main(String[] args) {
		ProcessHandle.allProcesses().forEach( ph -> System.out.println(ph.info()));
		System.out.println("# of processes: %d".formatted(ProcessHandle.allProcesses().count()));
	}

}
