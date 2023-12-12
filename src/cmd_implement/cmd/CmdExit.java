package cmd_implement.cmd;

import FileSys.CurrentState;
import cmd_implement.CommandLineCommand;

public class CmdExit implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        System.exit(0);
        return null;
    }
}
