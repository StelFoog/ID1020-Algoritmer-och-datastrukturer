/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A Breadth First Paths finder for graphs, inspired by
                    Algorithms 4th ed. (Sedgewick, Wayne). Main for testing.

    Dependancies:   Graph.java StdIn.java
    Compilation:    javac -d . BreadthFirstPaths.java Graph.java StdIn.java
    Execution:      java com.company.BreadthFirstPaths x y < filename
    Usage:          Replace x with starting node and y with target node. Replace
                    filename with the path for the graph file. The shortest path
                    between them will be printed out.
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BreadthFirstPaths {
    private boolean[] marked;   // visited nodes
    private int[] edgeTo;       // node that goes there
    private final int source;   // the source node

    public BreadthFirstPaths(Graph G, int source) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = source;
        bfs(G, source);         // goes through all adjecent nodes to the source
    }

    private void bfs(Graph G, int source) {
        Queue q = new Queue();
        marked[source] = true;       // markes node

        q.enqueue(source);      // enqueues source
        while(!q.isEmpty()) {       // goes through entire queue
            int v = q.dequeue();    // dequeues first Node
            for(int w : G.adj(v)) {     // checks all vertex v's adjecencies
                if(!marked[w]) {        // marks and queues them if not marked
                    edgeTo[w] = v;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private class Queue implements Iterable<Integer> {
        private int size;
        private Node head;
        private Node tail;

        private class Node {
            private Integer value;
            private Node next;
            private Node back;

            private Node(Integer value, Node next) {
                this.value = value;
                this.next = next;
                back = null;
            }
        }

        public Queue() {
            size = 0;
            head = null;
            tail = null;
        }

        // Adds a new value to the tail of the queue
        public void enqueue(Integer value) {
            Node oldTail = tail;
            tail = new Node(value, oldTail); // creates a new tail with the old tail as the next in the queue
            if(size == 0) head = tail;      // if the queue was empty it makes the head equal the tail
            else oldTail.back = tail;       // else it makes the old tail recognize the new tail as next in line
            size++;
        }

        // returns the head of the queue and removes it from the queue
        public Integer dequeue() {
            if(size == 0) throw new NoSuchElementException();   // if queue is empty
            Integer pass = head.value;      // saves the value to be passed
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

        public boolean isEmpty() { return size == 0; }

        // implements an iterator
        public Iterator<Integer> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<Integer> {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                Integer value = current.value;
                current = current.back;
                return value;
            }
        }
    }

    // returns if theres a path to v from source
    public boolean hasPathTo(int v) { return marked[v]; }

    // returns the path from source to a given node
    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;

        Path path = new Path();
        for(int x = v; x != source; x = edgeTo[x])
            path.add(x);
        path.add(source);
        return path;
    }

    // minimalistic stack to keep path in
    private class Path implements Iterable<Integer> {
        private Node head;

        private class Node {
            private Integer value;
            private Node next;

            private Node(Integer value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        public Path() {
            head = null;
        }

        private void add(int value) {
            Node temp = new Node(value, head);
            head = temp;
        }

        // implements an iterator
        public Iterator<Integer> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<Integer> {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                Integer value = current.value;
                current = current.next;
                return value;
            }
        }
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);      // takes in x value
        int y = Integer.parseInt(args[1]);      // takes in y value
        Graph g = new Graph(StdIn.readInt());
        if(x < 0 || x > g.V() || y < 0 || y > g.V())
            throw new NoSuchElementException("x and/or y nodes are out of bounds");
        StringBuilder s = new StringBuilder();
        Integer v = null;
        while(!StdIn.isEmpty()) {   // creates graph
            char c = StdIn.readChar();
            if(Character.isDigit(c)) {
                s.append(c);
            }
            else if(s.length() > 0 && v == null) {
                v = Integer.parseInt(s.toString());
                s = new StringBuilder();
            } else if(s.length() > 0) {
                g.addEdge(v, Integer.parseInt(s.toString()));
                v = null;
                s = new StringBuilder();
            }
        }

        BreadthFirstPaths finder = new BreadthFirstPaths(g, x);     // performs BFS

        // prints the path (or that there isn't one)
        if(finder.hasPathTo(y))
            for(int i : finder.pathTo(y))
                System.out.println(i);
        else
            System.out.println("There is no path from the source to the given node");
    }
}
