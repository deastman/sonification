/**
 * CheckingAccount.java
 * Purpose: Defines the Class for a Checking Account (extends Account.java)
 * Written by: Diana Eastman
 * Modified date: February 19, 2013
 * Methods:
 * method compareTo()
 * method to String()
 * final method deposit()
 */

//Inherits acccount number and methods deposit() and getbalance() from Parent Account.java

import java.text.DecimalFormat;  //used to format output

public class CheckingAccount extends Account {
  private final double minimumBalance = 50;
  private final double overdraftFee = 25;
  
  /**
   *   Account Constructor
   *   @param balance  the balance of the account; required to set up an account
   *   @param type     the account type (String), Checking or Savings
   */
  public CheckingAccount(double balance, String type) 
  {
    super(balance, type);
  }
  
   /**
   *   Defines Abstract method withdraw from parent Account.java
   *   @param amount  the amount to withdraw
   */
  public void withdraw(double amount) 
  {
    if (amount > 0) { //All withdrawals must be greater than zero or they are invalid
      if (balance - amount < minimumBalance) { //Incur fee if drops below minimumBalance
        balance = balance - amount - overdraftFee;
      }
      else {
        balance -= amount;
      }
    }
    else {
      System.out.println("You've tried to withdraw an invalid amount. Transaction cancelled.");
      amount = 0;
    }
  }
  
  /**
   *   overdraftFee getter method 
   *   @return overdraftFee
   */
  public double getoverdraftFee() 
  {
   return overdraftFee;
  }
  
  /**
   *  Method toString() prints Class - calls the toString() of parent Account.java
   *  @return String
   */
  public String toString() {
    String results = "";
    results += super.toString() + "\n";
    results += "Wellesley College Bank checking accounts have a minimum balance of: " + 
                                 fmtCurrency.format(minimumBalance) + "\n";
    results += ("******************************************************************************" + "\n");
    return results; 
  }
  
  //Testing
  public static void main(String args[]) {
    CheckingAccount c1= new CheckingAccount(100, "Checking Account"); 
    System.out.println("The balance of checking account 1 is: " + c1.getBalance());
    c1.withdraw(10.50);
    System.out.println("The balance of checking account 1 is: " + c1.getBalance() + " after withdrawing $10.50");
    System.out.println(c1);
  }
} // End Checking Account Class
