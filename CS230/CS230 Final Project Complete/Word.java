/**
 * FILENAME:  Word.java
 * WHO:       
 * WHEN:     
 * WHAT:      
 */

import java.util.*;

public class Word 
{
  private LinkedList<Space> activeSpaces;
  private HashDictionary dictionary;
  private WordDirection wordDirection;
  public static enum WordDirection { UNKNOWN, ACROSS, DOWN };
  private String primaryWord;
  private int start;
  private int end;
  private int p; // Shared position - the row or column that the letters are on
  
  public Word(LinkedList<Space> active)
  {
    activeSpaces = active;
    dictionary = new HashDictionary("dictionary.txt");
  }

  public static final Comparator<Space> X_COMPARATOR = new Comparator<Space>()
  {
    public int compare(Space s1, Space s2) 
    {
     return s2.getxPos()-s1.getxPos();
   }
  };
 
  public static final Comparator<Space> Y_COMPARATOR = new Comparator<Space>()
  {
   public int compare(Space s1, Space s2) 
   {
     return s2.getyPos()-s1.getyPos();
   }
  };
  
 private WordDirection wordDirection()
 {
  int hcount = 0; 
  int vcount = 0;
  int h = activeSpaces.peekFirst().getxPos();
  int v = activeSpaces.peekFirst().getyPos();
  
  for (int i = 0; i < activeSpaces.size(); i++) 
  {
    if(activeSpaces.get(i).getxPos()==h) {
      ++hcount; 
    }
    if (activeSpaces.get(i).getyPos()==v) {
      ++vcount;
    }
  }
  
  if (hcount==activeSpaces.size()) {
    wordDirection = WordDirection.ACROSS;
    Collections.sort(activeSpaces, Y_COMPARATOR);
    start = activeSpaces.getLast().getyPos();
    end   = activeSpaces.getFirst().getyPos();
    p = activeSpaces.getLast().getxPos();
  }
  
  else if (vcount==activeSpaces.size()) {
    wordDirection = WordDirection.DOWN;
    Collections.sort(activeSpaces, X_COMPARATOR);
    start = activeSpaces.getLast().getxPos();
    end   = activeSpaces.getFirst().getxPos();
    p = activeSpaces.getLast().getyPos(); 
  }
  
  else
     wordDirection = WordDirection.UNKNOWN;             
   return wordDirection;                           
 }
 
 public boolean isWord(String w)
 {
   primaryWord = w;
   if(dictionary.searchWord(primaryWord))
     return true;
   else return false;
 }
 
 public WordDirection getWordDirection()
 {
   return wordDirection(); 
 }
  
 public int getStart()
 {
   return start;
 }
 
 public void setStart(int s)
 {
   start = s;
 }
 
 public int getEnd()
 {
   return end; 
 }
  
 public void setEnd(int e)
 {
   end = e;
 }
 
 public int getSharedPos()
 {
   return p;
 }
 
 public void setSharedPos(int pos)
 {
   p = pos;
 }
}