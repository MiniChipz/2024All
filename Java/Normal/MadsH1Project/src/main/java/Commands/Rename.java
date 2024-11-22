import java.io.File;

public class Rename {
    static void newName(String arg) {
        String[] names = arg.split(" ", 2);
        String oldName = names[0];
        String newname = names[1];
        File file = null;
        if (new File(Main.currentDir, oldName).isFile() || new File(Main.currentDir, oldName).isDirectory()) {
            file = new File(Main.currentDir, oldName);
        }
        if (file != null) {
            file.renameTo(new File(Main.currentDir, newname));
            System.out.println("File: \u001B[33m" + oldName + "\u001B[0m renamed to: \u001B[33m" + newname + "\u001B[0m");
        }
    }
}
