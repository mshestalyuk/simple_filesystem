package cmd_implement.cmd;

import FileSys.CurrentState;
import cmd_implement.CommandLineCommand;

import java.util.LinkedList;
import java.util.List;

public class CommandLs implements CommandLineCommand {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        List<String> outputList = new LinkedList<>();
        outputList.addAll(
                currentState.getCurrentDirectory().getDirectories().values()
                        .stream()
                        .map(dir -> dir.toString() + "0")
                        .toList());
        outputList.addAll(
                currentState.getCurrentDirectory().getFiles().values()
                        .stream()
                        .map(file -> file.toString() + "1")
                        .toList());
        outputList.sort(null);
        outputList.forEach(entity -> {
            if (entity.charAt(entity.length() - 1) == '0') {
                System.out.println(ANSI_PURPLE + entity.substring(0, entity.length() - 1) + ANSI_RESET);
            } else if (entity.charAt(entity.length() - 1) == '1') {
                System.out.println(entity.substring(0, entity.length() - 1));
            }
        });
        return currentState;
    }
}
