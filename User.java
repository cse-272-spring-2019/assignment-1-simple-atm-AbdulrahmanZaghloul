public class User {
	private int cardNumber;
	private String name;
	private double balance;
	private int password;

	public User(int cardNumber, String name, double balance,  int password) {
		this.cardNumber = cardNumber;
		this.name = name;
		this.balance = balance;
		this.password = password;
	}
	
	public User(int cardNumber, int password) {
		this.cardNumber = cardNumber;
		this.password = password;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}
}
