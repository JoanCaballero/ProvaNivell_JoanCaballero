package org.example;

import java.util.Scanner;

public class ConsoleManager {
    private static Scanner sc = new Scanner(System.in);

    public static int readInt(){
        return sc.nextInt();
    }
    public static String readString(){
        return sc.nextLine();
    }
    public static boolean readBoolean(){
        return sc.nextBoolean();
    }
    public static double readDouble(){
        return sc.nextDouble();
    }
}
