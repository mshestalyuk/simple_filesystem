package Command.commands;

import Command.CommandLineCommand;
import fileSystemEntities.CurrentState;

public class CommandExit implements CommandLineCommand {
    @Override
    public CurrentState execute(CurrentState currentState, String[] args) {
        System.exit(0);
        return null;
    }
}
