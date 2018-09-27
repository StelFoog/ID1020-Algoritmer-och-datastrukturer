/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic, iterable double-linked circular list.

    Dependancies:   None
    Compilation:    javac -d . LinkedCircle.java
    Execution:      java com.company.LinkedCircle
    Usage:          Select an option by entering the corresponding number and pressing enter.
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LinkedCircle<Item> implements Iterable<Item> {
    private Node head;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node before;

        private Node(Item item, Node next, Node before) {
            this.item = item;
            this.next = next;
            this.before = before;
        }
    }

    public LinkedCircle() {
        head = null;
        size = 0;
    }

    // creates the first node of the circle if empty
    private void insertCreator(Item item) {
        head = new Node(item, null, null);  // creates node
        head.next = head;
        head.before = head;     // sets itself as both next and before in the list
    }

    // makes a new node at the start of the circle
    public void insertStart(Item item) {
        if (size == 0) insertCreator(item); // if circle is empty
        else {                              // if these's at least one node already
            Node temp = head;
            head = new Node(item, temp, temp.before);   // creates new node
            temp.before.next = head;
            temp.before = head;
        }
        size++;
    }

    // makes a new node at the end of the circle
    public void insertEnd(Item item) {
        if (size == 0) insertCreator(item); // if circle is empty
        else {                              // if these's at least one node already
            Node temp = new Node(item, head, head.before);  // creates a new node
            head.before.next = temp;
            head.before = temp;
        }
        size++;
    }

    // removes last node
    private Item removeBreaker() {
        Item temp = head.item;  // saves the item of the final node
        head = null;
        size--;

        return temp;
    }

    // removes an item from the start of the circle
    public Item removeStart() {
        if (size == 0) throw new NoSuchElementException();  // if there's no elements in circle
        else if (size == 1) return removeBreaker();         // if it's the last element
        else {                                              // if there are two or more nodes in the circle
            Node temp = head;           // saves the current head
            head = temp.next;           // moves head marker
            temp.before.next = head;
            head.before = temp.before;  // updates referances
            size--;
            return temp.item;
        }
    }

    // removes an item from the end of the circle
    public Item removeEnd() {
        if (size == 0) throw new NoSuchElementException();  // if there's no elements in circle
        else if (size == 1) return removeBreaker();         // if it's the last element
        else {                                              // if there are two or more nodes in the circle
            Node temp = head.before;    // saves the current end
            temp.before.next = head;
            head.before = temp.before;  // updates referances
            size--;
            return temp.item;
        }
    }

    // returns size
    public int getSize() {
        return size;
    }

    // returns the circle as a string
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = head;
        for(int i=0; i < size; i++) {
            s.append("[");
            s.append(current.item);
            s.append("], ");
            current = current.next;
        }

        return s.toString();
    }

    // implements an iterator
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() { return current != null; }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item push = current.item;
            current = current.next;
            return push;

        }
    }

    // main for testing
    public static void main(String[] args) {
        LinkedCircle<String> list = new LinkedCircle<>();
        Scanner in = new Scanner(System.in);
        String option;
        do {
            System.out.println("\n\n\n\n");
            System.out.println("Options:");
            System.out.println("\n");
            System.out.println(" [1]    Add to front");
            System.out.println(" [2]    Add to back");
            System.out.println(" [3]    Pop from front");
            System.out.println(" [4]    Pop from back");
            System.out.println(" [5]    Print link");
            System.out.println();
            System.out.println(" [0]    End");
            System.out.print("\n > ");
            option = in.nextLine();
            System.out.println("\n\n");
            switch(Integer.parseInt(option)) {
                case 0:
                    break;
                case 1:
                    System.out.println("String to add start: ");
                    list.insertStart(in.nextLine());
                    break;
                case 2:
                    System.out.println("String to add end: ");
                    list.insertEnd(in.nextLine());
                    break;
                case 3:
                    System.out.println("Item removed: ");
                    System.out.println(list.removeStart());
                    break;
                case 4:
                    System.out.println("Item removed: ");
                    System.out.println(list.removeEnd());
                    break;
                case 5:
                    System.out.println("Circular linked list:\n");
                    System.out.println(list);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        } while(Integer.parseInt(option) != 0);

    }
}
