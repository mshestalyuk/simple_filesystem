package cmd_implement.cmd;

import FileSys.CurrentState;
import cmd_implement.Cmd;

public class CmdExit implements Cmd {
    @Override
    public CurrentState exec(CurrentState currentState, String[] args) {
        System.exit(0);
        return null;
    }
}
