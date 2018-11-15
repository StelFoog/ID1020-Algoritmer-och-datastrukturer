package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedEdges implements Iterable<Edge> {
    Node smallest;
    int V;
    int E;

    private class Node {
        Edge edge;
        Node next;

        private Node(Edge edge, Node next) {
            this.edge = edge;
            this.next = next;
        }
    }

    public SortedEdges(int V) {
        smallest = null;
        this.V = V;
        this.E = 0;
    }

    public void addEdge(Edge edge) {
        if(smallest == null) {
            smallest = new Node(edge, null);
        } else if(edge.weight() < smallest.edge.weight()) {
            Node temp = new Node(edge, smallest);
            smallest = temp;
        } else {
            Node current = smallest;
            while(current != null) {
                if(current.next == null) {
                    Node temp = new Node(edge, null);
                    current.next = temp;
                    break;
                } else if(edge.weight() < current.next.edge.weight()) {
                    Node temp = new Node(edge, current.next);
                    current.next = temp;
                    break;
                }
                current = current.next;
            }
        }
        E++;
    }

    // public void removeEdge(Edge edge) {
    //     if(edge.weight() == smallest.edge.weight()) {
    //         smallest = smallest.next;
    //     } else {
    //         Node current = smallest;
    //         while(current != null) {
    //             if(current.next == null) return;
    //             if(current.next.edge.weight() == edge.weight()) {
    //                 current.next = current.next.next;
    //                 return;
    //             }
    //         }
    //     }
    // }

    public int V() { return V; }
    public int E() { return E; }

    // implements an iterator
    public Iterator<Edge> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Edge> {
        private Node current = smallest;

        public boolean hasNext() { return current != null; }

        public Edge next() {
            if (!hasNext()) throw new NoSuchElementException();
            Edge edge = current.edge;
            current = current.next;
            return edge;
        }
    }

}
