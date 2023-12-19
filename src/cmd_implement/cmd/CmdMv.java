package cmd_implement.cmd;

import static cmd_implement.Util.getNameStartPosition;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.exc.DoNotExistException;
import FileSys.exc.InvalidPathException;
import FileSys.exc.NameAlreadyExistsException;
import cmd_implement.Cmd;
import cmd_implement.Util;

public class CmdMv implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        try {
            Directory[] fromAndTo = Util.getFromAndToDirectories(currentState, args);
            move(args[0].substring(getNameStartPosition(args[0])), fromAndTo[0], fromAndTo[1]);
        } catch (InvalidPathException | NameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return currentState;
    }

    private void move(String name, Directory from, Directory to) {
        if (from.getDirectories().containsKey(name)) {
            if (to.getDirectories().containsKey(name))
                throw new NameAlreadyExistsException("Directory with such name already exists in destination directory");
            to.getDirectories().put(name, from.getDirectories().get(name));
            from.getDirectories().remove(name);
        } else if (from.getFiles().containsKey(name)) {
            if (to.getFiles().containsKey(name))
                throw new NameAlreadyExistsException("File with such name already exists in destination directory");
            to.getFiles().put(name, from.getFiles().get(name));
            from.getFiles().remove(name);
        } else throw new DoNotExistException("No such file or directory :" + name);
    }
}
