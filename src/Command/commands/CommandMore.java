package Command.commands;

import Command.CommandLineCommand;
import Command.Exception.DoesNotExistException;
import Command.Exception.InvalidPathException;
import Command.Util;
import fileSystemEntities.CurrentState;
import fileSystemEntities.Directory;

import static Command.Util.getNameStartPosition;

public class CommandMore implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        Directory from;
        try {
            int nameBeginsAt = getNameStartPosition(args[0]);
            if (nameBeginsAt > 0) {
                from = Util.getEndOfThePathDirectory(currentState, args[0].substring(0, nameBeginsAt));
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
