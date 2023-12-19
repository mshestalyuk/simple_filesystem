package FileSys;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private Path path;
    private Map<String, Directory> directories = new HashMap<>();
    private Map<String, File> files = new HashMap<>();
    private Directory parentDirectory; // Added reference to the parent directory

    public Directory(Path path) {
        this.path = path;
    }

    public Directory(Path path, Directory parentDirectory) {
        this.path = path;
        this.parentDirectory = parentDirectory;
    }


    
    public void addFile(String fileName){
        File f2 = new File(new Path(path, fileName));
        f2.setContent(generateRandomString(10)); // Example: 10 character long random string
        files.put(fileName, f2);
    }

    public void addDirectory(String dirName) {
        if (directories.containsKey(dirName)) {
            System.out.println("Directory already exists");
        } else {
            Directory newDirectory = new Directory(new Path(path, dirName), this); // Pass 'this' as the parent directory
            directories.put(dirName, newDirectory);
        }
    }

    @Override
    public String toString() {
        return path.getPath().get(path.getPath().size()-1);
    }

    public String getPath() {
        return path.toString();
    }

    public Path getPathNotString() {
        return path;
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

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    // function that generate random string and add it to file content
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
    
        while (length-- > 0) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
    
        return result.toString();
    }
}
