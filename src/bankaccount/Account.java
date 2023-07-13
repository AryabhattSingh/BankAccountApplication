package bankaccount;

import java.util.Scanner;

public abstract class Account implements InterestRateInterface{

	protected String name;
	protected String socialSecurityNumber;
	protected String accountNumber = "";
	protected String accountType;
	protected float initialDeposit = 0;
	protected float balance, interestRate;
	static int acctPosInAcctArrayList = -1;

	static Scanner sc = new Scanner (System.in);

	//-------------------------------Default Constructor------------------
	public Account() {
		System.out.println("Enter Customer Name : ");
		name = sc.nextLine();
		System.out.println("Enter Social Security Number : ");
		socialSecurityNumber = sc.nextLine();
		
		while (initialDeposit < 500) {
			System.out.println("Enter initial deposit : ");
			initialDeposit = sc.nextInt();
			sc.nextLine();
			if (initialDeposit >= 500) {
				balance = initialDeposit;
				break;
			}
			else {
				System.out.println("\nInitial Deposit should NOT be less than 500.");
				balance = 0;
			}
		}
	}// end of default constructor
	//----------------------------------------------------------------------
	
	
	//------------------------------Parameterized Constructor---------------
	public Account(String name, String socialSecurityNumber, String accountType, float initialDeposit) {
		this.name = name;
		this.socialSecurityNumber = socialSecurityNumber;
		this.accountType = accountType;
		balance = initialDeposit;
	}
	//-----------------------------------------------------------------------

	//-----------------------Getters functions-----------------
	public String getAccountNumber() {
		// TODO Auto-generated method stub
		return accountNumber;
	}
	
	public String getName() {
		return name;
	}
	//---------------------------------------------------------
	
	//-----------------------Setter functions------------------
	void setAccountNumber() {
		if (accountType.equals("Savings")) {
			accountNumber += "1";
		}
		if (accountType.equals("Checking")) {
			accountNumber += "2";
		}
		String lastTwoDigitOfSocialSecurity = socialSecurityNumber.substring(socialSecurityNumber.length() - 2);
		accountNumber += lastTwoDigitOfSocialSecurity;
		
		//unique 5 digit number
		int uniqueNumber = (int)(Math.random() * 100000);
		
		//random 3 digit number
		int randomNumber = (int) (Math.random() * 1000);
		
		accountNumber += String.valueOf(uniqueNumber) + String.valueOf(randomNumber);
	}
	//-------------------------------------------------------------

	//-------------------Defining bank functions menu--------------------------------
	static void bankFunctions (Account acc) {
		System.out.println("\n********** WELCOME " + acc.getName() + " **********");
		int choice = 0;

		while (choice != 5) {
			System.out.println("\nSelect one of the options : \n1. Check Info \n2. Deposit Money \n3. Withdraw Money \n4. Transfer Money \n5. EXIT");
			System.out.print("\nEnter your choice : ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1 :
				acc.showInfo();
				break;
			case 2 :
				acc.deposit();
				break;
			case 3 :
				acc.withdraw();
				break;
			case 4 :				
				System.out.print("\nEnter the account number to which you want to transfer money : ");
				String targetAccountNumber = sc.nextLine();
				if (accountNumberExists(targetAccountNumber)) {
					System.out.println("\nTransfer method started...");
					acc.transfer(targetAccountNumber);
				}
				break;
			
			case 5 :
				System.out.println("\n********** EXITING **********");
				System.exit(0);
				break;
			}// end of switch
		}// end of while
	}// end of bankFunctions()
	//--------------------------------------------------------------------
	
	//----------Checking if the bank account number exists or not---------------
	static boolean accountNumberExists(String accountNumber) {
		boolean accountFound = false;
		for (Account acc : BankAccount.accountList) {
			acctPosInAcctArrayList++;
			if (acc.getAccountNumber().equals(accountNumber)) {
				accountFound = true;
				break;
			}
		}

		if (accountFound == false) {
			System.out.println("\nThis Account Number doesn't exists.");
		}

		return accountFound;

	}
	
	//--------------------------Defining various bank functions--------------------------------------------
	void deposit() {
		System.out.println("\nEnter amount to be deposit : ");
		int depositAmount = sc.nextInt();
		balance = balance + depositAmount;
		System.out.println("\n" + depositAmount + " was successfully deposited into your account");
	}

	void withdraw() {
		String choice = "yes";
		while (choice.equals("yes") || choice.equals("y")) {
			System.out.println("\nEnter amount to be withdrawn : ");
			int withdrawAmount = sc.nextInt();
			sc.nextLine();
			if (withdrawAmount <= balance) {
				balance = balance - withdrawAmount;
				System.out.println(withdrawAmount + " was successfully withdrawn from your account.\nPLEASE COLLECT CASH.");
			}
			else {
				System.out.println("\nBalance is less than the entered amount for withdrawal.");
				System.out.println("\nAmount to be withdrawn : " + withdrawAmount);
				System.out.println("Balance                 : " + balance);
				System.out.println("\nDo you wish to withdraw your available balance completely (Yes/No) ? : ");
				choice = sc.nextLine().toLowerCase();
				if (choice.equals("yes") || choice.equals("y")) {
					System.out.println(balance + " was withdrawn from your account");
					balance = 0;
				}
			}

			if (balance == 0) {
				System.out.println("\nBalance is ZERO.\nNo withdrawal is possible");
				break;
			}
			System.out.println("\nDo you wish to make another withdrawal? (Yes/No) :");
			choice = sc.nextLine().toLowerCase();
		}//end of while
	}//end of withdraw

	void transfer(String targetAccountNum) {
		int choice = -1;
		String targetAccountNumber = targetAccountNum;
		float transferAmount = 0f;

		while (choice != 2) {

			// This if will only work
			if (choice == 1 && (transferAmount <= balance) ){
				System.out.println("\nEnter the account number : ");
				targetAccountNumber = sc.nextLine();
				if (accountNumberExists(targetAccountNumber) == false) {
					break;
				}
			}

			System.out.println("Enter the amount to be transfered : ");
			transferAmount = sc.nextFloat();
			if (transferAmount <= balance) {
				System.out.println("\n" + transferAmount + " was successfully transfered to Account Number - " + targetAccountNumber);
				balance -= transferAmount;
				System.out.println("\nDo you wish to make another transfer? (1 for YES / 2 for NO : ");
				choice = sc.nextInt();
				if(choice == 2) {
					break;
				}
				if (choice == 1) {
					System.out.println("\nSelect one of the options : ");
					System.out.println("\n0. Transfer in the previously entered Account Number.");
					System.out.println("1. Transfer in another account.");
					System.out.println("\nEnter your choice : ");
				}
			}
			else {
				System.out.println("\nAvaialble Balance is lower than the Amount To Be Transfered.");
				System.out.println("\nSelect Options :\n1.EDIT the Transfer Amount\n2.CANCEL the Transfer\nEnter your choice : ");
			}
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 2) {
				break;
			}
		}//end of while
	}//end of transfer

	void showInfo() {
		System.out.println("\n**********ACCOUNT DETAILS**********");
		System.out.println("Name : " + name + "\nAccount Number : " + accountNumber);
	}
	//-------------------------------------------------------------------------------------------
	
	//Checking and Savings will have different interest rates. Hence declaring it as abstract.
	//Checking and Saving will have define it separately
	public abstract void setInterestRate(); 
	
}
