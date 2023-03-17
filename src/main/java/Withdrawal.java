

package main.java;

public class Withdrawal extends Transaction {

  private int amount; 
  private Keypad keypad; 
  private CashDispenser cashDispenser; 
  private final static int CANCELED = 6;

 
  public Withdrawal(int userAccountNumber, Screen atmScreen,
                    BankDatabase atmBankDatabase, Keypad atmKeypad,
                    CashDispenser atmCashDispenser) {
    
    super(userAccountNumber, atmScreen, atmBankDatabase);
    keypad = atmKeypad;
    cashDispenser = atmCashDispenser;
  }

  
  public void execute() {
    boolean cashDispensed = false; 
    double availableBalance; 
    BankDatabase bankDatabase = getBankDatabase();
    Screen screen = getScreen();
    
    do {
      amount = displayMenuOfAmounts();
      if (amount != CANCELED) {
        availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        if (amount <= availableBalance) {
          if (cashDispenser.isSufficientCashAvailable(amount)) {
            bankDatabase.debit(getAccountNumber(), amount);
            cashDispenser.dispenseCash(amount); 
            cashDispensed = true; 
            screen.displayMessageLine("[i] Your cash has been dispensed.\n" +
                                      "    Please take your cash now.");
          } else { 
            screen.displayMessageLine("[!] Insufficient cash available in the ATM.\n" +
                                      "    Please choose a smaller amount.");
          }
        } else { 
          screen.displayMessageLine("[!] Insufficient funds in your account!\n" +
                                    "    Please choose a smaller amount.");
        }
      } else { 
        screen.displayMessageLine("[~] Canceling transaction...");
        return; 
      }
    } while (!cashDispensed);
  }


  private int displayMenuOfAmounts() {
    int userChoice = 0; // local variable to store return value
    Screen screen = getScreen(); // get screen reference
    // array of amounts to correspond to menu numbers
    int amounts[] = {0, 20, 40, 60, 100, 200};
    // loop while no valid choice has been made
    while (userChoice == 0) {
      // display the menu
      screen.displayMessageLine("\n[Withdrawal Menu]");
      screen.displayMessageLine("1 - $20");
      screen.displayMessageLine("2 - $40");
      screen.displayMessageLine("3 - $60");
      screen.displayMessageLine("4 - $100");
      screen.displayMessageLine("5 - $200");
      screen.displayMessageLine("6 - Cancel transaction");
      screen.displayMessage("[?] Choose a withdrawal amount: ");
      int input = keypad.getInput(); 
      switch (input) {
        case 1: 
        case 2: 
        case 3: 
        case 4:
        case 5:
          userChoice = amounts[input]; 
          break;
        case CANCELED: 
          userChoice = CANCELED; 
          break;
        default: 
          screen.displayMessageLine("[!] Invalid selection. Try again.");
      }
    }
    return userChoice; 
  }

}
