package Commands;

import mainPackage.Main;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cat {
    public static String readFile(String path) throws IOException {
        File file = null;
        if (new File(Main.currentDir, path).isFile()) {
            file = new File(Main.currentDir, path);
        } else if (new File(path).isFile()) {
            file = new File(path);
        }
        StringBuilder output = new StringBuilder();
        if (file != null) {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            int lineNumber = 0;
            output.append(file.getName());
            for (String line : lines) {
                if (line.isEmpty()) {
                    output.append(line);
                } else {
                lineNumber++;
                output.append(lineNumber).append(": ").append(line).append("\n");
            }
                }
//            System.out.println(file.getName() + "\n" + lines);
            return output.toString().replaceAll("(\\d)", "\n$1");
        } else {
            return "Couldn't read " + path;
        }
    }
}
