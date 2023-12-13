package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.exc.InvalidPathException;
import cmd_implement.Cmd;
import cmd_implement.Util;

public class CmdCd implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        Directory tmpCurrentDirectory = currentState.getCurrentDirectory();

        if (args.length == 0) {
            currentState.setCurrentDirectory(currentState.getRootDirectory());
            return currentState;
        }

        if (args[0].equals("/")) {
            currentState.setCurrentDirectory(currentState.getRootDirectory());
            return currentState;
        }

        if (args[0].equals("..")) {
            Directory parentDirectory = tmpCurrentDirectory.getParentDirectory();
            if (parentDirectory != null) {
                currentState.setCurrentDirectory(parentDirectory);
            }
            return currentState;
        }

        try {
            currentState.setCurrentDirectory(Util.resolveDirectoryPath(currentState, args[0]));
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

        return currentState;
    }
}
