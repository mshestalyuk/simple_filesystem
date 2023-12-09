package Command;

import fileSystemEntities.CurrentState;

public interface CommandLineCommand {
    public CurrentState execute(CurrentState currentState, String[] args);
}
