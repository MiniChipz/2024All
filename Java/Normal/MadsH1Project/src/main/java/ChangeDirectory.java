import java.io.File;

public class ChangeDirectory {
    public static void main(String path) {
        File newDir = path.equals("..") ? Main.currentDir.getParentFile() : new File(Main.currentDir, path);
        if (newDir != null && newDir.isDirectory()) {
            Main.currentDir = newDir;
        } else {
            System.out.println("Couldn't find " + path);
        }
    }
}
