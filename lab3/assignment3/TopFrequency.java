/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A generic binary search symbol table, transcribed from
                    Algorithms 4th ed. (Sedgewick, Wayne) with a main test function
                    that counts the frequency of words using a slightly altered
                    version of the frequency counter from Algorithms 4th ed.

    Dependancies:   StdIn.java BinarySearchST.java
    Compilation:    javac -d . TopFrequency.java BinarySearchST.java StdIn.java
    Execution:      java com.company.TopFrequency n m < inputFilename
    Usage:          Replace inputFilename with the file that should be read, n
                    with from where on the top it should show and m with how many
                    it should show
 */

package com.company;

public class TopFrequency {
    // main for testing, counting the first N*100 words
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);  // saves the first two inputs
        BinarySearchST<String, Integer> st = new BinarySearchST<>(200000);
        while(!StdIn.isEmpty()) {   // iterates through the file until it's over or we've read the given amount of words
            String word = StdIn.readString().toLowerCase();
            if(!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);    // saves the word in lowercase format
        }

        int size = n + m;       // saves the size of
        String[] highKey = new String[size];
        for(int i = 0; i < size; i++) {     // string array to keep track of highest key-value pairs
            highKey[i] = "";
        }

        for(int i = 0; i < st.size(); i++) {    // goes through all items of the st
            String word = st.getKeyAt(i);
            if(st.get(word) > st.get(highKey[size - 1])) {  // saves the key if larger than any of the ones in the high value array
                for(int j = 0; j < size; j++) {
                    if(st.get(word) > st.get(highKey[j])) {     // finds where it should be put in
                        for(int k = size - 1; k > j; k--)       // shifts the array so new value fits
                            highKey[k] = highKey[k - 1];
                        highKey[j] = word;
                        break;
                    }
                }
            }
        }
        for(int i = n - 1; i < size; i++)       // prints high value array
            System.out.println(highKey[i] + "    " + st.get(highKey[i]));
    }
}
