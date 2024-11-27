package Commands;

import java.io.File;
import mainPackage.Main;
import java.io.FileWriter;

public class MakeFile {
    public static String greg(String fileName) {
        boolean ongod = false;
        if (fileName.toLowerCase().contains(" -ongod")) {
            fileName = fileName.replace(" -ongod", "");
            ongod = true;
        }
        if (new File(Main.currentDir, fileName).isFile()) {
            return "File already exist";
        }
        boolean bol = false;
        try {
            File newFile = new File(Main.currentDir, fileName);
            bol = newFile.createNewFile();
            if (ongod) {
                try (FileWriter writer = new FileWriter(newFile)) {
                    writer.write("""
                            Gregorius T. Gregtech was a man of peculiar habits, always seen with a wrench and a mechanical blueprint. In Mechanisville, he was known for his odd ideas and brilliant inventions.
                            
                            One stormy evening, Gregorius unveiled his latest creation—a machine called the "Chrono-Tinker," meant to bend time. When lightning struck, the machine surged, and everything went dark. When the lights returned, Gregorius found himself in a different version of Mechanisville, with older buildings and people in strange clothing.
                            
                            Realizing his invention had not only altered time but reality itself, Gregorius used his skills to thrive in this new world, becoming a legend in both. He was known as the man who could bend not just time, but reality.
                            """);
                } catch (Exception e) {
                    return e.getMessage();
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        if (bol) {
            return "Successfully made file " + fileName;
        } else {
            return "Couldn't make file " + fileName;
        }
    }
}
