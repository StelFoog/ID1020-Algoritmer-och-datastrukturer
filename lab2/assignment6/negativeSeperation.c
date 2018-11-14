/*
  Author:         Samuel Larsson
  Copyright:      2018
  Description:    A program to seperate negative numbers from positive numbers

  Dependancies:   None
  Compilation:    gcc negativeSeperation.c -o negativeSeperation.o
  Execution:      ./negativeSeperation.o < filename
  Usage:          Exchange filename for the path to a file with a given amount of
                  whole numbers. The program will print out all the numbers
                  with positive seperated from negative.
 */

#include <stdio.h>

void seperate(int a[], int amount) {
  int marker = 0;
  int temp;
  for(int i = 0; i < amount; i++) {
    if(a[i] < 0) {
      temp = a[i];
      a[i] = a[marker];
      a[marker] = temp;
      marker++;
    }
    // invariant: all numbers before the marker are negative
    // ex:  for(int j = marker-1; j >= 0; j--)
    //        if(a[j] > 0) return false;
  }
}

int main(void) {
  int numbers;
  scanf("%d", &numbers);
  int elements[numbers];
  for(int i = 0; i < numbers; i++)
    scanf("%d", &elements[i]);

  seperate(elements, numbers);

  printf("Result:\n");
  for(int i = 0; i < numbers; i++)
    printf("%d\n", elements[i]);
}
