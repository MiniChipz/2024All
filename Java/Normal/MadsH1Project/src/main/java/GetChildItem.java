import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class GetChildItem {
    public static void main(String path) {
        File dir = new File(path);
        try {
            dir.mkdir();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String[] files = dir.list();

        int antal = 0;
        if (files != null) {
            for (String fileName : files) {
                File file = new File(dir, fileName);
                if (file.isFile()) {
                    System.out.print(fileName + " ");
                } else if (file.isDirectory()) {
                    System.out.print("\u001B[33m" + fileName +"\u001B[0m ");
                }
                antal++;
            }
        }
        System.out.println("\nAntal filer: " + antal);
    }
}
