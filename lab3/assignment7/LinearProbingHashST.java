/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A linear probing hash symbol table, transcribed from
                    Algorithms 4th ed. (Sedgewick, Wayne) with a main test function
                    that counts the frequency of words using a slightly altered
                    version of the frequency counter from Algorithms 4th ed.

    Dependancies:   StdIn.java
    Compilation:    javac -d . LinearProbingHashST.java StdIn.java
    Execution:      java com.company.LinearProbingHashST < inputFilename
    Usage:          Replace inputFilename with the file that should be read
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearProbingHashST<Key, Value> {
    private int N;          // number of key-value pairs in the table
    private int M = 16;     // size of linear-probing ST
    private Key[] keys;     // the keys
    private Value[] vals;   // the vals

    public LinearProbingHashST() {
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }

    private LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) { return (key.hashCode() & 0x7fffffff) % M; }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null) t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if(N >= M/2) resize(2 * M);
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if(keys[i].equals(key))
                return vals[i];
        return null;
    }

    public boolean contains(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if(keys[i].equals(key))
                return true;
        return false;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue();
        for(int i = 0; i < M; i++)
            if(keys[i] != null) q.enqueue(keys[i]);

        return q;
    }

    private class Queue<Item> implements Iterable<Item> {
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

        public Queue() {
            size = 0;
            head = null;
            tail = null;
        }

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

        // implements an iterator
        public Iterator<Item> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<Item> {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.back;
                return item;
            }
        }
    }

    // main for testing
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        while(!StdIn.isEmpty()) {   // goes through all words and adds them to st
            String word = StdIn.readString().toLowerCase();
            if(!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        String max = "";
        st.put(max, 0);
        for(String key : st.keys()) {   // finds highest key-value pair
            if(st.get(key) > st.get(max))
                max = key;
        }
        System.out.println(max + "    " + st.get(max));     // prints highest key-value pair
    }

}
