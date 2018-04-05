/* Username.java
   Purpose: A program that generates Wellesley-like (first letter of first name, 
   followed by no more than 7 letters of last name, all in lowercase) user names, 
   given a user's first name and last name.  
   Modified by: Diana Eastman
   Modified date: February 3, 2013
*/

import java.util.*;

public class Username {
   private String firstname;
   private String lastname;
   private String fcname; // the firstclass-like username
    final int MAX = 8;
    
   //-----------------------------------------------------------------
   //  Constructor. Sets the f and l as firstname and lastname,
   // and computes the fcname with the first character of f
   // and up to the first 7 characters of l
   //-----------------------------------------------------------------
    
   // Validate our input to make sure a string is actually entered for the first and last name
   // to avoid error. This just checks that the string is not null, an empty space, or a string
   // with numbers mixed in (especially important in the later part of the assignment when we generate
   // usernames that will have numbers to avoid dups); the check could be more robust to prohibit other special chars.
  public Username(String f, String l) {
   
   if (f == "" || f == " " || f.matches(".*\\d.*")) {
     throw new IllegalArgumentException("F can't be null, an empty string, or contain numeric values.");
   }           
   if (l == "" || l == " " || l.matches(".*\\d.*")){
     throw new IllegalArgumentException("L can't be null, an empty string, or contain numeric values.");
   }
    
   //Strip trailing and leading whitespaces, and remove dashes--if any--before taking substrings and concatenating 
    firstname = f.trim();
    lastname = l.trim().replaceAll("-","");
    String toSevenCharacters = lastname.substring(0, Math.min(lastname.length(), 7));
    fcname = (firstname.substring(0,1)).toLowerCase()+toSevenCharacters.toLowerCase();

  }

   //-----------------------------------------------------------------
   //  Returns a string description of the Username.
   //-----------------------------------------------------------------
   public String toString()
   {
     return (firstname + " " + lastname + " "  + fcname);
   }
   
   //-----------------------------------------------------------------
   //  Checks for equality between this and another Username and 
   //   returns boolean
   //-----------------------------------------------------------------
   public boolean equals (Username another){
     
     if (fcname.equals(another.fcname)) {
        return true;
     }
     else {
        return false;
     }
     
   }
   
  public static void main (String[] args) {
    Username u1 = new Username ("Kathy", "Lee"); System.out.println(u1);
    Username u2 = new Username ("Jennifer", "Anniston"); System.out.println(u2);
    if (!u2.equals(u1)) System.out.println("u2 and u1 do not have the same fcname");
    Username u3 = new Username ("Kate", "Lee"); System.out.println(u3);
    if (u3.equals(u1)) System.out.println("u3 and u1 have the same fcname");
    Username u4 = new Username ("Jane", "Anniston"); System.out.println(u4);
    if (u4.equals(u2)) System.out.println("u4 and u2 have the same fcname");
    
    // Additional tests
    Username u5 = new Username ("JOANNE", "KANG"); System.out.println(u5);
    Username u6 = new Username ("joanne", "kang"); System.out.println(u6);
    if (u5.equals(u6)) System.out.println("u5 and u6 have the same fcname");
    Username u7 = new Username ("Kathy", "Lee "); System.out.println(u7); //Add a trailing blank
    Username u8 = new Username (" Kathy", "Lee "); System.out.println(u8); //Add leading blank to fname and trailing to lname
    if (u7.equals(u1)) System.out.println("u1 and u7 have the same fcname");
    if (u8.equals(u1)) System.out.println("u1 and u8 have the same fcname");
    Username u9 = new Username ("Andrea", "Java-Bean"); System.out.println(u9);
    
   // Tests to throw exception (nothing prints in console -- can see exception in interaction pane)
    Username u10 = new Username ("", "Lee"); System.out.println(u10);
    Username u11 = new Username (" ", "Lee"); System.out.println(u11);
    Username u12 = new Username ("Kathy", "Lee8"); System.out.println(u12);
  }
}

