/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic queue built using a double-linked list.

    Dependancies:   None
    Compilation:    javac -d . LinkedQueue.java
    Execution:      java com.company.LinkedQueue
    Usage:          Select an option by entering the corresponding number and pressing enter.
 */

package com.company;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item> {
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

    // Returns the size of the queue
    public int getSize() { return size; }

    // Adds a new item to the tail of the queue
    public void enqueue(Item item) {
        Node oldTail = tail;
        tail = new Node(item, oldTail); // creates a new tail with the old tail as the next in the queue
        if(size == 0) head = tail;      // if the queue was empty it makes the head equal the tail
        else oldTail.back = tail;       // else it makes the old tail recognize the new tail as next in line
        size++;
    }

    // returns the head of the queue and removes it from the queue
    public Item dequeue() {
        if(size == 0) throw new NoSuchElementException();   // if queue is empty
        Item pass = head.item;      // saves the item to be passed
        if(size == 1) {         // empties queue if this is the last node in queue
            head = null;
            tail = null;
        } else {                // sets the next in the queue to be head
            head = head.back;
            head.next = null;
        }
        size--;
        return pass;
    }

    // returns the entire queue as a string
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

    // implements an iterator
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() { return current != null; }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // main for testing
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedQueue<String> queue = new LinkedQueue<>();
        String option;
        do {
            System.out.println("\n\n\n\n");
            System.out.println("Options:");
            System.out.println("\n");
            System.out.println(" [1]    Enqueue");
            System.out.println(" [2]    Dequeue");
            System.out.println(" [3]    Print queue");
            System.out.println();
            System.out.println(" [0]    End");
            System.out.print("\n > ");
            option = in.nextLine();
            System.out.println("\n\n");
            switch(Integer.parseInt(option)) {
                case 0:
                    break;
                case 1:
                    System.out.println("String to enqueue: ");
                    queue.enqueue(in.nextLine());
                    break;
                case 2:
                    System.out.println("Dequeued string:\n" + queue.dequeue());
                    break;
                case 3:
                    System.out.println("Queue:\n");
                    System.out.println(queue);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        } while(Integer.parseInt(option) != 0);
    }
}
