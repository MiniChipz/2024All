import java.io.File;

public class RemoveFile {
    public static void remove(String path) {
        File file = null;
        if (new File(Main.currentDir, path).isFile() || new File(Main.currentDir, path).isDirectory()) {
            file = new File(Main.currentDir, path);
        } else if (new File(path).isDirectory() || new File(path).isFile()) {
            file = new File(path);
        }
        if (file != null) {
            boolean bol = file.delete();
            System.out.println("Successfully deleted " + path);
        } else {
            System.out.println("Couldn't delete " + path);
        }

    }
}
