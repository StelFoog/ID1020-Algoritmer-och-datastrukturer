#include <stdio.h>

void recurcive() {
  char c = getchar();
  if(c != 0xA) {
    recurcive();
    putchar(c);
  }
}

void iterative(void) {
  char stack[64];
  int i;
  stack[0] = getchar();
  for(i = 1; stack[i-1] != 0xA; i++)
    stack[i] = getchar();
  for(i -= 2; i >= 0; i--)
    putchar(stack[i]);
}

int main(void) {
  recurcive();
  putchar('\n');
  iterative();
  putchar('\n');

  return 0;
}
