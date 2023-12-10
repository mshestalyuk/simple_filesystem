package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.CommandLineCommand;
import cmd_implement.Util;
import cmd_implement.exc.InvalidPathException;

public class CommandCd implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        Directory tmpCurrentDirectory = currentState.getCurrentDirectory();

        if (args.length == 0) {
            // If no arguments provided, return to the root directory
            currentState.setCurrentDirectory(currentState.getRootDirectory());
            return currentState;
        }

        if (args[0].equals("/")) {
            // If the argument is "/", set the current directory to the root directory
            currentState.setCurrentDirectory(currentState.getRootDirectory());
            return currentState;
        }

        if (args[0].equals("..")) {
            // If the argument is "..", navigate to the parent directory
            Directory parentDirectory = tmpCurrentDirectory.getParentDirectory();
            if (parentDirectory != null) {
                currentState.setCurrentDirectory(parentDirectory);
            }
            return currentState;
        }

        try {
            // Otherwise, attempt to set the current directory based on the provided path
            currentState.setCurrentDirectory(Util.getEndOfThePathDirectory(currentState, args[0]));
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

        return currentState;
    }
}