/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic insertion sort.

    Dependancies:   StdIn.java
    Compilation:    javac -d . InsertionSort.java StdIn.java
    Execution:      java com.company.InsertionSort < filename
    Usage:          Replace filename with the path to any file. The strings in
                    the file will then be printed out sorted.
 */

package com.company;

public class InsertionSort {
    public static void sort(Comparable[] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = i; j > 0 && more(list[j], list[j-1]); j--)
                swap(list, j, j-1);
        }
    }

    private static boolean more(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    private static void swap(Object[] list, int i, int j) {
        Object t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    private static void show(Comparable[] list) {
        for(int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] list) {
        for(int i = 1; i < list.length; i++)
            if(more(list[i], list[i-1])) return false;
        return true;
    }

    public static void main(String[] args) {
        String[] list = StdIn.readAllStrings();
        sort(list);
        assert isSorted(list);
        show(list);
    }
}
