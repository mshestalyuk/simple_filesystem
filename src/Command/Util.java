package Command;

import Command.Exception.InvalidPathException;
import fileSystemEntities.CurrentState;
import fileSystemEntities.Directory;

public class Util {
    public static Directory getEndOfThePathDirectory(CurrentState currentState, String fullPath) throws InvalidPathException {
        Directory EndOfThePathDirectory;
        if (fullPath.equals("/")) {
            return currentState.getRootDirectory();
        }
        if (fullPath.charAt(0) == '/') {
            String[] path = fullPath.substring(1).split("/");
            EndOfThePathDirectory = currentState.getRootDirectory();
            for (String pathPart : path) {
                EndOfThePathDirectory = EndOfThePathDirectory.getDirectories().get(pathPart);
                if (EndOfThePathDirectory == null) {
                    throw new InvalidPathException("invalid path");
                }
            }
        } else {
            String[] path = fullPath.split("/");
            EndOfThePathDirectory = currentState.getCurrentDirectory();
            for (String pathPart : path) {
                EndOfThePathDirectory = EndOfThePathDirectory.getDirectories().get(pathPart);
                if (EndOfThePathDirectory == null) {
                    throw new InvalidPathException("invalid path");
                }
            }
        }
        return EndOfThePathDirectory;
    }

    public static Directory[] getFromAndToDirectories(CurrentState currentState, String[] args) throws InvalidPathException {
        Directory from, to;
        int nameBeginsAt = getNameStartPosition(args[0]);

        if (nameBeginsAt > 0) {
            from = Util.getEndOfThePathDirectory(currentState, args[0].substring(0, nameBeginsAt));
        } else {
            from = currentState.getCurrentDirectory();
        }
        to = Util.getEndOfThePathDirectory(currentState, args[1]);

        return new Directory[]{from, to};
    }
    public static int getNameStartPosition(String path){
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '/') {
                return i + 1;
            }
        }
        return 0;
    }
}
