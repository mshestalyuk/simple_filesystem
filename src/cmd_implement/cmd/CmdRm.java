package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.CommandLineCommand;

public class CmdRm implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: rm <file_name>");
            return currentState;
        }

        String fileName = args[0];
        Directory currentDirectory = currentState.getCurrentDirectory();

        if (currentDirectory.getFiles().containsKey(fileName)) {
            currentDirectory.getFiles().remove(fileName);
        } else {
            System.out.println("File does not exist: " + fileName);
        }

        return currentState;
    }
}
