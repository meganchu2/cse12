/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

/*
 * Filename: hw5_C.c 
 * Author: Megan Chu
 * Userid: A12814536
 * Login: cs12waot
 * Description: allows user to specify array size, input elements in array, 
 *              and return the location and value of the maximum value
 * Date: Feburary 13, 2017
 * Source of Help: Links from HW5 writeup
 *
 * NOTE: Need to press enter after entering each number before entering 
 *       next number
 *
 */


#include <stdio.h>
 

/* function declaration */
/* Returns the index of max element */
int max_elem_location(int[], int); 

/*
 * Function prototype: int main()
 * Description: Main driver function.
 * Parameters: None.
 * Side Effects: Read from stdin and print to stdout
 * Error Conditions: None.
 * Return Value: 0 indicating successful termination
 */
int main() 
{
  int location; // index of largest element
  int size; // size of array
  int toAdd; // to fill array

  printf("Enter the number of elements that will be in the array\n");
   
  scanf("%d", &size); // get an input (size of the array) from a user
  int input[size];

  printf("Enter %d integers\n", size); //prompt for the numbers  int i;

  int i;
  for(i = 0; i < size; i++) // add to an array
  {
    scanf("%d", &toAdd); // sets variable to user input
    input[i] = toAdd; // set array index to variable
  }
  
  location = max_elem_location(input, size); // find index of largest integer
  int value = input[location]; // store value of largest integer
  
  printf("Max element location = %d and value = %d.\n", location + 1, value);

  return 0;
}
 

/*
 * Function prototype: int int max_elem_location(int a[], int n)
 * Description: function to get the location (index) of the maximum element
 *              in an array
 * Parameters: arg1 - int a[] -- the input array to be searched
 *             arg2 - int n   -- the size (length) of array
 * Side Effects: None.
 * Error Conditions: None.
 * Return Value: the location (index) of the maximum element in such array
 */
int max_elem_location(int a[], int n) 
{
  int i;
  int returnVal = a[0];
  int returnInd = 0;
  for(i = 1; i < n; i++) // loop through array
  {
    if(a[i]>=returnVal) // if value at index greater than stored value
    {
      returnVal = a[i]; // store value at index
      returnInd = i; // set index to return
    }
  }
  return returnInd;
}

