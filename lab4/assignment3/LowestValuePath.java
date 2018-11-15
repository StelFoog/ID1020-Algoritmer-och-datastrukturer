/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A Lowest Value Path algorithm. Performs a DFS that checks
                    a distance instead of a marking

    Dependancies:   EdgeWeightedGraph.java Edge.java StdIn.java
    Compilation:    javac -d . LowestValuePath.java EdgeWeightedGraph.java Edge.java StdIn.java
    Execution:      java com.company.LowestValuePath x y < filename
    Usage:          Replace x with starting node and y with target node. Replace
                    filename with the path for the graph file. The shortest path
                    between them will be printed out.
*/

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LowestValuePath {
    private final int source;       // source node
    private int[] edgeTo;           // edge that leads to it with shortest path
    private boolean[] marked;       // marks all vertecies that have been touched
    private double[] distanceTo;    // distance to each vertex from source

    public LowestValuePath(EdgeWeightedGraph G, int source) {
        this.source = source;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distanceTo = new double[G.V()];
        for(int i = 0; i < G.V(); i ++)
            distanceTo[i] = Double.POSITIVE_INFINITY;
        distanceTo[source] = 0.0;
        lvp(G, source);         // finds lowest value path
    }

    private void lvp(EdgeWeightedGraph G, int v) {
        marked[v] = true;           // marks vertex as touched
        for(Edge e : G.adj(v)) {    // goes through all adjecent edges
            int w = e.other(v);
            double distance = distanceTo[v] + e.weight();
            if(distance < distanceTo[w]) {  // if the distance here is less than the previously recorded distance
                edgeTo[w] = v;
                distanceTo[w] = distance;
                lvp(G, w);              // goes through all edges adjecent to vertex w
            }
        }
    }

    // returns if theres a path to v from source
    public boolean hasPathTo(int v) { return marked[v]; }

    // returns the distance to v from source
    public double distanceTo(int v) { return distanceTo[v]; }

    // returns an iterable with all vertecies leading to v from source
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
        EdgeWeightedGraph g = new EdgeWeightedGraph(StdIn.readInt());
        if(x < 0 || x > g.V() || y < 0 || y > g.V())
            throw new NoSuchElementException("x and/or y nodes are out of bounds");
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
                g.addEdge(v, w, Double.parseDouble(s.toString()));
                v = null;
                w = null;
                s = new StringBuilder();
            }
        }

        LowestValuePath finder = new LowestValuePath(g, x);     // finds lowest path value

        // prints the path (or that there isn't one)
        if(finder.hasPathTo(y)) {
            for(int i : finder.pathTo(y))
                System.out.println(i);
            System.out.println(finder.distanceTo(y));
        }
        else
            System.out.println("There is no path from the source to the given node");
    }

}
