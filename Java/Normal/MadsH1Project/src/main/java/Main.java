import java.io.File;
import java.util.Scanner;

public class Main {
    public static File currentDir = new File(System.getProperty("user.home"));

    public static void main(String[] arggers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        while (true) {
            System.out.print(currentDir.getAbsolutePath() + " >");
            String input = scanner.nextLine().strip();
            String[] args = input.split(" ", 2);

                switch (args[0].toLowerCase()) {
                    case "ls" -> {
                        GetChildItem.main(args[1]);
                        break;
                    }
                    case "cls" -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "echo" -> {
                        System.out.println(input.replace("echo ", ""));
                    }
                    case "mkdir" -> {
                        Mkdir.main(new File(currentDir, args[1]).getAbsolutePath());
                    }
                    case "cd" -> {
                        ChangeDirectory.main(args[1]);
                    }
                    default -> {
                        System.out.println("\u001B[31mInvalid command!\u001B[0m");
                        break;
                }
            }
        }
    }
}