package cmd_implement;

import FileSys.CurrentState;
import cmd_implement.cmd.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RunCmd {
    private Map<String, Cmd> commands;

    public RunCmd() {
        commands = new HashMap<>();
        commands.put("ls", new CmdLs());
        commands.put("exit", new CmdExit());
        commands.put("mkdir", new CmdMkDir());
        commands.put("cd", new CmdCd());
        commands.put("cp", new CmdCp());
        commands.put("more", new CmdMore());
        commands.put("mv", new CmdMv());
        commands.put("rm", new CmdRm());
        commands.put("rmdir", new CmdRmDir());
        commands.put("touch", new CmdTouch());
    }

    public void executeCommand(CurrentState currentState, String commandLine) {
        String[] args = getArgs(commandLine);

        if (commands.containsKey(args[0])) {
            try {
                commands.get(args[0]).exec(currentState, Arrays.copyOfRange(args, 1, args.length));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid parameters number");
                e.printStackTrace();
            }
        } else {
            System.out.println("Unknown command");
        }
    }

    private String[] getArgs(String commandLine) {
        String result = commandLine.trim().replaceAll("\\s+", " ");
        return result.split(" ");
    }
}
