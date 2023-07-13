package bankaccount;

public class Savings extends Account {

	protected int safetyDepositBoxNumber; // 3 digits random number
	private int safetyDepositBoxAccessCode; // 4 digit code
	
	public Savings() {
		//super() call by the compiler
		safetyDepositBoxNumber =  (int)(Math.random() * 1000);
		safetyDepositBoxAccessCode = (int) (Math.random() * 10000);
		accountType = "Savings";
		setAccountNumber();
		setInterestRate();
	}
	
	public Savings(String name, String socialSecurityNumber, String accountType, float initialDeposit) {
		super(name, socialSecurityNumber, accountType, initialDeposit);
		safetyDepositBoxNumber =  (int)(Math.random() * 1000);
		safetyDepositBoxAccessCode = (int) (Math.random() * 10000);
		setAccountNumber();
		setInterestRate();
	}

	void showInfo() {
		super.showInfo();
		
		System.out.println("Available Balance : " + balance);
		System.out.println("Account Type : " + accountType);
		System.out.println("Interest Rate : " + interestRate);
		System.out.println("Safety Deposit Box Number : " + safetyDepositBoxNumber);
		System.out.println("Safety Deposit Access Code : " + safetyDepositBoxAccessCode);
		
		System.out.println("*************************");
	}



	@Override
	public void setInterestRate() {
		interestRate = (float) (baseInterestRate - 0.25);
	}
	
	
	
}
