import java.util.*;
import java.net.*;
import java.io.*;


public class URLCollection {
  
  private LinkedList<MyURL> urls;
  private static final String inFileName = "url.txt";
 
  public URLCollection(String url, int count) {
    MyURL testclass = new MyURL(url, count);
    urls = new LinkedList<MyURL>();
    urls.add(testclass);
  }
  
  //From FileOps.java, written by Takis Metaxas//
  public static void readWebPage (String urlName) {
    try {
      int count = 0;
      URL u = new URL(urlName);
      BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
      String line = reader.readLine();  // Read the first line of the web page 
      while (line != null) {  // Line becomes null at end of web page  
        count++;
        line = reader.readLine();// Read the next line of the web page
      }
      URLCollection test = new URLCollection(urlName, count);
      reader.close();
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }
  
  public static void readFile(String inFileName) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inFileName));
      String line = reader.readLine();// Read the first line of the file.
      while (line != null) {  // Line becomes null at end of file
        URLCollection.readWebPage(line);
        line = reader.readLine();// Read the next line of the file
      }
      reader.close();
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }

  public String toString() {
    String n = "";
    for (int i=0; i < urls.size(); i++) {
     n += urls.get(i).getAddress() + ":"+ " " + urls.get(i).getNumlines();
    }
    return n;
  }

  public static void main (String args[]) {
    URLCollection.readFile(inFileName);
 
    
  }
  
  
}