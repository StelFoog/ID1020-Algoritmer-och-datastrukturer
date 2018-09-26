/*
    Author:         Samuel Larsson
    Copyright:      2018
    Description:    Examines balance of parentheses in a file.

    Dependencies:   StdIn.java
    Compilation:    javac -d . Balance.java StdIn.java
    Execution:      java com.company.Balance < filename
    Usage:          Replace filename with the filepath of the file to be examined.
                    (Tests are in testing folder)
 */

package com.company;

class Balance {
    private static int roundCount;
    private static int squareCount;
    private static int curlyCount;

    public Balance() {
        roundCount = 0;
        squareCount = 0;
        curlyCount = 0;
    }

    // returns the different counts
    public int getRoundCount() { return roundCount; }
    public int getSquareCount() { return squareCount; }
    public int getCurlyCount() { return curlyCount; }

    // adds/removes from the different counts
    public void openRound() { roundCount++; }
    public void closeRound() { roundCount--; }

    public void openSquare() { squareCount++; }
    public void closeSquare() { squareCount--; }

    public void openCurly() { curlyCount++; }
    public void closeCurly() { curlyCount--; }

    // checks balance
    public static boolean isBalanced() {
        if(roundCount == 0 && squareCount == 0 && curlyCount == 0)  // checks if all counts are 0
            return true;
        else
            return false;
    }

    // returns a string of what the (in)balance of a count is, for use in toString function
    private static String balanceCheck(int count) {
        StringBuilder s = new StringBuilder();
        if(count == 0)  // if count is balanced
            s.append("Balanced");
        else {
            s.append("Too many ");
            s.append(count < 0 ?
                "closing brackets (by " + count * (-1) + ")" :  // if count is negative
                "opening brackets (by " + count + ")"           // if count is positive
            );
        }
        return s.toString();
    }

    // writes out balance for all bracket kinds. (uses balanceCheck function)
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\nRound Brackets:   ");
        s.append(balanceCheck(roundCount));
        s.append("\nSquare Brackets:  ");
        s.append(balanceCheck(squareCount));
        s.append("\nCurly Brackets:   ");
        s.append(balanceCheck(curlyCount));

        return s.toString();
    }

    // main for testing
    public static void main(String[] args) {
        Balance balance = new Balance();

        while(!StdIn.isEmpty()) {   // goes through each char of the input
            char item = StdIn.readChar();
            switch(item) {
                case '(':
                    balance.openRound();
                    break;
                case ')':
                    balance.closeRound();
                    break;
                case '[':
                    balance.openSquare();
                    break;
                case ']':
                    balance.closeSquare();
                    break;
                case '{':
                    balance.openCurly();
                    break;
                case '}':
                    balance.closeCurly();
                    break;
                default:
                    break;
            }
        }
        if(isBalanced())
            System.out.println("File parenthesis are balanced.");
        else
            System.out.println("File parenthesis are not balanced." + balance);
    }
}
