package com.company;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedQueue<Character> queue = new LinkedQueue<Character>();

        System.out.println("Add String to queue:");
        String str = in.next();

        for(int i = 0; i < str.length(); i++)
        queue.enqueue(str.charAt(i));
        System.out.println(queue);
        do {
        System.out.print(queue.dequeue());
        } while(queue.getSize() > 0);
        }
}
