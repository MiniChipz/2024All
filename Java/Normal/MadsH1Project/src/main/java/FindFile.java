import java.io.File;

public class FindFile {
    static void find(File directory, String fileName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                     FindFile.find(file, fileName);
                } else if (file.getName().contains(fileName)) {
                    System.out.println("Possible file: " + file.getAbsolutePath());
                }
            }
        }

    }
}
