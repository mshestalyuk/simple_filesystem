package cmd_implement.cmd;

import static cmd_implement.Util.getNameStartPosition;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.exc.DoNotExistException;
import FileSys.exc.InvalidPathException;
import cmd_implement.Cmd;
import cmd_implement.Util;

public class CmdMore implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        Directory from;
        try {
            int nameBeginsAt = getNameStartPosition(args[0]);
            if (nameBeginsAt > 0) {
                from = Util.resolveDirectoryPath(currentState, args[0].substring(0, nameBeginsAt));
            } else {
                from = currentState.getCurrentDirectory();
            }
            String name = args[0].substring(nameBeginsAt);
            if(from.getFiles().containsKey(name)){
                System.out.println(from.getFiles().get(name).getContent());
            }
            else throw new DoNotExistException("File with name \"" + name + "\" does not exist");
        } catch (InvalidPathException | DoNotExistException e ) {
            System.out.println(e.getMessage());
        }
        return currentState;
    }
}
