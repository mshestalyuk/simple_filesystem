package cmd_implement;

import FileSys.CurrentState;

public interface CommandLineCommand {
    public CurrentState execute(CurrentState currentState, String[] args);
}
