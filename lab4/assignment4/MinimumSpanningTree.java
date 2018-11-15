/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A Breadth First Paths finder for graphs, inspired by
                    Algorithms 4th ed. (Sedgewick, Wayne). Main for testing.

    Dependancies:   SortedEdges.java Edge.java StdIn.java
    Compilation:    javac -d . MinimumSpanningTree.java SortedEdges.java Edge.java StdIn.java
    Execution:      java com.company.MinimumSpanningTree < filename
    Usage:          Replace filename with the path for the graph file. The
                    MST paths will be printed out.
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinimumSpanningTree {
    private boolean[] marked;
    private Path path;

    public MinimumSpanningTree(SortedEdges G) {
        marked = new boolean[G.V()];
        path = new Path();
        mst(G);
    }

    private void mst(SortedEdges G) {
        for(int i = 0; i < G.V() - 1; i++) {
            for(Edge e : G) {
                int v = e.either();
                int w = e.other(v);
                if(path.isEmpty()) {
                    path.add(e.toString());
                    marked[v] = true;
                    marked[w] = true;
                } else if((!marked[v] && marked[w]) || (marked[v] && !marked[w])) {
                    marked[v] = true;
                    marked[w] = true;
                    path.add(e.toString());
                }
            }
        }
    }

    public Iterable<String> path() { return path; }

    // minimalistic stack to keep path in
    private class Path implements Iterable<String> {
        private Node head;

        private class Node {
            private String value;
            private Node next;

            private Node(String value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        public Path() {
            head = null;
        }

        public boolean isEmpty() { return head == null; }

        private void add(String value) {
            Node temp = new Node(value, head);
            head = temp;
        }

        // implements an iterator
        public Iterator<String> iterator() { return new ListIterator(); }

        private class ListIterator implements Iterator<String> {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                String value = current.value;
                current = current.next;
                return value;
            }
        }
    }

    public static void main(String[] args) {
        SortedEdges g = new SortedEdges(StdIn.readInt());
        StringBuilder s = new StringBuilder();
        Integer v = null, w = null;
        while(!StdIn.isEmpty()) {   // creates graph
            char c = StdIn.readChar();
            if(Character.isDigit(c) || c == '.') {
                s.append(c);
            }
            else if(s.length() > 0 && v == null) {
                v = Integer.parseInt(s.toString());
                s = new StringBuilder();
            } else if(s.length() > 0 && w == null) {
                w = Integer.parseInt(s.toString());
                s = new StringBuilder();
            } else if(s.length() > 0) {
                g.addEdge(new Edge(v, w, Double.parseDouble(s.toString())));
                v = null;
                w = null;
                s = new StringBuilder();
            }
        }

        MinimumSpanningTree finder = new MinimumSpanningTree(g);    // finds lowest path value

        // prints the path
        for(String str : finder.path())
            System.out.println(str);
    }

}
