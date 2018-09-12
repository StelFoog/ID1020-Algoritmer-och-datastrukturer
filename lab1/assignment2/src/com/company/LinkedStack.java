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

    public int getSize() { return size; }

    public void push(Item item) {
         Node link = newest;
         newest = new Node(item, link);
         size++;
    }

    public Item pop() {
         if(size == 0) return null;
         Item pass = newest.item;
         newest = newest.link;
         size--;

         return pass;
    }

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
