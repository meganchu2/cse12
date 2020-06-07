/*
 * Filename: stack_program.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: runs a program to test methods of stack
 * Date: March 17, 2017
 * Source of Help:
 */


#include <stdio.h>
#include "stack.h"

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

	Stack* stack = stack_init(); // initialize a stack

	if (stack == NULL) {
		fprintf(stderr, STR_ERR_MEM);
		return EXIT_FAILURE;
	} // if stack could not be initialized

	printf(STR_STACK_WELCOME);
	printf(STR_PROMPT);

	//to store strings from stdin
	char readin[BUFSIZ];
	char* popped;
	char* peeked;
	
	int choice = STACK_EMPTY;
	
	//Loop until user wants to exit
	while (choice!=EXIT) {

	  scanf("%d",&choice); // get user input for choice
	  if(choice==EXIT) {
		break;
	  }
	
	  switch(choice) { // execute corresponding method based on choice
		
		case PUSH: 
				printf(STR_PUSH_PROMPT);
				scanf("%s",readin);
				push(stack,readin);
				printf(STR_PUSH_SUCCESS, readin);
				break; // push input to stack
				
		case POP: 
				popped = pop(stack);
				if(popped!=NULL) {
					printf(STR_POP_SUCCESS, popped);
				} else {
					printf(STR_POP_FAILURE);
				}
				break; // pop input from stack
				
		case PEEK: 
				peeked = peek(stack);
				if(peeked!=NULL) {
					printf(STR_PEEK_SUCCESS, peeked);
				} else {
					printf(STR_PEEK_FAILURE);
				}
				break; // get top element in stack
				
		case PRINT: 
				print(stack);
				break; // print elements in stack
				
		case ISEMPTY:
				if(isEmpty(stack)) {
					printf(STR_PRINT_NO_ITEMS);
				} else {
				printf(STR_PRINT_NOT_EMPTY);
				}
				break; // determines if stack is empty
				
		default: printf(STR_ERR_UNKNOWN);
				
	  }
	  printf("\n");

	  printf(STR_PROMPT);
	}

	//destroy stack
	stack_delete(stack);
	free(stack);
	printf(STR_GOODBYE);
	return EXIT_SUCCESS;
}
