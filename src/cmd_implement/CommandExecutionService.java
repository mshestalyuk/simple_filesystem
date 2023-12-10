package cmd_implement;

import FileSys.CurrentState;
import cmd_implement.cmd.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutionService {
    private Map<String, CommandLineCommand> commands= new HashMap<>(){{
        put("ls", new CommandLs());
        put("exit", new CommandExit());
        put("mkdir", new CommandMkDir());
        put("cd", new CommandCd());
        put("cp", new CommandCp());
        put("more", new CommandMore());
        put("mv", new CommandMv());
        put("rm", new CommandRm());
        put("rmdir", new CommandRmDir());
        put("touch", new CommandTouch());

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
