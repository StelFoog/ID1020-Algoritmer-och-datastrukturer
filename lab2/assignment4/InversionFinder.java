/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic insertion sort with an inversion counter.

    Dependancies:   StdIn.java
    Compilation:    javac -d . InsertionSort.java StdIn.java
    Execution:      java com.company.InsertionSort < filename
    Usage:          Replace filename with the path to any file. The inversions
                    of the array will be printed and then the strings in the
                    file will then be printed out sorted.
 */

package com.company;

public class InversionFinder {
    // performs sort
    public static void sort(Comparable[] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = i; j > 0 && less(list[j], list[j-1]); j--)
                swap(list, j, j-1);
        }
    }
    // checks if one comparable is less than a second
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    // counts and prints inversions of an array
    public static void inversions(Comparable[] list) {
        int count = 0;
        Comparable[] tempArr = Comparable[list.length];
        for(int i = 0; i < tempArr.length; i++)
            tempArr[i] = list[i];
        for(int i = 0; i < tempArr.length; i++) {
            int lowIndex = i;
            for(int j = i+1; j < tempArr.length; j++)
                if(less(tempArr[j], tempArr[lowIndex]))
                    lowIndex = j;
            if(lowIndex != i) {
                System.out.println("[" + i + ", " + tempArr[i] + "], [" + lowIndex + ", " + tempArr[lowIndex] + "]");
                swap(tempArr, i, lowIndex);
                count++;
            }
        }
        System.out.println("Inversions before sort: " + count);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
            if(less(list[i], list[i-1])) return false;
        return true;
    }

    public static void main(String[] args) {
        String[] list = StdIn.readAllStrings();
        inversions(list);
        sort(list);
        assert isSorted(list);
        show(list);
    }
}
