/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic symbol table, transcribed from Algorithms 4th ed.
                    (Sedgewick, Wayne). Only for use together with other files.

    Dependancies:   None
    Compilation:    -
    Execution:      -
    Usage:          -
 */

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SequentialSearchST<Key, Value> implements Iterable<Key> {
    private Node first;     // first node in the linked list

    // linked list node
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // search for key, return associated value
    public Value get(Key key) {
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key)) return x.val;     // search hit
        return null;                                // search miss
    }

    // search for key, update value if found, grow table if new
    public void put(Key key, Value val) {
        for(Node x = first; x != null; x = x.next) {
            if(key.equals(x.key)) {
                x.val = val;                    // search hit
                return;
            }
        }
        first = new Node(key, val, first);      // search miss
    }

    public Iterator<Key> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Key> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}
