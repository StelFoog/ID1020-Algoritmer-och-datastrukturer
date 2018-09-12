package com.company;

import java.util.Scanner;

public class LinkedQueue<Item> {
    private int size;
    private Node head;
    private Node tail;

    private class Node {
        private Item item;
        private Node next;
        private Node back;

        private Node(Item item, Node next) {
            this.item = item;
            this.next = next;
            back = null;
        }
    }

    public LinkedQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize() { return size; }

    public void enqueue(Item item) {
        Node oldTail = tail;
        tail = new Node(item, oldTail);
        if(size == 0) head = tail;
        else oldTail.back = tail;
        size++;
    }

    public Item dequeue() {
        if(size == 0) return null;
        Item pass = head.item;
        head = head.back;
        size--;
        if(size == 0) tail = null;
        return pass;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node track = head;
        while(track != null) {
            s.append("[");
            s.append(track.item.toString());
            s.append("], ");
            track = track.back;
        }
        return s.toString();
    }

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
