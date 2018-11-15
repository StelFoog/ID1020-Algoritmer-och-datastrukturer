/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic insertion sort (reversed).

    Dependancies:   StdIn.java
    Compilation:    javac -d . InsertionSort.java StdIn.java
    Execution:      java com.company.InsertionSort < filename
    Usage:          Replace filename with the path to any file. The strings in
                    the file will then be printed out sorted in decresing order.
 */

package com.company;

public class InsertionSort {
    // performs sort
    public static void sort(Comparable[] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = i; j > 0 && more(list[j], list[j-1]); j--)
                swap(list, j, j-1);
        }
    }
    // checks if one comparable is more than a second
    private static boolean more(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }
    // swaps position of two objects in an array
    private static void swap(Object[] list, int i, int j) {
        Object t = list[i];
        list[i] = list[j];
        list[j] = t;
    }
    // prints out entire array
    private static void show(Comparable[] list) {
        for(int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }
    // checks if array is sorted
    public static boolean isSorted(Comparable[] list) {
        for(int i = 1; i < list.length; i++)
            if(more(list[i], list[i-1])) return false;
        return true;
    }
    // main for testing
    public static void main(String[] args) {
        String[] list = StdIn.readAllStrings(); // puts all input strings in array
        sort(list);     // sorts array
        assert isSorted(list);  // checks to see that the array is sorted
        show(list);     // prints array
    }
}
