package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner sc = new Scanner(System.in);

    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine();
    }

    public static int promptForInt(String prompt) {
        System.out.print(prompt + ": ");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }

    public static char promptForChar(String prompt) {
        System.out.print(prompt + ": ");
        char result = sc.next().toUpperCase().charAt(0);
        sc.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt) {
        System.out.print(prompt + ": ");
        double result = sc.nextDouble();
        sc.nextLine();
        return result;
    }

}