package cmd_implement.cmd;

import FileSys.CurrentState;
import cmd_implement.Cmd;

import java.util.LinkedList;
import java.util.List;

public class CmdLs implements Cmd {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
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
