package cmd_implement.cmd;

import FileSys.CurrentState;
import cmd_implement.Cmd;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmdLs implements Cmd {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        List<String> directories = currentState.getCurrentDirectory().getDirectories().values().stream()
                                               .map(dir -> formatDirectoryName(dir))
                                               .collect(Collectors.toList());

        List<String> files = currentState.getCurrentDirectory().getFiles().values().stream()
                                         .map(file -> formatFileName(file))
                                         .collect(Collectors.toList());

        List<String> sortedOutput = Stream.concat(directories.stream(), files.stream())
                                          .sorted()
                                          .collect(Collectors.toList());

        sortedOutput.forEach(this::printFormattedName);

        return currentState;
    }

    private String formatDirectoryName(Object dir) {
        return dir.toString() + "0";
    }

    private String formatFileName(Object file) {
        return file.toString() + "1";
    }

    private void printFormattedName(String name) {
        if (name.endsWith("0")) {
            System.out.println(ANSI_PURPLE + name.substring(0, name.length() - 1) + ANSI_RESET);
        } else if (name.endsWith("1")) {
            System.out.println(name.substring(0, name.length() - 1));
        }
    }
}
