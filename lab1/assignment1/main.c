/*
  Author:         Samuel Larsson
  Copyright:      2018
  Description:    A program to reverse an input, both through recurcion and iteration.

  Dependancies:   None
  Compilation:    gcc main.c -o main.o
  Execution:      ./main.o
  Usage:          Write in a string and it will be printed out in reverse.
                  First time is recurcive and the second is iterative.
 */

#include <stdio.h>

// takes in a line and prints it out in reverse
void recurcive() {
  char c = getchar();   // takes in a char
  if(c != 0xA) {        // if it's not the end of the line
    recurcive();        // calls itself again
    putchar(c);         // after all the recurcions end the char is printed out
  }                     // (in reverse since the last recurcion prints first)
}

// takes in a line and prints it out in reverse
void iterative(void) {
  char stack[64];       // creates an array to hold the chars
  int i;
  stack[0] = getchar();     // takes in the first char
  for(i = 1; stack[i-1] != 0xA; i++)
    stack[i] = getchar();   // takes in a char until the char taken is the end of the line
  for(i -= 2; i >= 0; i--)
    putchar(stack[i]);      // prints all the chars taken
}

// main for testing
int main(void) {
  printf("Enter line:\n");
  recurcive();
  putchar('\n');
  printf("Enter line:\n");
  iterative();
  putchar('\n');

  return 0;
}
