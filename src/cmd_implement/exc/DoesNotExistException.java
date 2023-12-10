package cmd_implement.exc;

public class DoesNotExistException extends RuntimeException{
    public DoesNotExistException(String message){
        super(message);
    }
}
