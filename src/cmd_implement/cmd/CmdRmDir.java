package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.Cmd;

public class CmdRmDir implements Cmd {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: rmdir <directory_name>");
            return currentState;
        }

        String directoryName = args[0];
        Directory currentDirectory = currentState.getCurrentDirectory();

        if (currentDirectory.getDirectories().containsKey(directoryName)) {
            Directory directoryToRemove = currentDirectory.getDirectories().get(directoryName);

            if (directoryToRemove != currentDirectory.getParentDirectory()) {
                currentDirectory.getDirectories().remove(directoryName);
            } else {
                System.out.println("Cannot remove the parent directory.");
            }
        } else {
            System.out.println("Directory does not exist: " + directoryName);
        }

        return currentState;
    }
}
