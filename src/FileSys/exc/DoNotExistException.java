package FileSys.exc;

public class DoNotExistException extends RuntimeException{
    public DoNotExistException(String message){
        super(message);
    }
}
