package Commands;

import mainPackage.Main;
import java.io.File;

public class Mkdir {
    public static String main(String path) {
        File dir = null;
        if (new File(Main.currentDir, path).getParentFile().isDirectory()) {
            dir = new File(Main.currentDir, path);
        } else if (new File(path).isAbsolute()) {
            dir = new File(path);
        }

        if (!dir.exists()) {
            try {
                boolean bol = dir.mkdir();
                return (bol ? "Successfully created " + path : "Couldn't create " + path);
            } catch (Exception e) {
                return "Error creating directory: " + e.getMessage();
            }
        } else {
            return "Directory already exists: " + path;
        }
    }
}
