package org.example;

import java.util.Scanner;

public class additionCal {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Int eller String?");
            String svar = input.nextLine();

            System.out.println("Skriv tal nr 1:");
            int tal1 = Integer.parseInt(input.nextLine());

            System.out.println("Skriv tal nr 2:");
            int tal2 = Integer.parseInt(input.nextLine());

            int facit = tal1 + tal2;
            System.out.println("Facit = " + ANSI_GREEN + facit + ANSI_RESET);
        }

        catch (Exception fejl) {
            System.out.println("Fejl! " + ANSI_RED + fejl + ANSI_RESET);
        }
    }
}
