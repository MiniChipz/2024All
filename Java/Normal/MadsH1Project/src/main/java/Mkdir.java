import java.io.File;

public class Mkdir {
    public static void main(String path) {
        File dir = null;
        if (new File(Main.currentDir, path).getParentFile().isDirectory()) {
            dir = new File(Main.currentDir, path);
        } else if (new File(path).isAbsolute()) {
            dir = new File(path);
        }

        if (!dir.exists()) {
            try {
                boolean bol = dir.mkdir();
                System.out.println(bol ? "Successfully created " + path : "Couldn't create " + path);
            } catch (Exception e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }
}
