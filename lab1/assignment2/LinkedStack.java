/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic single-linked circular list. Used to reverse a string.

    Compilation:    javac -d . LinkedStack.java
    Execution:      java com.company.LinkedStack
    Usage:          Write in a string and it will be printed out in reverse.
 */

package com.company;

import java.util.Scanner;

public class LinkedStack<Item> {
    private int size;
    private Node newest;

     private class Node {
        private Item item;
        private Node link;

        private Node(Item item, Node link) {
            this.item = item;
            this.link = link;
        }
    }

    public LinkedStack() {
         newest = null;
         size = 0;
    }

    // returns the size of the stack
    public int getSize() { return size; }

    // adds a new element to the top of the stack
    public void push(Item item) {
         Node link = newest;
         newest = new Node(item, link);
         size++;
    }

    // removes and returns the newest element of the stack
    public Item pop() {
         if(size == 0) return null;
         Item pass = newest.item;
         newest = newest.link;
         size--;

         return pass;
    }

    // returns the stack as a string
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node track = newest;
        while (track != null) {
            s.append("[");
            s.append(track.item.toString());
            s.append("], ");
            track = track.link;
        }
        return s.toString();
    }

    // main for testing
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedStack<Character> stack = new LinkedStack<Character>();

        System.out.println("Add String to stack:");
        String str = in.next();

        for(int i = 0; i < str.length(); i++)
            stack.push(str.charAt(i));
        System.out.println(stack);
        do {
            System.out.print(stack.pop());
        } while(stack.getSize() > 0);

    }
}
