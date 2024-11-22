import java.io.File;

public class GetChildItem {
    public static void ls() {
        File dir = new File(String.valueOf(Main.currentDir));
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
