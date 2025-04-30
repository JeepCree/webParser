package ua.com.mobifix.test;


import java.util.Scanner;

public class Test {
    static String u = "string " ;
    static boolean isTrue = false;
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        int a = console.nextInt();
        int b = console.nextInt();
        if (a < b)
            System.out.println(a);
        else
            System.out.println(b);
        System.out.println();
    }
}
