package Commands;

import mainPackage.Main;
import java.io.File;

public class GetChildItem {
    public static String ls() {
        File dir = new File(String.valueOf(Main.currentDir));
        String[] files = dir.list();

        StringBuilder returna = new StringBuilder();
        int antal = 0;
        if (files != null) {
            for (String fileName : files) {
                File file = new File(dir, fileName);
                returna.append(fileName + ", ");
                System.out.println(fileName + " ");
                antal++;
            }
        }
        System.out.println("\nAntal filer: " + antal);
        return returna.toString();
    }
}
