/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A Depth First Paths finder for graphs, inspired by
                    Algorithms 4th ed. (Sedgewick, Wayne). Main for testing.

    Dependancies:   Graph.java StdIn.java
    Compilation:    javac -d . DepthFirstPaths.java Graph.java StdIn.java
    Execution:      java com.company.DepthFirstPaths x y < filename
    Usage:          Replace x with starting node and y with target node. Replace
                    filename with the path for the graph file. A path between
                    them will be printed out.
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DepthFirstPaths {
    private boolean[] marked;   // visited nodes
    private int[] edgeTo;       // node that goes there
    private final int source;   // the source node

    public DepthFirstPaths(Graph G, int source) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = source;
        dfs(G, source);         // goes through all adjecent nodes to the source
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;       // markes node

        for(int w : G.adj(v)) {
            if(!marked[w]) {        // checks all adjecent nodes that havent been marked
                edgeTo[w] = v;      // marks adjecent node as visited
                dfs(G, w);          // goes through all adjecent nodes
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

        DepthFirstPaths finder = new DepthFirstPaths(g, x);     // performs DFS

        // prints the path (or that there isn't one)
        if(finder.hasPathTo(y))
            for(int i : finder.pathTo(y))
                System.out.println(i);
        else
            System.out.println("There is no path from the source to the given node");
    }
}
