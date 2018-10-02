/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic binary search tree, transcribed from Algorithms 4th
                    ed. (Sedgewick, Wayne) with a main test function that counts
                    the frequency of the first N hundred words using a slightly
                    altered version of the frequency counter from Algorithms 4th ed.

    Dependancies:   StdIn.java
    Compilation:    javac -d . HashTable.java StdIn.java
    Execution:      java com.company.HashTable < inputFilename
    Usage:          Replace inputFilename with the file that should be read and
                    the program will print out where the words ended up in the
                    hashtable
 */

package com.company;

public class HashTable {
    // creates a hash code
    public static int getHashValue(String s, int hashSize) {
        return (s.hashCode() & 0x7fffffff) % hashSize;
    }

    // main
    public static void main(String[] args) {
        int hashSize = Integer.parseInt(args[0]);   // takes first input as hash size
        int[] wordsIn = new int[hashSize];          // array to count how many words go where
        for(int i = 0; i < hashSize; i++)
            wordsIn[i] = 0;
        while(!StdIn.isEmpty()) {       // takes in all words
            String word = StdIn.readString().toLowerCase();
            int hashVal = getHashValue(word, hashSize);
            wordsIn[hashVal]++;         // adds one where the word would go
        }

        int total = 0, min = wordsIn[0], max = wordsIn[0];
        for(int i = 0; i < hashSize; i++) {     // goes through all parts of the hash table
            System.out.println(i + 1 + "    " + wordsIn[i]);
            total += wordsIn[i];
            if(min > wordsIn[i]) min = wordsIn[i];
            if(max < wordsIn[i]) max = wordsIn[i];      // keeps track of min, max and total values
        }
        System.out.println("\nMin: " + min);
        System.out.println("Avg: " + total / hashSize);
        System.out.println("Max: " + max);              // prints min, avg and max values

    }
}
