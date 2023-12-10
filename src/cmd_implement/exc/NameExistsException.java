package cmd_implement.exc;

public class NameExistsException extends RuntimeException{
    public NameExistsException(String message){
        super(message);
    }
}
