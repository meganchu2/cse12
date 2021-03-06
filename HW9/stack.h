/*
 * Filename: stack.h
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: defines the stack structure
 * Date: March 17, 2017
 * Source of Help:
 */


#include <stdio.h>
#include <stdlib.h>

#define STACK_SIZE 100

#define STACK_EMPTY -1
#define PUSH 1
#define POP 2
#define PEEK 3
#define PRINT 4
#define ISEMPTY 5
#define EXIT 6
#define CONTINUE 6

#define MINSIZE 2
#define TRUE 1
#define FALSE 0


#define STACK_PRINT_STR "------STACK INFORMATION------\n"\
                        "Number of elements in the stack:\t%d\n"\
                        "Index of the top of the stack:\t\t%d\n"\
                        "Number of operations since initialized:\t%d\n"\
                        "-----------------------------\n"
#define STR_PRINT_ITEMS "\nItem(s) stored in the stack are:"
#define STR_PRINT_NO_ITEMS "\nStack is empty\n"
#define STR_PRINT_NOT_EMPTY "\nStack is not empty\n"

#define STR_ERR_MEM "\nMemory is not available:(\n"

#define STR_PUSH_SUCCESS "\nSuccessfully pushed %s\n"
#define STR_PUSH_FAILURE "\nFailed to push to stack\n"

#define STR_POP_SUCCESS "\n%s\n"
#define STR_POP_FAILURE "\nStack is empty\n"

#define STR_PEEK_SUCCESS "\n%s\n"
#define STR_PEEK_FAILURE "\nStack is empty\n"

#define STR_ERR_UNKNOWN "\nInvalid choice. Please try again."

#define STR_STACK_WELCOME "Welcome to the Stack Program! ENTER \n1 to "\
                          "push(string)\n2 to pop()\n3 to peek(),\n4 to "\
                          "print()\n5 to check for empty Stack\n6 to Exit\n"
#define STR_STACK_WELCOME_EXTRA "\n1 to push(string)\n2 to pop()\n3 to "\
                                "peek(),\n4 to print()\n5 to check for "\
                                "empty Stack\n6 to continue\n"
#define STR_PROMPT "> "
#define STR_PUSH_PROMPT "\nEnter item to be pushed: "
#define STR_GOODBYE "\nGoodbye!\n"
#define STR_INCORRECT_ARGUMENT "\nIncorrect number of arguments passed.\n"
#define STR_POSTFIX_EXPRESSION "\nThe postfix expression is %s"
#define STR_RESULT "\nThe result of the expression is %d\n"

#define STR_BALANCED "\nThe expression is correctly balanced.\n"
#define STR_NOT_BALANCED "\nThe expression is not correctly balanced.\n"

#define STR_EC_ONE "\nEnter sorted strings as input for Stack 1"
#define STR_EC_TWO "\nEnter sorted strings as input for Stack 2"
#define STR_MERGE_STACK "\nStack 1 and Stack 2 have been merged."
#define STR_SWAP_STACK "\nStack 1 and Stack 2 have been swapped. Printing "\
                       "contents below"

/*
 * Struct of the stack
 */
typedef struct {
	char ** array; // need a double pointer to point to char* elements
	int top; // keep track of topmost item in stack
} Stack;

/*
 * Initializes a stack
 * Return:
 *   Normal: An empty stack
 *   When there is no memory available to allocate: null pointer
 */
Stack * stack_init();

/*
 * Pushes to a stack
 * Param:
 *   item - the stuff to be pushed
 */
void push(Stack* stack, char * str);

/*
 * Pops from a stack
 */
char * pop(Stack* stack);

/*
 * Returns top element of stack
 */
char * peek(Stack* stack);

/*
 * Destructs a stack
 */
void stack_delete(Stack* stack);

/*
 * Prints out the information of a stack
 */
void print(Stack* stack);

/*
 * Checks if the stack is empty
 */
int isEmpty(Stack* stack);

