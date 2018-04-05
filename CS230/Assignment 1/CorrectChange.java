/* 
 CorrectChange.java
 Purpose: Output the minimum, correct amount of change given a randomly generated cost
 CS230 Homework Assignment 1
 Written by: Diana Eastman
 Modified date: Ferbruary 1, 2013
*/

import java.util.*;
import java.io.*; 

public class CorrectChange {
  public static void main (String[] args) {
    
    // Declarations and setup
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();  
    int [] currencyArray = {1000, 500, 100, 25, 10, 5, 1}; // Expressed in cents: $10, $5, $1, $0.25, $0.10, $0.01
    String [][] currencyNames = { {"Hamilton","Hamiltons"}, {"Lincoln", "Lincolns"}, {"Washington", "Washingtons"}, 
      {"Quarter", "Quarters"}, {"Dime", "Dimes"}, {"Nickel", "Nickels"}, {"Penny", "Pennies"} };
    int [] changeArray = new int[7]; // Empty array to store change of each currency type
    // Generate a random integer for the item's cost, up to $1000. 
    int cost = rand.nextInt(100000); // Numbers for testing: -20, 0, 4394832
    double paid;
    int change;
    
    // Validate the user's input and stop execution if the following criteria are NOT met:
    //  1. The input must be an integer
    //  2. The input must be greater than or equal to the item cost (this compensates for possible negative change)
    
    System.out.println("Welcome to the Wellesley College vending machine! The cost of your item is $" 
                        + (double)cost/100 + ".");
    
    do {
       System.out.println("Please enter enough money to pay for this item."); 
       
       while (!sc.hasNextDouble()) {
         System.out.println("Sorry, this doesn't look like a valid number. Please try again.");
         sc.next(); 
       }
       
       paid = sc.nextDouble();
        
     } while (paid*100 < cost);
    
     // If above criteria are met, calculate the change owed and print
     change = (int)(paid*100) - cost;    
     System.out.println("You paid $" + paid + "." + "Your change is $" + (double)(change)/100);
     
     // If change equals = mod of change divided by the particular currency, the currency is > than the change and 0
     // is inserted into the changeArray
     for (int i = 0; i < currencyArray.length; i++) {
       if (change == change % currencyArray[i]) {
         changeArray[i]=0;
       }
         
     // Otherwise, we traverse the array and determine the number of each currency denomination needed
       else {
         changeArray[i]=change/currencyArray[i]; // Put appropriate number in changeArray
         change=change-currencyArray[i]*changeArray[i];// Update number with the appropriate subtraction
         System.out.println(changeArray[i]+ " " + ((changeArray[i] == 1) ? currencyNames[i][0] : currencyNames[i][1])); 
       }
     }
      
     // EXTRA CREDIT SECTION
       
     // Create a new array to store the five image names
     String [] imagePaths = {"ten.jpeg", "five.jpeg", "one.jpeg", "quarter.jpg", "dime.jpg", "nickel.jpg", "penny.jpg"}; 
     
     // Use StringBuilder class to build out the skeleton of an HTML file
     StringBuilder sb = new StringBuilder();
     sb.append("<!DOCTYPE html>");
     sb.append("<head>");
     sb.append("<title>Welcome to the Wellesley College Vending Machine");
     sb.append("</title>");
     sb.append("</head>");
     sb.append("<body>");
     sb.append("<img src='http://new.wellesley.edu/sites/default/files/assets/images/wellesleycollege_logo.jpg' style='width: 150px; display: inline;'>");
     sb.append("<h1 style='font-family:Tahoma, san-serif; display: inline;'>Vending Machine</h1>");
     sb.append("<div>");
     sb.append("<h2 style='font-family:Tahoma, san-serif;'>Here's Your Change:</h2>");
       
     // Use nested for-loop to:
     // 1. Traverse change array
     // 2. Print an image source tag for the number of each currency 
     for (int i = 0; i <changeArray.length; i++){
       for (int j = 0; j<changeArray[i]; j++) {
         sb.append("<img src='http://cs.wellesley.edu/~cs230/Other/"+imagePaths[i]+"'>");
       }
     }
          
     sb.append("</div>");
     sb.append("</body>");
     sb.append("</html>");
        
     // Because we are using an I/O object, we must check for possible I/O exceptions
     try {  
          // Use FileWriter class to open new html file 
          FileWriter fstream = new FileWriter("change.html");
          BufferedWriter out = new BufferedWriter(fstream);
          out.write(sb.toString());
          out.close();
     }
     catch (IOException e){
     }
      
  } //End main
}//End class CorrectChange


