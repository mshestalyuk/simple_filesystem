package Command.commands;

import Command.CommandLineCommand;
import Command.Exception.InvalidPathException;
import Command.Util;
import fileSystemEntities.CurrentState;
import fileSystemEntities.Directory;

public class CommandCd implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        Directory tmpCurrentDirectory = currentState.getCurrentDirectory();
        if (args[0].equals("/")) {
            currentState.setCurrentDirectory(currentState.getRootDirectory());
            return currentState;
        }
        try {
            currentState.setCurrentDirectory(Util.getEndOfThePathDirectory(currentState, args[0]));
        }catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        return currentState;
    }
}
