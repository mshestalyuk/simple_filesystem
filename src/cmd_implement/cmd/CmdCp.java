package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.File;
import FileSys.Path;
import cmd_implement.Cmd;
import cmd_implement.Util;
import cmd_implement.exc.DoesNotExistException;
import cmd_implement.exc.InvalidPathException;
import cmd_implement.exc.NameExistsException;

public class CmdCp implements Cmd {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        // Check for sufficient arguments
        if (args.length < 2) {
            System.out.println("Insufficient arguments for cp command");
            return currentState;
        }

        try {
            Directory[] fromAndTo = Util.getFromAndToDirectories(currentState, args);
            String targetFileName = args[1].substring(Util.getNameStartPosition(args[1]));
            copy(args[0].substring(Util.getNameStartPosition(args[0])), fromAndTo[0], fromAndTo[1], targetFileName);
        } catch (InvalidPathException | NameExistsException e) {
            System.out.println(e.getMessage());
        }
        return currentState;
    }

    private void copy(String name, Directory from, Directory to, String targetFileName) {
        // Check if the source is a file
        if (from.getFiles().containsKey(name)) {
            // Handle file copying
            File original = from.getFiles().get(name);
    
            // Create or update the file in the target directory
            File copy = to.getFiles().getOrDefault(targetFileName, new File(new Path(to.getPathNotString(), targetFileName)));
            copy.setContent(original.getContent());
            to.getFiles().put(targetFileName, copy);
        } else if (from.getDirectories().containsKey(name)) {
            // Handle directory copying
            Directory newDirectory = new Directory(new Path(to.getPathNotString(), name), to);
            to.getDirectories().put(name, newDirectory);
        } else {
            throw new DoesNotExistException("No such file or directory :" + name);
        }
    }
}

