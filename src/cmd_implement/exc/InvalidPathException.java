package cmd_implement.exc;

public class InvalidPathException extends RuntimeException{
    public InvalidPathException(String message){
        super(message);
    }
}
