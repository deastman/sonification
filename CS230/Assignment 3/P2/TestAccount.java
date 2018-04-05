/** 
 * TestAccount.java
 * Purpose: Test Account.java, CheckingAccount.java and SavingsAccount.java
 * Written by: Diana Eastman
 * Modified date: February 19, 2013
 */

import java.text.NumberFormat;  //used to format output

public class TestAccount {
  protected static NumberFormat fmtCurrency = NumberFormat.getCurrencyInstance();
  
  public static void main(String[ ] args) 
  {
    /* CheckingAccount Class */
    CheckingAccount wendyWellesley= new CheckingAccount(300, "Checking Account"); 
    System.out.println("The initial balance of Wendy Wellesley's " + wendyWellesley.getAccountType() + " is: " + 
                       fmtCurrency.format(wendyWellesley.getBalance()));
    
    wendyWellesley.withdraw(100.5);
    System.out.println("The balance of Wendy Wellesley's " + wendyWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(wendyWellesley.getBalance()) + " after withdrawing $100.50");
    
    wendyWellesley.withdraw(-100.5);
    System.out.println("The balance of Wendy Wellesley's " + wendyWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(wendyWellesley.getBalance()) + " after trying to withdraw $-100.50");
    
    wendyWellesley.deposit(25);
    System.out.println("The balance of Wendy Wellesley's " + wendyWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(wendyWellesley.getBalance()) + " after depositing $25");
    
    wendyWellesley.withdraw(400);
    System.out.println("The balance of Wendy Wellesley's " + wendyWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(wendyWellesley.getBalance()) + " after withdrawing $400 " +
                       "and incurring an overdraft fee of " + fmtCurrency.format(wendyWellesley.getoverdraftFee()));
    
    System.out.println(wendyWellesley); //Print instance (Wendy Wellesley) of Checking Account object
    
    /* SavingsAccount Class */
    SavingsAccount winnieWellesley= new SavingsAccount(100, "Savings Account"); 
    System.out.println("The initial balance of Winnie Wellesley's " + winnieWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(winnieWellesley.getBalance()));
    
    winnieWellesley.withdraw(100.5);
    System.out.println("The balance of Winnie Wellesley's " + winnieWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(winnieWellesley.getBalance()) + " after attempting to withdraw $100.50");
    
    winnieWellesley.deposit(3000.5);
    System.out.println("The balance of Winnie Wellesley's " + winnieWellesley.getAccountType() + " is " + 
                       fmtCurrency.format(winnieWellesley.getBalance()) + " after depositing $3000.50");
    
    winnieWellesley.addInterest(); //Simulate end of month and add interest
    System.out.println("After one month, Winnie Wellesley's " + winnieWellesley.getAccountType() + " earned " +
                       fmtCurrency.format(winnieWellesley.getInterest()) + " in interest");
    
    System.out.println(winnieWellesley); //Print instance (Winnie Wellesley) of Savings Account object 
    
    /* Test border cases */
    CheckingAccount zeroCase= new CheckingAccount(0, "Checking Account"); 
    CheckingAccount negativeCase= new CheckingAccount(-100, "Savings Account"); 
       
  }//End main()
  
} //End TestAccount