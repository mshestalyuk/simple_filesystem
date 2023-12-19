package FileSys.exc;

public class NameAlreadyExistsException extends RuntimeException{
    public NameAlreadyExistsException(String message){
        super(message);
    }
}
