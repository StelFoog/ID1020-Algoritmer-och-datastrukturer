/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    A program that replicates a file but replaces all characters
                    that aren't alphabetical, space or new line with blanks.

    Dependancies:   StdIn.java
    Compilation:    javac -d . OnlyAlphaBlank.java StdIn.java
    Execution:      java com.company.OnlyAlphaBlank < inputFilename > outputFilename
    Usage:          Replace inputFilename with the file that should be replicated
                    and replace outputFilename with where the result should go.
 */

package com.company;

class OnlyAlphaBlank {
    // checks if input is alphabetical (just a shorthand)
    private static boolean isAlpha(char c) {
        return Character.isLetter(c);
    }

    // main for testing
    public static void main(String[] args) {
        char c;
        while(!StdIn.isEmpty()) {   // takes all chars until file is empty
            c = StdIn.readChar();   // saves the next char
            if(isAlpha(c) || c == ' ' || c == '\n')     // check if char is alphabetical, a space or a newline marker
                System.out.print(c);    // adds to output file
            else
                System.out.print(' ');  // else; adds space to output file
        }
    }
}
