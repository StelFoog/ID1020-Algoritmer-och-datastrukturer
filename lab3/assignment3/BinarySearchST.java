/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic binary search symbol table, transcribed from
                    Algorithms 4th ed. (Sedgewick, Wayne) with a main test function
                    that counts the frequency of words using a slightly altered
                    version of the frequency counter from Algorithms 4th ed.

    Dependancies:   StdIn.java
    Compilation:    javac -d . TopFrequency.java StdIn.java
    Execution:      java com.company.TopFrequency n m < inputFilename
    Usage:          Replace inputFilename with the file that should be read, n
                    with from where on the top it should show and m with how many
                    it should show
 */

package com.company;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }

    public Value get(Key key) {
        if(isEmpty()) return null;
        int i = rank(key);

        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public boolean contains(Key key) {
        if(isEmpty()) return false;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return true;
        else return false;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            for(int j = i + 1; j < N; j++) {
                keys[j - 1] = keys[j];
                vals[j - 1] = vals[j];
            }
        }
    }

    public Key getKeyAt(int pos) {
        if(pos < N) return keys[pos];
        else return null;
    }
}
