package cmd_implement;

import FileSys.CurrentState;
import FileSys.Directory;
import FileSys.exc.InvalidPathException;

public class Util {

    public static Directory resolveDirectoryPath(CurrentState currentState, String path) throws InvalidPathException {
        boolean isRootPath = path.startsWith("/");
        String[] pathComponents;
        Directory directory;
        if (isRootPath) {
            pathComponents = path.substring(1).split("/");
        } else {
            pathComponents = path.split("/");
        }
        if (isRootPath) {
            directory = currentState.getRootDirectory();
        } else {
            directory = currentState.getCurrentDirectory();
        }
        

        for (int i = 0; i < pathComponents.length - 1; i++) {
            directory = navigateToNextDirectory(directory, pathComponents[i], path);
        }

        return findEndDirectory(directory, getLastPathComponent(path));
    }

    private static Directory navigateToNextDirectory(Directory directory, String nextDir, String fullPath) throws InvalidPathException {
        Directory nextDirectory = directory.getDirectories().get(nextDir);
        if (nextDirectory == null) {
            throw new InvalidPathException("Invalid directory path: " + fullPath);
        }
        return nextDirectory;
    }

    private static Directory findEndDirectory(Directory directory, String lastComponent) throws InvalidPathException {
        if (directory.getDirectories().containsKey(lastComponent)) {
            return directory.getDirectories().get(lastComponent);
        } else if (!directory.getFiles().containsKey(lastComponent)) {
            throw new InvalidPathException("No such file or directory: " + lastComponent);
        }
        return directory;
    }

    public static Directory[] getFromAndToDirectories(CurrentState currentState, String[] paths) throws InvalidPathException {
        int nameStartIndex = getNameStartPosition(paths[0]);
        Directory from;
        if (nameStartIndex > 0) {
            from = resolveDirectoryPath(currentState, paths[0].substring(0, nameStartIndex));
        } else {
            from = currentState.getCurrentDirectory();
        }
        
        Directory to = resolveDirectoryPath(currentState, paths[1]);

        return new Directory[]{from, to};
    }

    public static int getNameStartPosition(String path) {
        int lastIndex = path.lastIndexOf('/');
        if (lastIndex == -1) {
            return 0;
        } else {
            return (lastIndex + 1);
        }
    }

    private static String getLastPathComponent(String path) {
        int lastIndex = path.lastIndexOf('/');
        if (lastIndex == -1) {
            return path;
        } else {
            return path.substring(lastIndex + 1);
        }
     }
}
