/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Math;


/**
 * Title: class Complexity
 *  Description: class for testing efficiency of two search methods
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-01-29
 */
public class Complexity 
{

  public LinkedList<Integer> chain = new LinkedList<Integer>();
  public ArrayList<Integer> array = new ArrayList<Integer>();

  public int divide = 2; // for search2
  public int listSize = 5000; // size of list

  public static int toLook = 7500; // set number to look for in tests
  public static int loopRuns = 1000; // number of loops to average run times
  public int randomInt = 100000; // upper bound for numbers populating list


  /**
   * constructor for the class that initializes and sorts elements in array 
   * and linked list
   */
  public Complexity()
  {
    for(int i = 0; i<listSize; i++) 
    // populates lists with certain number of elements
    {
      int randomNum = (int)(Math.random()*randomInt); // randomizes elements

      while(array.contains(randomNum) || chain.contains(randomNum)) 
      // puts in array only if lists do not already contain elements
      {
        randomNum = (int)(Math.random()*randomInt);
      }
      chain.add(randomNum);
      array.add(randomNum);
    }

    for(int i = 1; i < array.size(); i++) // sorts lists using insertion sort
    {
      int toCompare = array.get(i); // stores element at current index
      int saveElement = chain.get(i); // stores element at current index
      int j = i - 1;

      while(j>=0 && array.get(j) > toCompare) 
      // shifts elements to right if larger than current element comparing
      {
        array.set(j + 1,  array.get(j));
        chain.set(j + 1,  chain.get(j));
        j--;
      }

      array.set(j + 1,  toCompare); // current element in sorted position
      chain.set(j + 1,  saveElement); // current element in sorted position 
    }
  }


  /**
   * uses linear search algorithm to search for certain element starting 
   * from beginning of list
   *
   * @param toFind: the specific element in list we are looking for
   * @return the index of element or -1 if element is not in list
   */
  public int search1(int toFind)
  {
    for(int i = 0; i < chain.size(); i++) // loops through LinkedList
    {
      if(chain.get(i) == toFind) // returns index of element if found
      {
        return i;
      }
    }
    return -1;
  }


  /**
   * uses binary search algorithm to search for certain element by splitting 
   * list in half and narrowing down choices
   *
   * @param toFind: the specific element in list we are looking for
   * @return the index of element or -1 if element is not in list
   */
  public int search2(int toFind)
  {
    int start = 0;
    int end = array.size() - 1;
    int half = 0;

    while(start <= end) // if there is still at least one element to compare
    {
      half = (start + end)/divide; // split the group of elements in half

      if(array.get(half) < toFind) 
      // eliminate first half if element is greater than first half
      {
        start = half + 1;
      }
      else if (array.get(half) > toFind)
      // eliminate last half if element is less than last half
      {
        end = half - 1;
      }
      else if (array.get(half) == toFind)
      // if only one element left, see if it matches what we are looking for
      {
        return half;
      }
    }
    return -1;
  }


  /**
   * main method that runs the search methods and prints out the runtimes 
   * of averages of each
   *
   * @param args: array of strings we can input when running code if user 
   * input is needed (in this case it is not)
   */
  public static void main(String args[])
  {
    Complexity tester = new Complexity(); // new tester for methods
    double avg1 = 0; // stores average time for search1
    double avg2 = 0; // stores average time for search2

    for(int i = 0; i < loopRuns; i++) // loop searches multiple times
    {
      long currTime = System.currentTimeMillis();
      tester.search1(toLook);
      long endTime = System.currentTimeMillis();;
      avg1 += (endTime - currTime); // total the runtimes

      currTime = System.nanoTime();
      tester.search2(toLook);
      endTime = System.nanoTime();
      avg2 += (endTime - currTime); // total the runtimes
    }

    avg1 = avg1/loopRuns; // average run times
    avg2 = avg2/loopRuns; // average run times

    // print runtimes
    System.out.println("Method 1 runtime in milli seconds is: " + avg1);
    System.out.println("Method 2 runtime in nano seconds is: " + avg2);
  }

} // end of Complexity class


