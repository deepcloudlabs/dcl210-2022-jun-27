package com.example;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class MostCpuConsumingProcess {

	public static void main(String[] args) {
		DateTimeFormatter formatter
        = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")
        .withZone(ZoneId.systemDefault());
Consumer<String> printUser = user -> {
    System.out.println("User: " + user);
};
Consumer<String> printCmd = cmd -> {
    System.out.println("Command: " + cmd);
};
Consumer<String> printCmdLine = cmdln -> {
    System.out.println("Command line: " + cmdln);
};
Consumer<String[]> printArgs = arguments -> {
    System.out.println("Arguments: " + Arrays.toString(arguments));
};
Consumer<Instant> printInstant = inst -> {
    System.out.println("Start time: " + formatter.format(inst));
};
Consumer<Duration> printCpu = duration -> {
    System.out.println("CPU time (millisec): " + duration.toMillis());
};
Consumer<ProcessHandle.Info> printInfo
        = info -> {
            info.user().ifPresent(printUser);
            info.command().ifPresent(printCmd);
            info.commandLine().ifPresent(printCmdLine);
            info.arguments().ifPresent(printArgs);
            info.startInstant().ifPresent(printInstant);
            info.totalCpuDuration().ifPresent(printCpu);
            System.out.println();
        };
Optional<ProcessHandle.Info> processInfo
        = ProcessHandle.allProcesses()
        .map(p -> p.info())
        .filter(info -> info.totalCpuDuration().isPresent())
        .max((p, q) -> p.totalCpuDuration().orElse(Duration.ZERO)
            .compareTo(q.totalCpuDuration().orElse(Duration.ZERO)));
processInfo.ifPresent(printInfo);
	}

}
