package fileSystemEntities;

public class File {
    private Path path;
    private String name;
    private String content;

    public File(Path path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path.getPath().get(path.getPath().size()-1);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
