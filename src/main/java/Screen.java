

package main.java;

public class Screen {

 
  public void displayMessage(String message) {
    System.out.print(message);
  }

  
  public void displayMessageLine(String message) {
    System.out.println(message);
  }

 
  public void displayDollarAmount(double amount) {
    System.out.printf("$%,.2f", amount);
  }

  
  public String getMessage(String message) {
    return message;
  }

  // get dollar amount
  public String getDollarAmount(double amount) {
    return String.format("$%,.2f", amount);
  }

}
