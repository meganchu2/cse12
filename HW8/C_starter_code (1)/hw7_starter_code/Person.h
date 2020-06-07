#ifndef PERSON_H
#define PERSON_H

//defining string of char as string
typedef char* string;

/**
 * Making struct Person with 3 members.
 * first name, last name and date of birth.
 *
 * Assume valid full name and date of birth 
 * when making a struct Person
 */
struct Person {
	//TODO
};
//defining struct Person as Person
typedef struct Person Person;


/**
 * Prints first name, last name and the date
 * of birth of Person to stdout.
 *
 * Argument:
 *    argPerson -- Person to read information from.
 *
 * Return:
 * none.
 */
void print( Person argPerson );

/**
 * Prints The initials of Person struct to stdout
 * and adds a space at end too.
 *
 * Argument:
 *    argPerson -- Person to read information from.
 *
 * Return:
 * none.
 */
void printInitials( Person argPerson );

/**
 * Calculates the age of Person by subtracting
 * the current year ( 2016 ) from the Person's
 * year of birth.
 *
 * Argument:
 *    argPerson -- Person to read information from.
 *
 * Return:
 * The age of Person passed in as argument.
 */
int calculateAge( Person argPerson );

/**
 * Checks if Person is an adult or not by getting
 * the age and comparing it to 18
 *
 * Argument:
 *    argPerson -- Person to read information from.
 *
 * Return:
 * 1 if Person is an adult. 0 otherwise.
 */
int isAdult( Person argPerson );

#endif //PERSON_H
