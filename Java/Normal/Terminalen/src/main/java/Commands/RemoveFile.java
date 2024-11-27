package Commands;

import mainPackage.Main;
import java.io.File;

public class RemoveFile {
    public static String remove(String path) {
        File file = null;
        if (new File(Main.currentDir, path).isFile() || new File(Main.currentDir, path).isDirectory()) {
            file = new File(Main.currentDir, path);
        } else if (new File(path).isDirectory() || new File(path).isFile()) {
            file = new File(path);
        }
        if (file != null) {
            boolean bol = file.delete();
            return "Successfully deleted " + path;
        } else {
            return "Couldn't delete " + path;
        }

    }
}
