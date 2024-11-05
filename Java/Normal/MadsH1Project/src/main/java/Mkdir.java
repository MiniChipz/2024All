import java.io.File;

public class Mkdir {
    public static void main(String path) {
        File dir = new File(path);
        boolean bol = false;
        try {
            bol = dir.mkdir();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (bol) {
            System.out.println(path + " Was successfully made.");
        } else {
            System.out.println(path + " Couldn't be made.");
        }
    }
}
