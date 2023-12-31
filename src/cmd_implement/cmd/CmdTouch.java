package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.File;
import FileSys.Path;
import cmd_implement.Cmd;

public class CmdTouch implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: touch <file_name>");
            return currentState;
        }

        String fileName = args[0];
        Directory currentDirectory = currentState.getCurrentDirectory();
        Path filePath = new Path(currentDirectory.getPathNotString(), fileName);

        if (currentDirectory.getFiles().containsKey(fileName)) {
            System.out.println("File already exists: " + fileName);
        } else {
            File newFile = new File(filePath);
            currentDirectory.getFiles().put(fileName, newFile);
        }

        return currentState;
    }
}
