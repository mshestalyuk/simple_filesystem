package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.File;
import FileSys.Path;
import FileSys.exc.DoNotExistException;
import FileSys.exc.InsufficentArguments;
import FileSys.exc.InvalidPathException;
import FileSys.exc.NameAlreadyExistsException;
import cmd_implement.Cmd;
import cmd_implement.Util;

public class CmdCp implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {

        if (args.length < 2) {
            throw new InsufficentArguments("Insufficient parameters: source and destination paths required. Num of Args: " + args.length);
        }
        
    
        try {
            Directory[] fromAndTo = Util.getFromAndToDirectories(currentState, args);
            String targetFileName = args[1].substring(Util.getNameStartPosition(args[1]));
            copy(args[0].substring(Util.getNameStartPosition(args[0])), fromAndTo[0], fromAndTo[1], targetFileName);
        } catch (InvalidPathException | NameAlreadyExistsException | InsufficentArguments e) {
            System.out.println(e.getMessage());
        }
        return currentState;
    }

    private void copy(String name, Directory from, Directory to, String targetFileName) {
        if (from.getFiles().containsKey(name)) {
            File original = from.getFiles().get(name);
    
            File copy = to.getFiles().getOrDefault(targetFileName, new File(new Path(to.getPathNotString(), targetFileName)));
            copy.setContent(original.getContent());
            to.getFiles().put(targetFileName, copy);
        } else if (from.getDirectories().containsKey(name)) {
            Directory newDirectory = new Directory(new Path(to.getPathNotString(), name), to);
            to.getDirectories().put(name, newDirectory);
        } else {
            throw new DoNotExistException("No such file or directory :" + name);
        }
    }
}

