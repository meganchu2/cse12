#include <stdio.h>
#include "stack.h"

//will not compile until you provide a declaration for stack
int main(int argc, char * argv[]) {

	//will not compile till you provide a declaration for stack 
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
					break;
					
			case POP: 
					popped = pop(stack1);
					if(popped!=NULL) {
						printf(STR_POP_SUCCESS, popped);
					} else {
						printf(STR_POP_FAILURE);
					}
					break;
					
			case PEEK: 
					peeked = peek(stack1);
					if(peeked!=NULL) {
						printf(STR_PEEK_SUCCESS, peeked);
					} else {
						printf(STR_PEEK_FAILURE);
					}
					break;
					
			case PRINT: 
					print(stack1);
					break;
					
			case ISEMPTY:
					if(isEmpty(stack1)) {
						printf(STR_PRINT_NO_ITEMS);
					} else {
						printf(STR_PRINT_NOT_EMPTY);
					}
					break;
					
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
					break;
					
			case POP: 
					popped = pop(stack2);
					if(popped!=NULL) {
						printf(STR_POP_SUCCESS, popped);
					} else {
						printf(STR_POP_FAILURE);
					}
					break;
					
			case PEEK: 
					peeked = peek(stack2);
					if(peeked!=NULL) {
						printf(STR_PEEK_SUCCESS, peeked);
					} else {
						printf(STR_PEEK_FAILURE);
					}
					break;
					
			case PRINT: 
					print(stack2);
					break;
					
			case ISEMPTY:
					if(isEmpty(stack2)) {
						printf(STR_PRINT_NO_ITEMS);
					} else {
						printf(STR_PRINT_NOT_EMPTY);
					}
					break;
					
			default: printf(STR_ERR_UNKNOWN);
					
		}
		printf("\n");

		printf(STR_PROMPT);
	}

	//EC 1 - Remove if you are not implementing this EC function
	
	Stack* stack3 = merge(stack1, stack2);
	printf(STR_MERGE_STACK);
	print(stack3);
	
	//EC 2 - Remove if you are not implementing this EC function
	swap(stack1, stack2);
	printf(STR_SWAP_STACK);
	print(stack1);
	print(stack2);
	
	//destroy stack
	stack_delete(stack1);
	stack_delete(stack2);
	free(stack1);
	free(stack2);
	printf(STR_GOODBYE);
	return EXIT_SUCCESS;
}
