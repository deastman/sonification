/**
 *  CS230 Lab: Hash tables
 *  Speller.java   
 *  MODIFICATIONS:  Sohie dropped String tokenizer in favor of split()
 *  Stella, 4/11: Changed to instance methods, updated main(0 etc...
 *
 **/

import java.io.*;
import java.util.*;

public class Speller  {
  
  HashDictionary dictionary;
  LinkedList<String> misspelledWords;
  
  public Speller(String dictFileName) {
    dictionary = new HashDictionary(dictFileName);
    misspelledWords = new LinkedList<String>();
  }
    
  /**
   * foundAlready returns true if the word already occurs in the LinkedList
   * of misspelled words.  This is a helper method for checkSpelling
   * so that the misspelled linkedList contains only unique misspelled words
   * 
   * @param LinkedList words  a LinkedList of words that are misspelled 
   *  in the current document
   * @param String word  the word to look for in the LinkedList words
   * @return boolean   Returns true if the word is found in the list of 
   *  misspelled words; false otherwise
   *
   */
  private boolean foundAlready(String word){
    for (int i= 0; i < misspelledWords.size(); i++) {
      if (((String)misspelledWords.get(i)).equals(word))
        return true;
    }
    return false;  // made it thru LL without finding word
  }
  
  /**  
   *
   * Reads in the file to be spell-checked as well as the HashTable word table.
   * This method uses split() to parse the file, so it is important that
   * each word in the file is separated by a single space.
   *
   *  @param String documentName the name of the text file to be spell-checked
   *  @param Hashtable wordTable the name of the Hashtable containing 
   *                   the dictionary
   *  @return LinkedList the list of all misspelled words in the given filename
   *
   */
  
  public void checkSpelling(String documentName)  {
    int count = 0;   // keeps track of total number of words in the document
    String word, exactword;
    try {
      // set up reading words from a textfile
      Scanner reader = new Scanner(new File(documentName));
      
      // process words from the textfile
      while (reader.hasNext()) {
        String s = reader.nextLine(); // holds a document line at a time 
        String [] textline = s.split(" ");
        for (int i = 0; i< textline.length; i++) {
          exactword = textline[i]; // get the next word
          word = exactword.toLowerCase(); // the dictionary is lowercase
          // search for word in current wordTable
          if (dictionary.searchWord(word)) {
            //System.out.println(word + " is spelled correctly");
          } else{ 
            // new word not yet in misspelled list
            if (!(foundAlready(word))) {
              //System.out.println(exactword + " is MISspelled ");
              misspelledWords.add(exactword);
              count++;
            }
          } // misspelled word
        } // for loop
      } // while loop
      reader.close();
      //System.out.println("Document "+ documentName +" contains " + count + " misspelled words:");
      //System.out.println(misspelledWords);
    } catch (IOException e) {
      System.out.println("Error checking words in "+documentName+" against dictionary");
    }
  }
  
  public static void main (String[] args)  throws IOException {
    //e.g. String args[0] = "EnglishWords.txt";
    //e.g. String args[1] = "AllYouNeedIsLove.txt";
    Speller speller = new Speller("dictionary.txt"); //default dictionary
    
    if (args.length == 2) {
      speller = new Speller(args[0]);
      speller.checkSpelling(args[1]);
    } else if (args.length == 1) { 
      speller.checkSpelling(args[0]);
    } else {
      String usage = "Usage: >> Speller \"dictionary.txt\"  \"document.txt\"\n";
      usage += "  or   >> Speller \"document.txt\"\n";
      //System.out.println(usage);
    }
    
  }// main
} // Speller class
