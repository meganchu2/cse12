/**
 * Including C libraries
 */
#include <stdio.h>
#include <stdlib.h>

/**
 * Including function prototypes
 */
#include "Person.h"

/**
 * Definition of constants
 */
#define INDEX_OF_DOB 6
#define CURRENT_YEAR 2016
#define ADULT_YEAR 18

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
void print( Person argPerson ) {
  //TODO
}

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
void printInitials( Person argPerson ) {
  //Getting the first letter of the names
  //Hint: think string as array of characters.
  //TODO
}

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
int calculateAge( Person argPerson ) {
  //Getting the year of birth only
  //Hint: think string as array of characters.
  //TODO
}

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
int isAdult( Person argPerson ) {
  //TODO
}
