/*
 * Filename: Person.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: defines methods that prints out information about person 
 *              argument passed into methods
 * Date: March 8, 2017
 * Source of Help: Pen Chu
 */


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
#define THOUSANDS 1000
#define HUNDREDS 100
#define TENS 10


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
void print( Person argPerson ) 
{
  // prints first/last name and date of birth on three separate lines
  printf("First name: %s\n", argPerson.firstName);
  printf("Last name: %s\n", argPerson.lastName);
  printf("Date of birth: %s\n", argPerson.DOB);
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
void printInitials( Person argPerson ) 
{
  //Getting the first letter of the names
  printf("%c.", argPerson.firstName[0]);
  printf("%c. ", argPerson.lastName[0]);
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
int calculateAge( Person argPerson ) 
{
  //Getting the year of birth only
  int temp = INDEX_OF_DOB; // get starting index for year of birth

  // store digits in year of birth multiplied by corresponding place holder
  int a = (int)(argPerson.DOB[temp++])*THOUSANDS;
  int b = (int)argPerson.DOB[temp++]*HUNDREDS;
  int c = (int)argPerson.DOB[temp++]*TENS;
  int d = (int)argPerson.DOB[temp];

  // add digits together to get actual year
  int year = a + b + c + d;

  // get age by subtracting year of birth from 2016
  int age = CURRENT_YEAR - year;
  return age;  
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
int isAdult( Person argPerson ) 
{
  if(calculateAge(argPerson) >= ADULT_YEAR) // if age of person is 18 or over
  {
    return 1; // person is adult
  }
  else // age of person less than 18
  {
    return 0; // person is not adult
  }
}
