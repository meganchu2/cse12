/*
 * Filename: stack_program_extra.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: allows user input to stacks to test merge and swap functions
 * Date: March 17, 2017
 * Source of Help:
 */

#include <stdio.h>
#include <string.h>
#include "stack.h"


/*
 * Function prototype: Stack* merge( Stack* stack1, Stack* stack2 )
 * Description: merges two stacks maintaining order, must have pushed onto 
 *              2 stacks starting with items later in lexiographical order 
 *              and ending with items that come first in lexiographical order
 * Parameters: arg1 - Stack* stack1 -- first stack to merge
 *             arg2 - Stack* stack2 -- second stack to merge
 * Error Conditions: None.
 * Return Value: Stack* to the merged stack
 */
Stack* merge(Stack* stack1, Stack* stack2)
{
  Stack* merged = stack_init(); // stores merged stack with largest on top
  Stack* reverseIt = stack_init(); // reversed merged stack
  char* pop1;
  char* pop2;
  char* mPop;
  
  while(isEmpty(stack1) == FALSE && isEmpty(stack2) == FALSE)
  // both stacks not empty
  {
    if(isEmpty(merged) == FALSE) // not first iteration
    {
      if(strcmp(peek(merged), pop1) == 0) // if we have pushed pop1 already
      {
        pop1 = pop(stack1);
      }
      if(strcmp(peek(merged), pop2) == 0) // if we have pushed pop2 already
      {
        pop2 = pop(stack2);
      }
    }
    else // first iteration
    {
      pop1 = pop(stack1);
      pop2 = pop(stack2);
    }

    if(strcmp(pop1, pop2) < 0) // pop1 smaller than pop2
    {
      push(merged, pop1);
    }
    else if(strcmp(pop1, pop2) > 0) // pop2 smaller than pop1
    {
      push(merged, pop2);
    }
    else // pop1 = pop2
    {
      push(merged, pop1);
      push(merged, pop2);
    }
  } // at least one of stacks is empty

  while(isEmpty(stack1) == FALSE) // stack 1 still has items
  {
    if(strcmp(peek(merged), pop1) == 0) // if we have pushed pop1 already
    {
      pop1 = pop(stack1);
    }
    push(merged, pop1);
  }
  while(isEmpty(stack2) == FALSE) // stack 2 still has items
  {
    if(strcmp(peek(merged), pop2) == 0) // if we have pushed pop2 already
    {
      pop2 = pop(stack2);
    }
    push(merged, pop2);
  }

  while(isEmpty(merged) == FALSE) // pop all items in merged
  {
    mPop = pop(merged);
    push(reverseIt, mPop); // add popped items in reverseIt (smallest on top)
  }

  stack_delete(merged); // we don't need items in merged anymore
  free(merged); // we don't need merged anymore
  return reverseIt;
}

  

/*
 * Function prototype: void swap( Stack* stack1, Stack* stack2 )
 * Description: swaps two stacks
 * Parameters: arg1 - Stack* stack1 -- first stack to swap
 *             arg2 - Stack* stack2 -- second stack to swap
 * Error Conditions: None.
 * Return Value: void
 */
void swap(Stack* stack1, Stack* stack2)
{
  if(isEmpty(stack1) == FALSE) // stack1 has elements still (recursive case)
  {
    char* pop1 = pop(stack1);
    swap(stack1, stack2); 
    // recursive calls will lead to base case making stack2 empty

    push(stack2, pop1); // we have popped items onto stack, and are 
    // therefore pushing them onto stack2 maintaining original stack1 order
  }
  else // stack1 is empty, base case
  {
    Stack* reversed = stack_init(); // holds reversed stack2
    while(isEmpty(stack2) == FALSE) // stack 2 has elements still
    {
      char* pop2 = pop(stack2);
      push(reversed, pop2);
    } // stack2 is empty now
    while(isEmpty(reversed) == FALSE) // pop all elements in reversed
    {
      char* rPop = pop(reversed);
      push(stack1, rPop);
    } // reversed is empty now

    stack_delete(reversed);
    free(reversed); // we don't need reversed anymore
  }
}
      

/*
 * Function prototype: int main( int argc, char * argv[] )
 * Description: Main driver function.
 * Parameters: arg1 - int argc -- the number of arguments passed.
 *             arg2 - char *argv[] -- an array storing command line argument
 * Error Conditions: None.
 * Return Value: EXIT_FAILURE indicating failed execution, and EXIT_SUCCESS
 *               indicating successful execution.
 */
int main(int argc, char * argv[]) {

	Stack* stack1 = stack_init();
	Stack* stack2 = stack_init();

	if (stack1 == NULL || stack2==NULL) {
		fprintf(stderr, STR_ERR_MEM);
		return EXIT_FAILURE;
	}

	printf(STR_EC_ONE);
	printf(STR_STACK_WELCOME_EXTRA);
	printf(STR_PROMPT);

	//to store strings from stdin
	char readin[BUFSIZ];
	char* popped;
	char* peeked;
	
	int choice = STACK_EMPTY;
	
	//Loop until user wants to continue. This is for Stack 1
	while (choice!=CONTINUE) {

	  scanf("%d",&choice);
	  if(choice==CONTINUE) {
		break;
	  }
	
	  switch(choice) {
		
		case PUSH: 
				printf(STR_PUSH_PROMPT);
				scanf("%s",readin);
				push(stack1,readin);
				printf(STR_PUSH_SUCCESS, readin);
				break; // push onto stack1
				
		case POP: 
				popped = pop(stack1);
				if(popped!=NULL) {
					printf(STR_POP_SUCCESS, popped);
				} else {
					printf(STR_POP_FAILURE);
				}
				break; // pop from stack1
				
		case PEEK: 
				peeked = peek(stack1);
				if(peeked!=NULL) {
					printf(STR_PEEK_SUCCESS, peeked);
				} else {
					printf(STR_PEEK_FAILURE);
				}
				break; // get top element of stack1
				
		case PRINT: 
				print(stack1);
				break; // print elements in stack1
				
		case ISEMPTY:
				if(isEmpty(stack1)) {
					printf(STR_PRINT_NO_ITEMS);
				} else {
					printf(STR_PRINT_NOT_EMPTY);
				}
				break; // tells if stack1 empty
				
		default: printf(STR_ERR_UNKNOWN);
				
	  }
	  printf("\n");

	  printf(STR_PROMPT);
	}
	
	choice = STACK_EMPTY;
	printf(STR_EC_TWO);
	printf(STR_STACK_WELCOME_EXTRA);
	printf(STR_PROMPT);
	
	//Get input for Stack 2
	while (choice!=CONTINUE) {

	  scanf("%d",&choice);
	  if(choice==CONTINUE) {
		break;
	  }
	
	  switch(choice) {
		
		case PUSH: 
				printf(STR_PUSH_PROMPT);
				scanf("%s",readin);
				push(stack2,readin);
				printf(STR_PUSH_SUCCESS, readin);
				break; // push onto stack2
				
		case POP: 
				popped = pop(stack2);
				if(popped!=NULL) {
					printf(STR_POP_SUCCESS, popped);
				} else {
					printf(STR_POP_FAILURE);
				}
				break; // pop from stack2
				
		case PEEK: 
				peeked = peek(stack2);
				if(peeked!=NULL) {
					printf(STR_PEEK_SUCCESS, peeked);
				} else {
					printf(STR_PEEK_FAILURE);
				}
				break; // get top elements from stack2
				
		case PRINT: 
				print(stack2);
				break; // print elements in stack2
				
		case ISEMPTY:
				if(isEmpty(stack2)) {
					printf(STR_PRINT_NO_ITEMS);
				} else {
					printf(STR_PRINT_NOT_EMPTY);
				}
				break; // tells if stack2 is empty
				
		default: printf(STR_ERR_UNKNOWN);
				
	  }
	  printf("\n");

	  printf(STR_PROMPT);
	}

        //EC 2
	swap(stack1, stack2);
	printf(STR_SWAP_STACK);
	print(stack1);
	print(stack2);

        swap(stack1, stack2); // swap again to maintain original order for 
        // merging (doesn't really matter)

	//EC 1
	Stack* stack3 = merge(stack1, stack2);
	printf(STR_MERGE_STACK);
	print(stack3);

        // make sure to free memory of stack3 and elements after using it
        stack_delete(stack3);
        free(stack3);	
	
	
	//destroy stack
	stack_delete(stack1);
	stack_delete(stack2);
	free(stack1);
	free(stack2);
	printf(STR_GOODBYE);
	return EXIT_SUCCESS;
}
