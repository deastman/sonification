/** 
 * SavingsAccount.java
 * Purpose: Defines the Class for a Savings Account (extends Account.java)
 * Written by:  Diana Eastman
 * Modified date: February 19, 2013
 * Methods:
 * public void method withdraw()
 * private void method addInterest()
 * public double method getInterest()
 * method to String()
 */

//Inherits acccount number and methods deposit() and getbalance() from Parent Account.java

public class SavingsAccount extends Account {
  private static final double interestRate = 0.055;
  private double interest;
  
  /**
   *   Account Constructor
   *   @param balance  the balance of the account; required to set up an account
   */
  public SavingsAccount(double balance, String type) 
  {
    super(balance, type);
    interest = 0;
  }
  
  /**
   *   Defines Abstract method withdraw from parent Account.java
   *   @param amount  the amount to withdraw
   */
  public void withdraw(double amount) 
  { 
    if ((balance >= amount) && (amount>0)) { //Savings Accounts have no overdraft fee- balance must be >=amount
      balance = balance - amount;
    }
    else { 
      System.out.println("You've tried to withdraw an invalid amount. Transaction cancelled.");
      amount = 0;
    }
  }
  
  /**
  *  Add monthly interest
  */
  public void addInterest()
  {
     interest = balance * (interestRate/12);
     balance = balance + interest;
  }
  
 /**
  *  Interest getter
  *  @return interest rate
  */
  public double getInterest() 
  { 
    return interest; 
  }
  
  /**
  *  Method toString() prints Class - calls the toString() of parent Account.java
  *  @return String
  */
  public String toString() {
    fmtPerCent.setMaximumFractionDigits(1); // Display interest rate percentage to the tenths place value
    String results = "";
    results += super.toString() + "\n";
    results += "The current interest rate of Wellesley College Bank savings accounts is: " + 
                                 fmtPerCent.format(interestRate)  + "\n";
    results += ("******************************************************************************" + "\n");
    return results; 
  }
  
  //Testing
  public static void main(String args[]) {
    SavingsAccount s1= new SavingsAccount(100, "Savings Account"); 
    System.out.println("The balance of savings account 1 is: " + s1.getBalance());
    s1.deposit(1000);
    s1.withdraw(800);
    s1.withdraw(-25);
    System.out.println("The balance of savings account 1 is: " + s1.getBalance() + " after depositing $1000");
    s1.addInterest();
    System.out.println("The interest you earned at the end of the month is: " + s1.getInterest());
    System.out.println(s1);
  }
} // End Savings Account Class