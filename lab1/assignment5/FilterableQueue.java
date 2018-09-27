/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic queue built using a double-linked list with a filter function.

    Dependancies:   None
    Compilation:    javac -d . FilterableQueue.java
    Execution:      java com.company.FilterableQueue
    Usage:          Select an option by entering the corresponding number and pressing enter.
 */

package com.company;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class FilterableQueue<Item> {
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

    public FilterableQueue() {
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

    // filters out all nodes of the queue where the item is the same as the input
    public int removeItem(Item item) {
        Node current = head;
        int removedItems = 0;   // keeps count of how many items have been removed
        while(current != null) {
            if(current.item.equals(item)) { // if the item should be filtered out
                removedItems++;     // increases count of removed items
                if(current.next == null)    // if current == head
                    head = current.back;
                else
                    current.next.back = current.back;

                if(current.back == null)    // if current == tail
                    tail = current.next;
                else
                    current.back.next = current.next;
            }
            current = current.back;
        }
        size -= removedItems;
        return removedItems;
    }

    public Item removeItemAt(int k) {
        Item pass = null;
        if(k == 1) {
            pass = head.item;
            head.back.next = null;
            head = head.back;
        } else if(k == size) {
            pass = tail.item;
            tail.next.back = null;
            tail = tail.next;
        } else if(k < size) {
            Node current = head;
            for(int i = 1; i < k; i++)
                current = current.back;
            pass = current.item;
            current.next.back = current.back;
            current.back.next = current.next;
        } else
            throw new NoSuchElementException();

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

    // main for testing
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FilterableQueue<String> queue = new FilterableQueue<>();
        String option;
        do {
            System.out.println("\n\n\n\n");
            System.out.println("Options:");
            System.out.println("\n");
            System.out.println(" [1]    Enqueue");
            System.out.println(" [2]    Dequeue");
            System.out.println(" [3]    Remove item(s) with name");
            System.out.println(" [4]    Remove item at position");
            System.out.println(" [5]    Print queue");
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
                    System.out.println("String to filter out:");
                    int removed = queue.removeItem(in.nextLine());
                    System.out.println("\nAmount removed:\n" + removed);
                    break;
                case 4:
                    System.out.println("Position to remove from:");
                    int position = Integer.parseInt(in.nextLine());
                    System.out.println("\nRemoved item:\n" + queue.removeItemAt(position));
                    break;
                case 5:
                    System.out.println("Queue:\n");
                    System.out.println(queue);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        } while(Integer.parseInt(option) != 0);
    }
}
