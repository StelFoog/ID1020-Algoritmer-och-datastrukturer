/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A Graph API, inspired by Algorithms 4th ed. (Sedgewick, Wayne)

    Dependancies:   none
    Compilation:    -
    Execution:      -
    Usage:          To be used by other programs
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Graph {
    private final int V;
    private int E;
    private Adjecency[] adj;

    // minimalistic bag to keep track of adjecent nodes
    private class Adjecency implements Iterable<Integer> {
        private Node head;
        private int n;

        private class Node {
            private Integer value;
            private Node next;

            private Node(Integer value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        public Adjecency() {
            head = null;
            n = 0;
        }

        private void add(int value) {
            Node link = head;
            head = new Node(value, link);
            n++;
        }

        // implements an iterator
        public Iterator<Integer> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<Integer> {
            private Node current = head;
            private int i = 0;

            public boolean hasNext() { return current != null; }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                Integer value = current.value;
                current = current.next;
                return value;
            }
        }
    }

    // creates disconected nodes
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Adjecency[V];
        for(int i = 0; i < V; i++)
            adj[i] = new Adjecency();
    }

    public int V() { return V; }
    public int E() { return E; }

    // adds an edge between nodes
    public void addEdge(int v, int w) {
        adj[v].add(w);      // adds to v's adjecencies
        adj[w].add(v);      // adds to w's adjecencies
        E++;
    }

    public Iterable<Integer> adj(int v) { return adj[v]; }

}
