/** 
 * Account.java
 * Purpose: Defines the Abstract Account Class for a bank account
 * Written by: Diana Eastman
 * Modified date: February 19, 2013
 * Methods:
 * Abstract method withdraw() - to be implemented in inheriting classes
 * method toString()
 * method getBalance()
 * method getAccountType()
 * final method deposit()
 */

import java.text.NumberFormat;  //used to format output
import java.util.*; //used for Random number generation

public abstract class Account {
  private int accountNumber;
  protected double balance;
  protected String type;
  
  // Get currency and percent instances to use in formatting printed values
  protected static NumberFormat fmtCurrency = NumberFormat.getCurrencyInstance();
  protected static NumberFormat fmtPerCent = NumberFormat.getPercentInstance();
  
  /**
   *   Account Constructor
   *   @param balance  the balance of the account-required to set up an account
   *   @param type     the type of account (Savings Account or Checking Account)
   *   @exception      IllegalArgumentException thrown if object constructed with negative balance
   *   Exceptions are needed here to ensure proper construction of Account object. The other way to
   *   accomplish this would be to take in the parameters, interactively, from the user by way of the
   *   Scanner class. Since this program did not have to be interactive, I do the validation here.
   */
  public Account(double balance, String type) 
  {
    this.balance = balance;
    this.type = type;
    if (this.balance <= 0) 
    {
      throw new IllegalArgumentException(
          "Balance out of range: " + this.balance);
    }
    Random rand = new Random();
    int min =1000000;
    int max =9999999;
    accountNumber = rand.nextInt(max - min + 1) + min;
  }
  
  /**
   *   Final deposit method
   *   Deposit must be greater than zero
   */
  protected final void deposit(double amount) 
  { 
    if (amount > 0) 
    {
    balance += amount;
    }
    else 
    {
    System.out.println("You've tried to deposit an invalid amount. Transaction cancelled.");
    }
  }
  
  /**
   *   Account balance getter method
   *   @return double balance
   */
  public double getBalance() 
  {
   return balance;
  }
  
  /**
   *   Account type getter method (Checking or Savings)
   *   @return String account type 
   */
  public String getAccountType() 
  {
   return type;
  }
  
  /**
   *    Method abstract withdraw() 
   *    To be implemented by the inheriting class.
   */
  abstract public void withdraw(double amount);
  
  /**
   *  Method to Display the Class
   *  @return String
   */
 public String toString() {
     String result = "";
     result += ("******************************************************************************" + "\n");
     result += (type + " # " + accountNumber + " has balance: " + fmtCurrency.format(getBalance()));
     return result;
  }
} // End Abstract Account Class
