package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.CommandLineCommand;
import cmd_implement.Util;
import cmd_implement.exc.InvalidPathException;

public class CmdMkDir implements CommandLineCommand {
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
            Directory finalParentDir = Util.resolveDirectoryPath(currentState, args[0].substring(0, nameBeginsAt));
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
