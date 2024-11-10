import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cat {
    public static void readFile(String path) throws IOException {
        File file = null;
        if (new File(Main.currentDir, path).isFile()) {
            file = new File(Main.currentDir, path);
        } else if (new File(path).isFile()) {
            file = new File(path);
        }
        if (file != null) {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            int lineNumber = 0;
            System.out.println(file.getName());
            for (String line : lines) {
                lineNumber++;
                System.out.println("\u001B[94m" + lineNumber + ": " + "\u001B[0m" + line);
            }
//            System.out.println(file.getName() + "\n" + lines);
        } else {
            System.out.println("Couldn't read " + path);
        }
    }
}
