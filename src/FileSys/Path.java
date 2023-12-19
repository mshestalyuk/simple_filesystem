package FileSys;

import java.util.LinkedList;

public class Path {
    LinkedList<String> path;

    public Path(Path path, String name) {
        this.path = new LinkedList<>(path.getPath());
        this.path.add(name);
    }

    public Path(){
        this.path = new LinkedList<>();
    }



    public LinkedList<String> getPath() {
        return path;
    }

    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "/" + String.join("/", path);
    }
}
