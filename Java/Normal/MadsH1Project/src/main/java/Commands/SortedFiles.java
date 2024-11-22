import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class SortedFiles {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\Malte\\Desktop");
        File[] files = dir.listFiles();
        if (files != null) {
            // Sort files by size
            Arrays.sort(files, Comparator.comparingLong(File::length));

            for (File file : files) {
                System.out.println(file.getName() + " - Size: " + file.length() + " bytes");
            }
        }
    }
}
