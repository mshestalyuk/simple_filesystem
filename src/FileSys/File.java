package FileSys;

public class File {
    private Path path;
    private String filename;
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

    public String getFileName() {
        return filename;
    }

    public String getContent() {
        return content;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
