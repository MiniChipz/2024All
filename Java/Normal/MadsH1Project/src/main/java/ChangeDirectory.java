import java.io.File;

public class ChangeDirectory {
    public static void cd(String path) {
        File newDir = null;
        if (path.equals("..")) {
            newDir = Main.currentDir.getParentFile();
        } else if (new File(Main.currentDir, path).isDirectory()) {
            newDir = new File(Main.currentDir, path);
        } else if (new File(path).isDirectory()) {
            newDir = new File(path);
        }
        if (newDir != null) {
            Main.currentDir = newDir;
        } else {
            System.out.println("Couldn't find " + path);
        }
    }
}
