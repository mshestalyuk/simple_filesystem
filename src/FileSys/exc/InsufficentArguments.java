package FileSys.exc;

public class InsufficentArguments extends RuntimeException{
    public InsufficentArguments(String message){
        super(message);
    }
}