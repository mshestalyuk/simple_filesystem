package fileSystemEntities;

import java.util.LinkedList;

public class Path {
    LinkedList<String> path;
    public Path(){
        this.path = new LinkedList<>();
    }
    public Path(Path path, String name) {
        this.path = new LinkedList<>(path.getPath());
        this.path.add(name);
    }

    @Override
    public String toString() {
        return "/" + String.join("/", path);
    }

    public LinkedList<String> getPath() {
        return path;
    }
}
