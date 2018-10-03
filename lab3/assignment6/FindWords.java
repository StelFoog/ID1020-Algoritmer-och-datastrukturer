/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic binary search tree, transcribed from Algorithms 4th
                    ed. (Sedgewick, Wayne) with a main test function that counts
                    the frequency of the first N hundred words using a slightly
                    altered version of the frequency counter from Algorithms 4th ed.

    Dependancies:   StdIn.java
    Compilation:    javac -d . FindWords.java StdIn.java
    Execution:      java com.company.FindWords word < inputFilename
    Usage:          Replace inputFilename with the file that should be read and
                    word with the word to be searched for
 */

 package com.company;

 import java.util.ArrayList;

 public class FindWords {
     // checks if input is alphabetical (just a shorthand)
    private static boolean isAlpha(char c) {
        return Character.isLetter(c);
    }

    // main
    public static void main(String[] args) {
        String target = args[0];    // takes first input as target word

        ArrayList<Integer> distance = new ArrayList<>();
        int amount = 0;
        char c;
        StringBuilder s = new StringBuilder();
        while(!StdIn.isEmpty()) {   // goes through all the chars
            c = StdIn.readChar();
            amount++;       // tracks how many chars have passed
            if(isAlpha(c))      // if it's an alphabetical char it's added to a StringBuilder
                s.append(c);
            else if(s.toString() != "") {   // if there's a string and current char isn't alphabetical
                if(s.toString().equals(target))     // checks if string is the target
                    distance.add(amount - s.length());  // saves the distance if it is
                s = new StringBuilder();        // deletes the string
            }
        }

        for(int val : distance)     // prints all distances from start
            System.out.println("Distance in chars: " + val);
    }
 }
