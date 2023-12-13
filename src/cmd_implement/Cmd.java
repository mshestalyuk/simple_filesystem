package cmd_implement;

import FileSys.CurrentState;

public interface Cmd {
    public CurrentState exec(CurrentState currentState, String[] args);
}
