package cmd_implement;

import FileSys.CurrentState;

public interface Cmd {
    public CurrentState execute(CurrentState currentState, String[] args);
}
