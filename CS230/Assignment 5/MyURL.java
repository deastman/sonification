import java.net.*;
import java.io.*;

public class MyURL implements Comparable<MyURL> {
  
  private String address;
  private int numlines;
  
  public MyURL(String addr, int count) {
    address = addr;
    numlines = count;
  }
  
  public String getAddress() {
   return address; 
  }
  
  public int getNumlines() {
    return numlines;
  }
  
  public int compareTo(MyURL another) {
    if (this.numlines == another.numlines)
      return 0;
    else if (this.numlines > another.numlines)
      return 1;
    else return -1;
  }
  
  public String toString() {
   String result = "";
   result += address + ":" + " " + numlines;
   return result;
  }
  
  public static void main (String args[]) {
   //URL google = new URL("www.google.com", 16);
   //URL yahoo = new URL("www.yahoo.com", 28);
   //System.out.println(google);
   //int check = google.compareTo(yahoo);
   //System.out.println(check);
  }
}