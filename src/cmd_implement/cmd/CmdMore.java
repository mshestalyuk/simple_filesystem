package cmd_implement.cmd;

import static cmd_implement.Util.getNameStartPosition;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.CommandLineCommand;
import cmd_implement.Util;
import cmd_implement.exc.DoesNotExistException;
import cmd_implement.exc.InvalidPathException;

public class CmdMore implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
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
            else throw new DoesNotExistException("File with name \"" + name + "\" does not exist");
        } catch (InvalidPathException | DoesNotExistException e ) {
            System.out.println(e.getMessage());
        }
        return currentState;
    }
}
