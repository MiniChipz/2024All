package Commands;

import mainPackage.Main;
import java.io.File;

public class ChangeDirectory {
    public static String cd(String path) {
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
            return "";
        } else {
            return "Couldn't find " + path;
        }
    }
}
