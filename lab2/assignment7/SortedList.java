/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic insertion sort.

    Dependancies:   StdIn.java
    Compilation:    javac -d . SortedList.java StdIn.java
    Execution:      java com.company.SortedList < filename
    Usage:          Replace filename with the path to any file. The integers in
                    the file will be added to the sorted list.

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

        private Node(int value, Node next, Node back) {
            this.value = value;
            this.next = next;
            this.back = back;
        }
    }

    public SortedList() {
        head = null;
        N = 0;
    }

    public void add(int value) {
        if(head == null) {
            head = new Node(value, null, null);
        } else if(value < head.value) {
            Node temp = head;
            head = new Node(value, head, null);
            temp.back = head;
        } else {
            Node current = head;
            while(current.next != null) {
                if(value < current.next.value) {
                    Node temp = new Node(value, current.next, current);
                    current.next.back = temp;
                    current.next = temp;
                    break;
                }
                current = current.next;
            }
            if(current.next == null) {
                Node temp = new Node(value, null, current);
                current.next = temp;
            }
        }
        N++;
        print();
    }

    public void print() {
        Node current = head;
        while(current != null) {
            System.out.print(current.value  + ", ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortedList list = new SortedList();
        while(!StdIn.isEmpty())
            list.add(StdIn.readInt());
    }
}
