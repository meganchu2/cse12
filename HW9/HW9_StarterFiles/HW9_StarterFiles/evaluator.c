#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

//Type conversions starts here//

char* charToString(char c) {
	char *str = (char*)malloc(sizeof(char)*TWO);
	str[ZERO] = c;
	str[ONE] = '\0';
	return str;
}
char stringToChar(char* str) {
	return str[ZERO];
}

char* intToString(int res) {
	char *str = (char*)malloc(BUFSIZ);
	sprintf(str,"%d",res);
	return str;
}

int stringToInt(char* str) {
	return atoi(str);
}
//Type conversions end here

// --------------------------------------------------------------------------

//Helper function to return precedence of operator
int precedence(char x) {
	//TODO
	return -1;
}


char* convertToPostFix(char* str) {
	//TODO
	char* postfix = "This function has not been implemented yet";
	return postfix;
	
}


int calculateExpression(char* str) {
	//TODO
	return -1;
}


int main(int argc, char **argv) {
	
	if(argc<TWO) {
		printf(STR_INCORRECT_ARGUMENT);
	} else {
		
		//TODO CHANGE BELOW!!
		char* input = NULL;  
		char* postfix = NULL;
		int answer;
		//TODO CHANGE ABOVE!!
		
		printf(STR_POSTFIX_EXPRESSION,postfix);
		printf(STR_RESULT,answer);
		free(input);
		free(postfix);
	}
	return EXIT_SUCCESS;
}
