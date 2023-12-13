
import FileSys.CurrentState;
import cmd_implement.RunCmd;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
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
        
        RunCmd ces = new RunCmd();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true){
                System.out.print(username+ "@" + machineName + ":" + currentState.getCurrentDirectory().getPath() + "$ ");
                command = scanner.nextLine();
                ces.executeCommand(currentState, command);
            }
        }
    }
}
