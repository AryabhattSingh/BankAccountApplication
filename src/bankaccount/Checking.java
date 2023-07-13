package bankaccount;

public class Checking extends Account {

	private String debitCardNumber = "";
	private int debitCardPIN;

	public Checking() {
		//super() call by the compiler
		accountType = "Checking";
		String selectStr = "1234567890";
		for (int i = 1; i <= 12; i++) {
			debitCardNumber = debitCardNumber + selectStr.charAt((int)(Math.random() * selectStr.length()));
		}

		debitCardPIN = (int) (Math.random() * 10000);

		setAccountNumber();
		setInterestRate();
	}

	public Checking(String name, String socialSecurityNumber, String accountType, float initialDeposit) {
		super(name, socialSecurityNumber, accountType, initialDeposit);
		String selectStr = "1234567890";
		for (int i = 1; i <= 12; i++) {
			debitCardNumber = debitCardNumber + selectStr.charAt((int)(Math.random() * selectStr.length()));
		}

		debitCardPIN = (int) (Math.random() * 10000);

		setAccountNumber();
		setInterestRate();

	}


	void showInfo() {
		super.showInfo();

		System.out.println("Available Balance : " + balance);
		System.out.println("Account Type : " + accountType);
		System.out.println("Interest Rate : " + interestRate);
		System.out.println("Debit Card Number : " + debitCardNumber);
		System.out.println("Debit Card PIN    : " + debitCardPIN);
		System.out.println("*************************");
	}

	@Override
	public void setInterestRate() {
		interestRate = (float) (0.15 * baseInterestRate);

	}

}
