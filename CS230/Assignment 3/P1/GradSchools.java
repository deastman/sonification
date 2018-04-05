/** 
 * GradSchools.java
 * Purpose: Stores information about a collection of CS grad schools and provides methods to sort the schools
 * according to four, weighted factors (Academics, Publication Impact, Research, and Overall Rating)
 * Written by: Diana Eastman
 * Modified date: February 19, 2013
 */

import java.util.*;

public class GradSchools {
  private School[] collection;
  private int count;
  private int test;
  
  /**
   *   School Constructor
   *   Creates a collection of schools
   *   and initializes count variable to 0
   */
  public GradSchools (int n) 
  {
    collection = new School[n]; //Set at 4 to show that the addSchool method works when 5th school added in main
    count = 0;
  }
 
  /**
   *   Loops through command line parameters, 
   *   passing to isInRange helper method; returns false
   *   when encounters integer in the array that is out of range
   *   @param integer array of weights arr
   *   @return boolean
   */
  public static boolean allInRange(int[] arr) 
  {
    for (int i = 0; i < arr.length; i ++) 
    {
        if (!isInRange(arr[i])) return false;
    }
    return true;
  }
  
  /**
  *   Tests if integer is between 1 and 5
  *   @param integer i
  *   @return boolean
  */
  private static boolean isInRange(int i) 
  {
    return i > 0 && i < 6;
  }
  
  /**
  * Adds a school to the collection array and increments count variable 
  * @param name, aRating (Academic rating), rRating (research rating), 
  * pRating (Publication Impact rating)
  */
  public void addSchool (String name, int aRating, int rRating, int pRating) 
  {
    if (count==collection.length) //Calls increaseSize() if count=current size of collection array so another school can be added
      increaseSize();
    
   collection[count] = new School(name, aRating, rRating, pRating);
   count ++;
  }
  
  /**
  * Doubles the size of the collection array by creating a temp array twice as large
  * and copying the collection array into the new temp array
  * from Java Foundations
  */
  private void increaseSize ()
  {
    School[] temp = new School[collection.length*2];
    
    for (int i=0; i <collection.length; i++)
    {
     temp[i]=collection[i];
    }
     collection = temp;
  }
  
  /**
  * Computes the weighted rating of each school, based on command line 
  * parameters 
  * @param, aweight (weight for Academics), rweight (weight for Research), pweight (weight for Pub. Impact)
  */
  private void computeRating (int aweight, int rweight, int pweight) 
  {
    int i =0;
    for (i = 0; i < count; i++) 
    {
      collection[i].computeRating(aweight, rweight, pweight);
    }
  }
  
   /**
  * Loops through the Schools in the collection and stores the appropriate factor (based on input parameter)
  * into the rank variable; calls sortArray() method
  * @param rankbyElement (determines which factor to sort on)
  */
  
  private void rankSchools (String rankbyElement)
  {
    int i =0;
    for (i = 0; i < count; i ++) 
    {
      if (rankbyElement.equals("Academics"))
      {
        collection[i].rank = collection[i].getAcademicRating();
      }
      else if (rankbyElement.equals("Research")) 
      {
        collection[i].rank = collection[i].getResearchRating();
      }
      else if (rankbyElement.equals("Publications")) 
      {
        collection[i].rank = collection[i].getPubImpactRating();
      }
      else 
      {
        collection[i].rank = collection[i].getOverallRating();
      }
    }
    sortArray();
  }
  
 /*
  * Sorts the integers in the input array in descending order and calls printOut()
  * Explanation (Task 1): 
  * Works by dividing the array to be sorted into two parts: the part that is already sorted
  * and the part that isn't (initially, the sorted portion is empty).
  * At each step in the algorithm, the sorted portion of the array is extended by one element.
  * The inner for loop finds the largest element in the unsorted portion of the array and 
  * swaps it with the element with the index as the first counter (at first pass through
  * elements at [0] and [1]) Array size - 1 passes through the loop are required to sort the entire array.
  * Modified from Sort.java
  */
 public void sortArray () 
 {
    int maxNum;    // maximum integer so far
    int maxIndex;  // index of maximum integer
    int i, j;
    for (j = count - 1; j > 0; j--) 
    { 
      maxIndex = 0;
      maxNum = collection[0].rank;
      
      for (i = 1; i <= j; i++) 
        if (collection[i].rank < maxNum) //descending order
      {
          maxNum = collection[i].rank;
          maxIndex = i;
      }
      swap(collection, maxIndex, j);
    }
    printOut(collection);
  }
  
 /** 
  * exchanges the contents of locations i and j in the input array
  */
 private void swap (School[] collection, int i, int j) 
 {
    School temp = collection[i];
    collection[i] = collection[j];
    collection[j] = temp;
 }
 
 /** 
  * Prints the name of each School in the collection in descending order after sorting
  */
 private void printOut (School[] collection)
 {
   int i=0;
   for (i = 0; i<count; i++)
   {
     System.out.println(collection[i].getName());
   }
 }
  
  /**
   *  toString method to print out GradSchool object
   *  @return String
   */
  final public String toString() 
  {
    String result ="";
    result += "There are " + count + " schools in the database \n";
        
    for (int i = 0; i < count; i++) {
      result += collection[i].toString();
    }
    return result;
  }
  
  //Testing method - takes in three parameters from the command line
  public static void main(String args[]) 
  {
    if ((args.length !=  3)) //Exit if three weights not provided
    { 
      System.out.println("Please provide 3 weights between 1 and 5 for Academics, Research and Publications"); 
      System.exit(0); 
    } 
    try {
      int aweight = Integer.parseInt(args[0]);
      int rweight = Integer.parseInt(args[1]);
      int pweight = Integer.parseInt(args[2]);
      int [] arr = {aweight, rweight, pweight};
      
      //Calls allInRage method and proceeds if true
      if (allInRange(arr))
      {
        /*Instantiate GradSchools object and add schools to database*/
        GradSchools myschools = new GradSchools(1);
        myschools.addSchool("MIT", 10, 10, 7);
        myschools.addSchool("Stanford", 8, 10, 9);
        myschools.addSchool("CMU", 7, 8, 6);
        myschools.addSchool("UC Berkeley", 9, 9, 9);
        myschools.addSchool("Cornell", 8, 9, 6); //Add 5th school to show that increaseSize() method functions properly
        myschools.computeRating(aweight, rweight, pweight);
        
        /*Print output*/
        System.out.println(myschools);
        System.out.println("\n" + "Ranking of schools from highest to lowest using Academics as a factor:");
        myschools.rankSchools("Academics");
        System.out.println("\n" + "Ranking of schools from highest to lowest using Publication Impact as a factor:");
        myschools.rankSchools("Publications");
        System.out.println("\n" + "Ranking of schools from highest to lowest using Research as a factor:");
        myschools.rankSchools("Research");
        System.out.println( "\n" + "Ranking of schools from highest to lowest using Overall as a factor:");
        myschools.rankSchools("Overall");
      }
      else 
      {
        System.out.println("One or more values are out of range.");
      }
    }
    
    catch (NumberFormatException e) {
      System.out.println("Sorry, one of your inputs is not an integer."); 
      System.out.println("Please provide 3 weights between 1 and 5, for Academics Research and Publications");
    }
  }
} // End GradSchool Class