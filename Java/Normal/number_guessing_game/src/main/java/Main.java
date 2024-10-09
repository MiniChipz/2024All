import java.awt.event.TextEvent;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int correct_number = random_number(1, 10);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gæt et tal fra 1-10");
        try {
            int guess;
            guess = scanner.nextInt();
            while (guess != correct_number) {
                System.out.println("Forkert! Gæt igen");
                guess = scanner.nextInt();
            }
            System.out.println("Du gættede rigtigt! Tallet var: " + correct_number);

        } catch (Exception e) {
            System.out.println("Du skal skrive et tal!");
            main(["ww"]);
        }
    }

    static int random_number(int int1, int int2) {
        return new Random().nextInt(int1, int2);
    }
}
