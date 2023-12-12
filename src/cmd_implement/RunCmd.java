package cmd_implement;

import FileSys.CurrentState;
import cmd_implement.cmd.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RunCmd {
    private Map<String, Cmd> commands= new HashMap<>(){{
        put("ls", new CmdLs());
        put("exit", new CmdExit());
        put("mkdir", new CmdMkDir());
        put("cd", new CmdCd());
        put("cp", new CmdCp());
        put("more", new CmdMore());
        put("mv", new CmdMv());
        put("rm", new CmdRm());
        put("rmdir", new CmdRmDir());
        put("touch", new CmdTouch());

    }};


    public void executeCommand(CurrentState currentState, String commandLine){
        String[] args = getArgs(commandLine);

        if(commands.containsKey(args[0])){
            try {
                commands.get(args[0]).execute(currentState, Arrays.copyOfRange(args, 1, args.length));
            }catch (IndexOutOfBoundsException e){
                System.out.println("Invalid parameters number");
                e.printStackTrace();
            }
        }else System.out.println("Unknown command");


    }
    private String[] getArgs(String commandLine){
        String result = commandLine.replaceAll("\\s+", " ");
        return result.split(" ");
    }
}
