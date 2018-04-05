/* 
 StringOps.java
 Purpose: Contains two methods that operate on strings - one to remove a specific character if
 contained in a string and another to determine if two strings are anagrams. Assumes, as described 
 in the homework problem, that strings are in all lowercase.
 CS230 Homework Assignment P2
 Written by: Diana Eastman
 Created date: February 11, 2013
*/

import java.util.*;

public class StringOps {
  
/**  Looks for the first instance of a given character in
 * a specified string; if found, the character is removed,
 * otherwise, the original string is returned.
 * @param name   a String
 * @param numRounds  a character
*/
  
  public static String removeChar  (String S, char ch) {
    String result="";
    int index = -1;
    
    index = S.indexOf(ch);
    char[] charArray = S.toCharArray();
    char[] charcopyArray = new char[charArray.length];
    
    int i=0;
    for (i=0; i< charArray.length; i++){
      if (i!=index){
      charcopyArray[i]=charArray[i];
      }
    }
    result = new String(charcopyArray);
    return result;
  }
  
  /**  Tests if two provided words are anagrams and 
 * prints a sentence indicating the result of the
 * test. Two words that are the same word are not 
 * considered anagrams
 * @param name   a String
 * @param numRounds  a String
*/
  
  public static void testAnagrams (String word1, String word2) 
  {
    if ((!word1.equals(word2)) && (word1.length()==word2.length())) 
    {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        String sorted1 = new String(chars1);
        String sorted2 = new String(chars2);
        if (sorted1.equals(sorted2))
              System.out.println(word1 + " and " + word2 + " are anagrams.");
    }
    else {
      System.out.println(word1 + " and " + word2 + " are not anagrams.");
    }
  }
  
  //Testing method
  public static void main (String args[]){
    String teststring1;
    String s1="e";
    char c = s1.charAt(0);
    teststring1= StringOps.removeChar("lemon", c);
    String teststring2;
    String s2="e";
    char d = s2.charAt(0);
    teststring2= StringOps.removeChar("java", c);
    System.out.println("The result is: " + teststring1);
    System.out.println("The result is: " + teststring2);
    StringOps.testAnagrams("canter", "nectar");
    StringOps.testAnagrams("hello", "hello");
    StringOps.testAnagrams("java", "bean");
    StringOps.testAnagrams("java", "");
    StringOps.testAnagrams("java", "jav");
  }
}