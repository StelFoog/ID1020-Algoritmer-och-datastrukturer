/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    An Edge API, inspired by Algorithms 4th ed. (Sedgewick, Wayne)

    Dependancies:   none
    Compilation:    -
    Execution:      -
    Usage:          To be used by other programs
*/

package com.company;

import java.lang.IllegalArgumentException;

public class Edge implements Comparable<Edge> {
    private final int v;            // first vertex
    private final int w;            // second vertex
    private final double weight;    // weight of edge

    public Edge(int v, int w, double weight) {
        if(v < 0) throw new IllegalArgumentException();
        if(w < 0) throw new IllegalArgumentException();
        if(Double.isNaN(weight)) throw new IllegalArgumentException();
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() { return weight; }

    public int either() { return v; }

    public int other(int x) {
        if(x == v) return w;
        else if(x == w) return v;
        else throw new IllegalArgumentException();
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return v + "-" + w + " " + weight;
    }
}
