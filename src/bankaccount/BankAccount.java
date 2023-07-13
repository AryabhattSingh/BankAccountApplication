package bankaccount;

import java.util.ArrayList;
import java.util.Scanner;


//Driver Class - It contains main()
//Account class is parent class for Savings class and Checking class

public class BankAccount {

	static Scanner sc = new Scanner (System.in);

	//This array list will contain all the accounts created either from input csv file or from user input
	static ArrayList<Account> accountList = new ArrayList<>();

	public static void main(String[] args) {

		//This array list is made up of String[]
		//Each String[] represents 1 line from the csv file
		//The indices in a String[] represent values for 1 account creation
		ArrayList<String[]> newBankAccounts = CSVRead.read();

		//Using the above String[] array list to create accounts and adding them in accountList
		for (String[] acctHolder : newBankAccounts) {
			String name = acctHolder[0];
			String socialSecurityNumber = acctHolder[1];
			String accountType = acctHolder[2];
			float initialDeposit = Float.parseFloat( acctHolder[3]);

			if (accountType.equals("Savings")) {
				Savings s = new Savings (name, socialSecurityNumber, accountType, initialDeposit);
				s.showInfo();
				accountList.add(s);

			}
			else if (accountType.equals("Checking")) {
				Checking c = new Checking (name, socialSecurityNumber, accountType, initialDeposit);
				c.showInfo();
				accountList.add(c);

			}
			else {
				System.out.println("ERROR READING ACCOUNT TYPE FROM CSV FILE");
			}
		}// end of for

		//FIRST EVER PROMPT FOR USER
		int choice = 0;

		while (choice != 3) {
			System.out.println("\nWELCOME TO XYZ BANK\n\nSelect one of the following :\n1. OPEN A NEW ACCOUNT\n2. DO A TRANSACTION (Check Info/Deposit/"
					+ "Withdrawal/Transfer \n3. EXIT \nEnter your choice : ");
			choice = sc.nextInt();
			sc.nextLine();

			Savings s1;
			Checking c1;

			if (choice == 1) {
				System.out.println("\nWhat type of account you wish to open?\n1. Savings\n2. Checking\n\nEnter your choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				if (choice == 1) {
					s1 = new Savings();
					//bankFunctions(s1);
					accountList.add(s1);
				}
				else {
					c1 = new Checking();
					//bankFunctions(c1);
					accountList.add(c1);
				}
			}// end of if

			else if (choice == 2) {
				System.out.println("\nEnter your Account Number : ");
				String accountNumber = sc.nextLine();
				if (Account.accountNumberExists(accountNumber)) {
					Account.bankFunctions(accountList.get(Account.acctPosInAcctArrayList));
					break;
				}
			}// end of else if
			else {
				System.exit(0);
			}// end of else
		}// end of while
	}// end of main()
}// end of class