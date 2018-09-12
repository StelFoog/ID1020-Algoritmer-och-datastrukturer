package com.company;

import java.util.Scanner;

public class Main {

    public static void flipString(String str) {
        LinkedStack<Character> stack = new LinkedStack<Character>();
        for(int i = 0; i < str.length(); i++)
            stack.push(str.charAt(i));
        System.out.println(stack);
        do {
            System.out.print(stack.pop());
        } while(stack.getSize() > 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("String to flip:");
        String str = in.next();
        flipString(str);

    }
}
