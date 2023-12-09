package Command.commands;

import Command.CommandLineCommand;
import Command.Exception.InvalidPathException;
import Command.Util;
import fileSystemEntities.CurrentState;
import fileSystemEntities.Directory;

public class CommandMkDir implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        int nameBeginsAt = 0;
        for(int i = args[0].length()-1; i >= 0; i--){
            if(args[0].charAt(i) == '/'){
                nameBeginsAt = i+1;
                break;
            }
        }
        if(nameBeginsAt > 0){
            try {
            Directory finalParentDir = Util.getEndOfThePathDirectory(currentState, args[0].substring(0, nameBeginsAt));
            finalParentDir.addDirectory(args[0].substring(nameBeginsAt));
            }catch (InvalidPathException e){
                System.out.println(e.getMessage());
            }
        }else {
            currentState.getCurrentDirectory().addDirectory(args[0]);
        }

        return currentState;
    }
}
