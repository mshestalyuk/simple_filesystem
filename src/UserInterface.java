
import FileSys.CurrentState;
import cmd_implement.CommandExecutionService;

import java.util.Scanner;

public class UserInterface {
    public void run() {
        String command;
        String username = "user";
        String machineName = "server";
        CurrentState currentState = new CurrentState();
        currentState.getCurrentDirectory().addDirectory("dev");
        currentState.getCurrentDirectory().addDirectory("usr");
        currentState.getCurrentDirectory().addDirectory("docs");
        currentState.getCurrentDirectory().getDirectories().get("usr").addDirectory("admin");
        currentState.getCurrentDirectory().getDirectories().get("docs").addFile("file1");
        currentState.getCurrentDirectory().getDirectories().get("docs").addFile("file2");
        
        CommandExecutionService ces = new CommandExecutionService();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true){
                System.out.print(username+ "@" + machineName + ":" + currentState.getCurrentDirectory().getPath() + "$ ");
                command = scanner.nextLine();
                ces.executeCommand(currentState, command);
            }
        }
    }
}
