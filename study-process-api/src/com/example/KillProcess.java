package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class KillProcess {

	public static void main(String[] args) {
		if (args.length==0){
            System.out.println("Usage: ");
            System.out.println("ProcessKill <list of process names>");
            System.out.println("Example: \n\tProcessKill calc.exe mspaint.exe");
        }
        Predicate<ProcessHandle> processCriteria
                = p -> {
                    Optional<String> cmd = p.info().command();
                    if (cmd.isPresent()) {
                        Path path = Paths.get(cmd.get());
                        System.out.println(path.getFileName());
                        return Arrays.stream(args)
                               .anyMatch( arg -> path.getFileName().toString().equals(arg) );
                    }
                    return false;
                };
        ProcessHandle.allProcesses()
                .filter(p -> p.isAlive())
                .filter(processCriteria)
                .forEach(ProcessHandle::destroyForcibly);
	}

}
