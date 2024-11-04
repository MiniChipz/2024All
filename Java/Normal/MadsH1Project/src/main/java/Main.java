import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] arggers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(">");
        String input = scanner.nextLine();
        String[] args = input.split(" ");

        switch (args[0]) {
            case "ls" -> {
                GetChildItem.main(args[1]);
            }
            default -> {
                System.out.println("\u001B[31mInvalid command!\u001B[0m");
            }
        }
    }
}
