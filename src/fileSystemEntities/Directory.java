package fileSystemEntities;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private Path path;
    private Map<String, Directory> directories = new HashMap<>();
    private Map<String, File> files = new HashMap<>();

    public Directory(Path path) {
        this.path = path;
    }

    public void addDirectory(String dirName){
        if(directories.containsKey(dirName)) System.out.println("Directory already exists");
        directories.put(dirName, new Directory(new Path(path, dirName)));
    }
    public void addFile(String fileName){
    files.put(fileName, new File(new Path(path, fileName)));
    }
    public void addFile(String fileName, String content){

    }

    @Override
    public String toString() {
        return path.getPath().get(path.getPath().size()-1);
    }

    public String getPath() {
        return path.toString();
    }

    public String getDirName(){
        return path.getPath().get(path.getPath().size()-1);
    }



    public Map<String, File> getFiles() {
        return files;
    }

    public Map<String, Directory> getDirectories() {
        return directories;
    }
}
