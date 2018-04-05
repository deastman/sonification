import java.util.*;
import javax.swing.JOptionPane;

public class Vigenere {
  
  private String message;
  private String password;
  private boolean encrypted;
  private int [] shiftArray; //Not sure if this should be an instance variable
  
  public Vigenere(String msg, String pw){
   message = msg.replaceAll("\\s","").toUpperCase();
   password = pw.replaceAll("\\s","").toUpperCase();
   encrypted = false;
   shiftArray = new int[message.length()];
  }
  
  public void encrypt() {
    if (!encrypted)
    {
      String masked = "";
      for (int i=0; i < message.length(); i++) {
        shiftArray[i] =((message.charAt(i)-'A') + password.charAt(i%password.length())-'A')%26;
        masked = masked + String.valueOf((char)(shiftArray[i]+'A'));
    } 
      encrypted = true;
      message = masked;
      System.out.println(message);
    }
  }
  
  public String decrypt() {
    if (encrypted)
    {
      String unmasked = ""; 
      for (int i=0; i < message.length(); i++) {
        shiftArray[i] =((message.charAt(i)+'A') - password.charAt(i%password.length())+'A')%26;
        unmasked = unmasked + String.valueOf((char)(shiftArray[i]+'A'));
      }
      encrypted = false;
      message = unmasked;
      System.out.println(message);
    }
    return message;
  }
 
  //Integer equivalent of Unicode A - Z is 65 - 90
  //Determine the integer equivalent of the ASCII/Unicode character 
  //Subtract 65 from the integer equivalent to obtain the number of shifts
  //We could also substract 'A' and accomplish the same result
  
  //Two issues: one is looping back to A if shifts go beyond Z
  //The other is looping back through the password if the number of letters in the password is
  //fewer than the number of letters in the message
  
  public static void main (String args[]){
    int again;
    do {
      String msg = JOptionPane.showInputDialog ("What is the message?");
      String pwd = JOptionPane.showInputDialog ("What is the password?");
      Vigenere mymessage = new Vigenere(msg, pwd);
      
      mymessage.encrypt();
      JOptionPane.showMessageDialog (null, mymessage.message);
      int decryptMsg;
      decryptMsg = JOptionPane.showConfirmDialog (null, "Would you like your message decrypted?");
      if (decryptMsg == JOptionPane.YES_OPTION) {
        mymessage.decrypt();
        JOptionPane.showMessageDialog (null, mymessage.message);
      }
      again = JOptionPane.showConfirmDialog (null, "Do Another?");
    } while (again == JOptionPane.YES_OPTION);
  }
 }