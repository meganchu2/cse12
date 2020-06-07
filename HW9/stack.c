/*
 * Filename: stack.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: allocates memory for the stack and defines stack methods
 * Date: March 17, 2017
 * Source of Help: Haoyan Fan
 */

/**
 * Adding C standard library
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Adding local header files
 */
#include "stack.h"



/*
 * Function prototype: init(void)
 * Description: creates a Stack and initializes all necessary fields, and 
 *              returns the stack
 * Parameters: 
 * Return Value: initialized stack
 */
Stack * stack_init()
{
  // allocate space for the stack
  Stack* stack = (Stack*)malloc(sizeof(Stack));

  // allocate space for 100 pointers to strings, initialize array in stack
  stack->array = (char**)malloc(STACK_SIZE*sizeof(char*));

  // initialize top value of stack
  stack->top  = STACK_EMPTY;

  return stack;
}


/*
 * Function prototype: void push(Stack* stack, char* str)
 * Description: inserts the string at the appropriate index in stack array
 * Parameters: arg1 - Stack* stack -- pointer to the stack array to insert in
 *             arg2 - char* str -- pointer to the string to insert to stack
 * Return Value: EXIT_FAILURE indicating failed execution, and EXIT_SUCCESS
 *               indicating successful execution.
 */
void push(Stack* stack, char * str)
{
  stack->top = stack->top + 1; // increment top of stack

  // allocate space for string at new top of stack
  stack->array[stack->top] =  (char*)malloc(sizeof(strlen(str) + 1));

  // copy string to top of stack
  strcpy(stack->array[stack->top], str);
}


/*
 * Function prototype: char* pop(Stack* stack)
 * Description: removes the string at the last index inserted in stack
 * Parameters: arg1 - Stack* stack -- pointer to the stack array to remove 
 *                                    from
 * Return Value: pointer to the removed string if successful, NULL otherwise
 */
char* pop(Stack* stack)
{
  if(stack->top == STACK_EMPTY)
  // if index of top of stack is not in stack, then it is empty
  {
    return NULL;
  }
  else // index of top is in stack
  {
    // allocate memory in string pointer to return
    char* str = (char*)malloc(sizeof(strlen(stack->array[stack->top]) + 1));
    // we will free this memory in evaluator since we will add it to 
    // another stack (stack memory will be deleted together at end)

    // copy string at top of stack to pointer to return
    strcpy(str, stack->array[stack->top]);

    free(stack->array[stack->top]); // free memory at top of stack
    stack->top--; // decrement index of top of stack
    return str;
  }
}


/*
 * Function prototype: char* peek(Stack* stack)
 * Description: gives the last element inserted in the stack if any
 * Parameters: arg1 - Stack* stack -- pointer to the stack array to get 
 *                                    element
 * Return Value: pointer to the string in last index inserted to stack if 
 *               successful, NULL if stack is empty
 */
char* peek(Stack* stack)
{
  if(stack->top == STACK_EMPTY) // stack is empty
  {
    return NULL;
  }
  else // stack not empty
  {
    return stack->array[stack->top]; // return string at top index
  }
}


/*
 * Function prototype: void print(Stack* stack)
 * Description: gives the last element inserted in the stack if any
 * Parameters: arg1 - Stack* stack -- pointer to the stack array to print
 * Return Value: none
 */
void print(Stack* stack)
{
  if(stack->top != STACK_EMPTY) // stack not empty
  {
    printf(STR_PRINT_ITEMS);
    int i;
    for(i = 0; i <= stack->top; i++) // loop through stack array 0 -> top
    {
      printf(" %s", stack->array[i]); // string each string
    }
    printf("\n");
  }
  else // stack is empty
  {
    printf(STR_PRINT_NO_ITEMS);
  }
}
  

/*
 * Function prototype: int isEmpty(Stack* stack)
 * Description: tells if the stack contains any elements
 * Parameters: arg1 - Stack* stack -- pointer to the stack array check
 * Return Value: 1 if stack is empty, 0 otherwise
 */
int isEmpty(Stack* stack)
{
  if(stack->top == STACK_EMPTY) // stack is empty
  {
    return TRUE;
  }
  else // stack not empty
  {
    return FALSE;
  }
}


/*
 * Function prototype: void stack_delete(Stack* stack)
 * Description: deallocates the memory at each array index, and then the 
 *              array itself
 * Parameters: arg1 - Stack* stack -- pointer to the stack array to print
 * Return Value: none
 */
void stack_delete(Stack*stack)
{
  int i;
  for(i = 0; i < stack->top; i++) // loop through stack 0 -> top
  {
    free(stack->array[i]); // free memory at each index in stack
    // memory at indexes greater than top if any were freed by pop method
  }
  free(stack->array); // free memory for array for stack
}
