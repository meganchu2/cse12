/*
 * Filename: evaluator.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: calculates input using infix to postfix conversion
 * Date: March 17, 2017
 * Source of Help:
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.h"


//Type conversions starts here//

/*
 * Function prototype: int charToString( char c )
 * Description: Helper function to convert char to char*
 * Parameters: arg1 - c -- char to point to
 * Error Conditions: None.
 * Return Value: char* to specified char
 */
char* charToString(char c) 
{
	char *str = (char*)malloc(sizeof(char)*2);
	str[0] = c;
	str[1] = '\0';
	return str;
}

/*
 * Function prototype: int stringToChar( char* str )
 * Description: Helper function to convert char* to char
 * Parameters: arg1 - str -- char* that points to string
 * Error Conditions: None.
 * Return Value: char value pointed to by char*
 */
char stringToChar(char* str) 
{
	return str[0];
}

/*
 * Function prototype: int intToString( int res )
 * Description: Helper function to convert int to char*
 * Parameters: arg1 - res -- int to convert to string
 * Error Conditions: None.
 * Return Value: char* to string form of int
 */
char* intToString(int res) 
{
	char *str = (char*)malloc(BUFSIZ);
	sprintf(str,"%d",res);
	return str;
}

/*
 * Function prototype: int stringToInt( char* str )
 * Description: Helper function to convert char* to int
 * Parameters: arg1 - str -- char* that points to string
 * Error Conditions: None.
 * Return Value: int value of char pointed to by char*
 */
int stringToInt(char* str) 
{
	return atoi(str);
}
//Type conversions end here

// --------------------------------------------------------------------------

/*
 * Function prototype: int precedence( char x )
 * Description: Helper function to return precedence of operator
 * Parameters: arg1 - x -- char that stores operator to calculate precedence
 * Error Conditions: None.
 * Return Value: 1 if operator is *, /, or %, 
 *               0 if + or -, 
 *               -1 if operator is ( or )
 */
int precedence(char x) 
{
  if(x == '*' || x == '%' || x == '/') // highest precedence
  {
    return 1;
  }
  else if(x == '+' || x == '-') // char is + or -
  {
    return 0;
  }
  else // parentheses does not have any precedence
  {
    return -1;
  }
}


/*
 * Function prototype: char* convertToPostFix( char* str )
 * Description: takes input and puts it in postfix form
 * Parameters: arg1 - char* str -- an array that stores command line argument
 * Error Conditions: None.
 * Return Value: a char* pointing to converted postfix string
 */
char* convertToPostFix(char* str) 
{
  Stack* operatorStack = stack_init(); // stack for ordering operators
  
  // char pointer to postfix string
  char* postfix = (char*)malloc(sizeof(char)*(strlen(str) + 1)); 


  int i;
  for(i = 0; i < strlen(str); i++) // loop through all chars in input string
  {   
    if(*(str + i) != '(' && *(str + i) != ')' && *(str + i) != '+' && 
       *(str + i) != '-' && *(str + i) != '*' && *(str + i) != '/' &&
       *(str + i) != '%') // if we have an operand
    {
      char append = *(str + i);
      char* toAdd = charToString(append);
      strcat(postfix, toAdd);
      free(toAdd); // free after using
    }

    if(*(str + i) == '(') // if we have open parenthesis
    {
      char append = *(str + i);
      char* toAdd = charToString(append);
      push(operatorStack, toAdd); // push left parenthesis onto stack
      // will be freed when freeing stack
    }

    if(*(str + i) == ')') // if we have closing parenthesis
    {
      char* popped = pop(operatorStack);
      while(*popped != '(') // pop from stack and add to postfix until we 
      // reach open parenthesis
      {
        strcat(postfix, popped);
        free(popped); // free after using
        popped = pop(operatorStack);
      }
      free(popped); // free after using
    }

    if(*(str + i) == '+' || *(str + i) == '-' || *(str + i) == '*' || 
       *(str + i) == '/' || *(str + i) == '%') // if we have operator
    {
      char append = *(str + i);
      char* toAdd = charToString(append);

      if(isEmpty(operatorStack) == FALSE) // make sure stack not empty
      {
        char* top = peek(operatorStack);
        char op = *top;     
        while(precedence(op) >= precedence(append)) 
        // if top char has higher precedence than current operator
        {
          char* popped = pop(operatorStack); 
          // pop top char from stack and add to postfix

          strcat(postfix, popped);
          free(popped); // free after using

          if(isEmpty(operatorStack) == FALSE) // if stack not empty
          {
	    op = stringToChar(peek(operatorStack)); 
            // need to compare next element
          }
          else // stack empty
          {
            break; // go ahead with pushing current operator to stack
          }
        }
      }

      push(operatorStack, toAdd); // if top char of stack is lower 
      // precedence than current operator, push current operator onto stack
    }
  }

  while(isEmpty(operatorStack) == FALSE) 
  // after scanning input, pop remaining chars in stack
  {
    char* popped = pop(operatorStack);
    strcat(postfix, popped);
  }  

  stack_delete(operatorStack); // free element pointers in stack
  free(operatorStack); // free pointer to stack
  return postfix; // we will need to free postfix in main
	
}

/*
 * Function prototype: int calculateExpression( char * str )
 * Parameters: arg1 - char* str -- a pointer that stores postfix to calculate
 * Error Conditions: None.
 * Return Value: int for the calculated expression value
 */
int calculateExpression(char* str) 
{
  Stack* storage = stack_init(); // stack for storage of tokens in postfix

  int i;
  for(i = 0; i < strlen(str); i++) // loop through postfix
  {
    if(*(str + i) != '+' && *(str + i) != '-' &&
       *(str + i) != '*' && *(str + i) != '/' &&
       *(str + i) != '%')
    {
      char append = *(str + i);
      char* toAdd = charToString(append);
      push(storage, toAdd); // add all operands to stack
    }
    else // for the operators
    {
      int pop1 = stringToInt(pop(storage));
      int pop2 = stringToInt(pop(storage));
      int calc;
      if(*(str + i) == '+') // add top two elements in stack
      {
        calc = pop2 + pop1;      
      }
      else if(*(str + i) == '-') // subtract top element from 2nd top element
      {
        calc = pop2 - pop1;   
      }
      else if(*(str + i) == '*') // multiply top two elements in stack
      {
        calc = pop2*pop1;   
      }
      else if(*(str + i) == '/') // divide 2nd to top element by top element
      {
        calc = pop2 / pop1;   
      }
      else // char is %, mod 2nd to top element by top element
      {
        calc = pop2 % pop1;
      }
      char* solved = intToString(calc);
      push(storage, solved); // add calculated value back to stack
    }
  }

  int evaluated = stringToInt(pop(storage)); // get final calculated value

  stack_delete(storage); // free element pointers in stack
  free(storage); // free stack
  return evaluated; // do not need to free primitive type
}


/*
 * Function prototype: int checkParentheses( char* str )
 * Description: checks if we have a balanced number of parentheses
 * Parameters: arg1 - char* str -- a pointer that stores the input to parse
 * Error Conditions: None.
 * Return Value: 1 indicating expresssion is balanced, 0 for unbalanced
 */
int checkParentheses(char* str)
{
  Stack* paren = stack_init(); // stack for storage of parentheses
  int i;
  for(i = 0; i < strlen(str); i++) // loop through input
  {
    if(*(str + i) == '(') // if we have open parenthesis
    {
      char append = *(str + i);
      char* toAdd = charToString(append);
      push(paren, toAdd); // push left parenthesis onto stack
    }

    if(*(str + i) == ')') // if we have closing parenthesis
    {
      if(isEmpty(paren) == TRUE) 
      // stack should not be empty before scanning last ')'
      {
        stack_delete(paren);
        free(paren);
        return FALSE;
      }
      else // stack not yet empty
      {
        char* popped = pop(paren); // pop a '('
      }
    }
  }

  if(isEmpty(paren) == TRUE) // after looping through input if stack empty
  {
    stack_delete(paren); // free element pointers in stack
    free(paren); // free stack
    return TRUE; // normal
  }
  else // unbalanced, there are leftover '('
  {
    stack_delete(paren); // free element pointers in stack
    free(paren); // free stack
    return FALSE;
  }
}
  

/*
 * Function prototype: int main( int argc, char ** argv )
 * Description: Main driver function.
 * Parameters: arg1 - int argc -- the number of arguments passed.
 *             arg2 - char **argv -- a pointer storing command line argument
 * Error Conditions: None.
 * Return Value: EXIT_FAILURE indicating failed execution, and EXIT_SUCCESS
 *               indicating successful execution.
 */
int main(int argc, char **argv) 
{
  if(argc<2) // need 2 arguments ./a.out and the input expression
  {
    printf(STR_INCORRECT_ARGUMENT);
  } 
  else // we have needed arguments
  {
    char* input = (char*)malloc(sizeof(char)*(strlen(*(argv + 1) 
                                + 1)));
    strcpy(input, *(argv + 1)); // copy input argument

    if(checkParentheses(input) == FALSE)
    {
      printf(STR_NOT_BALANCED); // parentheses unbalanced
    }
    else // parentheses balanced
    {
      char* postfix = (char*)malloc(sizeof(char)
                      *(strlen(input) + 1));
      strcpy(postfix, convertToPostFix(input)); // derive postfix

      int answer = calculateExpression(postfix);
      // calculate answer from postfix
		
      printf(STR_POSTFIX_EXPRESSION,postfix);
      printf(STR_RESULT,answer);
      free(postfix); // free postfix after using
    }

    free(input); // free input after using
  }
  return EXIT_SUCCESS;
}
