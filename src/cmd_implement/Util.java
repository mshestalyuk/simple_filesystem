package cmd_implement;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.exc.InvalidPathException;
public class Util {

    public static Directory getEndOfThePathDirectory(CurrentState currentState, String fullPath) throws InvalidPathException {
        Directory endOfThePathDirectory;

        if (fullPath.equals("/")) {
            return currentState.getRootDirectory();
        }

        if (fullPath.charAt(0) == '/') {
            String[] path = fullPath.substring(1).split("/");
            endOfThePathDirectory = currentState.getRootDirectory();
            for (int i = 0; i < path.length - 1; i++) {
                String pathPart = path[i];
                endOfThePathDirectory = endOfThePathDirectory.getDirectories().get(pathPart);
                if (endOfThePathDirectory == null) {
                    throw new InvalidPathException("Invalid directory path: " + fullPath);
                }
            }
        } else {
            String[] path = fullPath.split("/");
            endOfThePathDirectory = currentState.getCurrentDirectory();
            for (int i = 0; i < path.length - 1; i++) {
                String pathPart = path[i];
                endOfThePathDirectory = endOfThePathDirectory.getDirectories().get(pathPart);
                if (endOfThePathDirectory == null) {
                    throw new InvalidPathException("Invalid directory path: " + fullPath);
                }
            }
        }

        // Check if the last part of the path is a file or a directory
        String lastPathPart = getLastPathPart(fullPath);
        if (endOfThePathDirectory.getDirectories().containsKey(lastPathPart)) {
            return endOfThePathDirectory.getDirectories().get(lastPathPart);
        } else if (endOfThePathDirectory.getFiles().containsKey(lastPathPart)) {
            return endOfThePathDirectory; // The directory containing the file
        } else {
            throw new InvalidPathException("No such file or directory: " + lastPathPart);
        }
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

    public static int getNameStartPosition(String path) {
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '/') {
                return i + 1;
            }
        }
        return 0;
    }

    private static String getLastPathPart(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }
}
