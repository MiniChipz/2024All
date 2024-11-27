package Commands;

import mainPackage.Main;
import java.io.File;

public class Rename {
    public static String newName(String arg) {
        String[] names = arg.split(" ", 2);
        String oldName = names[0];
        String newname = names[1];
        File file = null;
        if (new File(Main.currentDir, oldName).isFile() || new File(Main.currentDir, oldName).isDirectory()) {
            file = new File(Main.currentDir, oldName);
        }
        if (file != null) {
            file.renameTo(new File(Main.currentDir, newname));
            return "File: " + oldName + " renamed to: " + newname;
        }
        return "Couldn't rename file" + oldName;
    }
}
