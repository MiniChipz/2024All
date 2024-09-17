package org.example;

import java.util.Scanner;

public class pythonToJava {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void numberPrevSum(String[] args) {
        int prev = 0;
        int summ = 0;
        for (int i = 0; i < 10;) {
            summ += i;
            prev = i;
            i += 1;
            System.out.println("Current: " + ANSI_YELLOW + i + ANSI_RESET + " Prev: " + ANSI_YELLOW + prev + ANSI_RESET + " Sum: " + ANSI_YELLOW + summ + ANSI_RESET);
        }
    }
    public static void removeChars(String word, char letter) {
        word = word.toLowerCase();
        if (word.toLowerCase().contains(String.valueOf(letter).toLowerCase())) {
            for (int i = word.length() - 1; i >= 0; i--) {
                if (!(Character.toLowerCase(word.charAt(i)) == Character.toLowerCase(letter))) {
                } else {

                    System.out.println("Whole word: " + ANSI_YELLOW + word + ANSI_RESET);
                    System.out.println("Removed char: " + ANSI_YELLOW + word.charAt(i) + ANSI_RESET);
                    System.out.println("Index in word: " + ANSI_YELLOW + i + ANSI_RESET);
            }
        }
        }
        else {
            System.out.println("3");
            System.out.println(ANSI_RED + "Invalid arguments! " + word + ", " + letter + ANSI_RESET);
        }
    }
    public static void main(String[] args) throws InterruptedException {
//        removeChars("Ordet", 'E');
//        numGuessr(1, 10);
        calculatorSimple(10, "x", 5);
    }

    public static void numGuessr(int min, int max) throws InterruptedException {
        int correct = (int) (Math.random() * (max - min + 1)) + min;
        System.out.println("Pick a number between " + ANSI_YELLOW + min + ANSI_RESET + " and " +ANSI_YELLOW + max + ANSI_RESET);
        Scanner sc = new Scanner(System.in);
        int guess = sc.nextInt();
        if (guess == correct) {
            System.out.println(ANSI_GREEN + "You guessed correctly!" + ANSI_RESET);
        }
        else {
            System.out.println("You guessed incorrectly! The number was " + ANSI_YELLOW + correct + ANSI_RESET);
            Thread.sleep(2000);
            numGuessr(min, max);
        }
    }
    public static void calculatorSimple(int num1, String operator, int num2) {
        switch (operator.toLowerCase()) {
            case "-":
                int result = num1 - num2;
                System.out.println(num1 + " " + operator + " " + num2 + " = " + ANSI_GREEN + result + ANSI_RESET);
                break;
            case "+":
                result = num1 + num2;
                System.out.println(num1 + " " + operator + " " + num2 + " = " + ANSI_GREEN + result + ANSI_RESET);
                break;
            case "/":
                result = num1 / num2;
                System.out.println(num1 + " " + operator + " " + num2 + " = " + ANSI_GREEN + result + ANSI_RESET);
                break;
            case "*", "x":
                result = num1 * num2;
                System.out.println(num1 + " " + operator + " " + num2 + " = " + ANSI_GREEN + result + ANSI_RESET);
                break;
        }
    }
    public static void calculatorAdvanced() {
        Scanner input = new Scanner(System.in);

    }
}
