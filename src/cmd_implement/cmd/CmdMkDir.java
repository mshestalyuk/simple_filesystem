package cmd_implement.cmd;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.exc.InvalidPathException;

import cmd_implement.Cmd;
import cmd_implement.Util;

public class CmdMkDir implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        int nameBeginsAt = 0;
        for(int i = args[0].length()-1; i >= 0; i--){
            if(args[0].charAt(i) == '/'){
                nameBeginsAt = i+1;
                break;
            }
            if(args[0].charAt(i) == '$'){
                nameBeginsAt = i+1;
                break;
            }
        }

        try {
            if(nameBeginsAt > 0){
                Directory finalParentDir = Util.resolveDirectoryPath(currentState, args[0].substring(0, nameBeginsAt));
                finalParentDir.addDirectory(args[0].substring(nameBeginsAt));
            } else {
                currentState.getCurrentDirectory().addDirectory(args[0]);
            }
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

        return currentState;
    }
}
