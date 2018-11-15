/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A sorted linked list.

    Dependancies:   StdIn.java
    Compilation:    javac -d . SortedList.java StdIn.java
    Execution:      java com.company.SortedList < filename
    Usage:          Replace filename with the path to any file. The integers in
                    the file will be added to the list sorted.

    }
 */

package com.company;

public class SortedList {
    private Node head;
    private int N;

    private class Node {
        int value;
        Node next;
        Node back;
        // constructor for Node
        private Node(int value, Node next, Node back) {
            this.value = value;
            this.next = next;
            this.back = back;
        }
    }
    // constructor for SortedList
    public SortedList() {
        head = null;
        N = 0;
    }
    // adds an item to the list sorted
    public void add(int value) {
        if(head == null) {  // creates head if list is empty
            head = new Node(value, null, null);
        } else if(value < head.value) { // takes over head if new item is less than head
            Node temp = head;
            head = new Node(value, head, null);
            temp.back = head;
        } else {
            Node current = head;
            while(current.next != null) {   // goes through all items greater than head
                if(value < current.next.value) {    // inserts new item if it's less than the next
                    Node temp = new Node(value, current.next, current);
                    current.next.back = temp;
                    current.next = temp;
                    break;
                }
                current = current.next;
            }
            if(current.next == null) {  // adds new item to the tail if there was no smaller item already
                Node temp = new Node(value, null, current);
                current.next = temp;
            }
        }
        N++;
        print();    // prints the current sorted list
    }
    // prints the sorted list
    public void print() {
        Node current = head;
        System.out.println("[ ");
        while(current != null) {    // goes through the list and prints each item
            System.out.print(current.value  + ", ");
            current = current.next;
        }
        System.out.println("]");
        System.out.println();
    }
    // main for testing
    public static void main(String[] args) {
        SortedList list = new SortedList();
        while(!StdIn.isEmpty()) // adds all ints from StdIn to the list
            list.add(StdIn.readInt());
    }
}
