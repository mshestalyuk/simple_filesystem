package cmd_implement;

import FileSys.CurrentState;
import FileSys.Directory;
import cmd_implement.exc.InvalidPathException;

public class Util {

    public static Directory resolveDirectoryPath(CurrentState currentState, String path) throws InvalidPathException {
        boolean isAbsolutePath = path.startsWith("/");
        String[] pathComponents = isAbsolutePath ? path.substring(1).split("/") : path.split("/");

        Directory directory = isAbsolutePath ? currentState.getRootDirectory() : currentState.getCurrentDirectory();

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
        Directory from = (nameStartIndex > 0) ? resolveDirectoryPath(currentState, paths[0].substring(0, nameStartIndex))
                                              : currentState.getCurrentDirectory();
        Directory to = resolveDirectoryPath(currentState, paths[1]);

        return new Directory[]{from, to};
    }

    public static int getNameStartPosition(String path) {
        int lastIndex = path.lastIndexOf('/');
        return lastIndex == -1 ? 0 : lastIndex + 1;
    }

    private static String getLastPathComponent(String path) {
        int lastIndex = path.lastIndexOf('/');
        return lastIndex == -1 ? path : path.substring(lastIndex + 1);
    }
}
