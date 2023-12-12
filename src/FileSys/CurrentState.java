package FileSys;


public class CurrentState {
    private Directory root;
    private Directory currentDir;

    public CurrentState() {
        this.root = new Directory(new Path());
        this.currentDir =  root;
    }

    public Directory getRootDirectory() {
        return root;
    }

    public void setRootDirectory(Directory root) {
        this.root = root;
    }

    public Directory getCurrentDirectory() {
        return currentDir;
    }

    public void setCurrentDirectory(Directory currentDir) {
        this.currentDir = currentDir;
    }
}
