package FileSys;


public class CurrentState {
    private Directory rootDirectory;
    private Directory currentDirectory;

    public CurrentState() {
        this.rootDirectory = new Directory(new Path());
        this.currentDirectory =  rootDirectory;
    }

    public Directory getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(Directory rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
    }
}
