/*
  Author:         Samuel Larsson
  Copyright:      2018
  Description:    A program to seperate negative numbers from positive numbers

  Dependancies:   None
  Compilation:    gcc negativeSeperation.c -o negativeSeperation.o
  Execution:      ./negativeSeperation.o < filename
  Usage:          Exchange filename for the path to a file with a given amount
                  of whole numbers. The program will print out all the numbers
                  with positive seperated from negative.
 */

#include <stdio.h>

// moves all negative numbers to the beginning of the array
void seperate(int a[], int amount) {
  int marker = 0; // keeps track of how many negative numbers have so far been inserted
  int temp;
  for(int i = 0; i < amount; i++) { // goes through entire array
    if(a[i] < 0) {  // moves current number to the front if it's negative
      temp = a[i];
      a[i] = a[marker];
      a[marker] = temp;
      marker++; // moves marker forward
    }
  }
}
// main for testing
int main(void) {
  int numbers;
  scanf("%d", &numbers);  // takes in the first number as the length of the array
  int elements[numbers];
  for(int i = 0; i < numbers; i++)  // takes in remaining numbers as part of the array
    scanf("%d", &elements[i]);

  seperate(elements, numbers);

  printf("Result:\n");
  for(int i = 0; i < numbers; i++)  // prints the segregated array
    printf("%d\n", elements[i]);
}
