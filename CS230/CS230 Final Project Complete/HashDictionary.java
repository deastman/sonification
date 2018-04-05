/**
 * CS230
 * Reads a dictionary from the input file, into a hash table. 
 * Each line in the file contains only one word.
 * The input file should not contain empty lines. 
 * An empty line signals the end of the file.
 * 
 *     MODIFICATIONS: - Instantiated generic type with the hashtable
 *                    - Dropped String tokenizer in reading 
 *                      words from file
 */

import java.io.*;
import java.util.*;

public class HashDictionary implements Dictionary {
  
  Hashtable<String,String> hashDictionary;
  
  //constructor
  /**
   *   
   *   @param  String dictFileName  the name of the file that contains the dictionary words
   *   @return  Hashtable  the Hashtable that contains all the dictionary words, 
   *                       keyed on their word.
   */
  
  
  public HashDictionary(String dictFileName) {
    
    int count = 0;   // keeps track of total number of words in the file 
    hashDictionary = new Hashtable<String,String>(100000);  //holds the dictionary; 
    //expected to be about 80K words
    
    // set up variables for reading words from a textfile
    try {   // set up file for reading line by line
      Scanner reader = new Scanner(new File(dictFileName));
      String line;     // to store each line in the file
      String word;     // to store each word in each line
      
      // process words from the textfile
      while (reader.hasNext()) {
        line = reader.nextLine();
        word = line.trim();
        word = word.toLowerCase();
        hashDictionary.put(word, word); //add the word into the hash table
        count++; 
      }
      reader.close();    // close file
    }
    catch (IOException e) {
      System.out.println("error in reading dictionary file");
    }
    //System.out.println("\n*************************");
    //System.out.println("Dictionary "+ dictFileName +" loaded containing " + count + " words");
    //System.out.println("*************************\n");
    
  }
  /**
   * Adds input String to the dictionary 
   */
  public void addWord(String word) {
    hashDictionary.put(word.toLowerCase(), word.toLowerCase());
  }
  
  /**
   * Searches the dictionary for the input String.
   * Returns true if found, false otherwise.
   */
  public boolean searchWord(String word) {
    return hashDictionary.containsKey(word.toLowerCase());
  }
  
  
  public static void main(String[] args) {
    HashDictionary hd = new HashDictionary("dictionary.txt");
    System.out.println("Diotima: " + hd.searchWord("Diotima"));
    hd.addWord("Diotima");
    System.out.println("Diotima: " + hd.searchWord("Diotima"));
    System.out.println("data: " + hd.searchWord("data"));
    System.out.println("blahblah: " + hd.searchWord("blahblah"));
    System.out.println("  ");
  }
  
}
