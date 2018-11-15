/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    An Edge Weighted Graph API, inspired by Algorithms 4th ed.
                    (Sedgewick, Wayne)

    Dependancies:   none
    Compilation:    -
    Execution:      -
    Usage:          To be used by other programs
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Adjecency[] adj;

    // minimalistic bag to keep track of adjecent nodes
    private class Adjecency implements Iterable<Edge> {
        private Node head;
        private int n;

        private class Node {
            private Edge value;
            private Node next;

            private Node(Edge value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        public Adjecency() {
            head = null;
            n = 0;
        }

        private void add(Edge value) {
            Node link = head;
            head = new Node(value, link);
            n++;
        }

        // implements an iterator
        public Iterator<Edge> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<Edge> {
            private Node current = head;
            private int i = 0;

            public boolean hasNext() { return current != null; }

            public Edge next() {
                if (!hasNext()) throw new NoSuchElementException();
                Edge value = current.value;
                current = current.next;
                return value;
            }
        }
    }

    // creates disconected nodes
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Adjecency[V];
        for(int i = 0; i < V; i++)
            adj[i] = new Adjecency();
    }

    public int V() { return V; }
    public int E() { return E; }

    // adds an edge between nodes
    public void addEdge(int v, int w, double weight) {
        Edge e = new Edge(v, w, weight);
        adj[v].add(e);      // adds to v's adjecencies
        adj[w].add(e);      // adds to w's adjecencies
        E++;
    }

    public Iterable<Edge> adj(int v) { return adj[v]; }

}
