/*
 * Filename: main.c
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: main method that prints out information about person 
 *              based on user input
 * Date: March 8, 2017
 * Source of Help: Pen Chu
 */


/**
 * Addind C Standard library
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Adding local header files
 */
#include "Person.h"

/**
 * Definition of constants
 */
#define NUM_ARGS 4
#define fName 1
#define lName 2
#define dob 3

/**
 * Main method to make a Person struct, populate it and
 * call helper methods to print Person information.
 * 
 * Arguments:
 *     argc -- Holds the number of command line
 *             arguments passed.
 *     argv -- Holds each command line argument as
 *             char string.
 *
 * Error:
 * If we don't pass in 3 command line arguments, write an error to stderr
 * and terminate the program
 *
 * Return:
 * EXIT_FAILURE upon hitting an error. EXIT_SUCCESS on successful program run.
 */
int main( int argc, char* argv[] ) 
{	
  //Checks if we pass in 3 arguments
  if(argc != NUM_ARGS) 
  {
    fprintf( stderr, "Program only accepts 3 command line arguments.\n" );
             return EXIT_FAILURE;
  }

  //Make and populate Person struct from command line arguments
  struct Person myPerson;

  strcpy(myPerson.firstName, argv[fName]); // store first name argument
  strcpy(myPerson.lastName, argv[lName]); // store last name argument
  strcpy(myPerson.DOB, argv[dob]); // store date of birth argument

  //Print Person's initials followed by Person's information
  printInitials(myPerson);

  //then print Person informaiton:
  printf( "Information:\n" );

  //Print first and last name followed by whether Person is an adult or not
  print(myPerson);
  if(isAdult(myPerson) == 1) // if myPerson is 18 or over
  {
    printf("%s %s is an adult!", myPerson.firstName, myPerson.lastName);
  }
  else // myPerson is younger than 18
  {
    printf("%s %s is not an adult!", myPerson.firstName, myPerson.lastName);
  } 

  printf("\n"); // make sure we go to new line after executing main

  return EXIT_SUCCESS;
}

