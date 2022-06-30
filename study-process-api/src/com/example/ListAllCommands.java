package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class ListAllCommands {
	public static void main(String[] args) {
		Consumer<String> printCommand = command -> {
			Path path = Paths.get(command);
			System.out.println(path.getFileName());
		};
		ProcessHandle.allProcesses()
		             .filter(p -> p.info().command().isPresent())
		             .map(p -> p.info().command().get())
				     .distinct()
				     .sorted()
				     .forEach(printCommand);
	}
}
