import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	private ArrayList<User> users = new ArrayList<User>();
	private Transaction transaction = new Transaction();

	/**
	 * 
	 */
	public ATM() {
	}
	
	Transaction getTransaction() {
		return this.transaction;
	}
	
	void addUser(User user) {
		users.add(user);
	}

	void deleteUser(User user) {
		users.remove(users.indexOf(user));
	}

	User searchForUser(User user) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getCardNumber() == (user.getCardNumber())
					&& users.get(i).getPassword() == user.getPassword())
				return users.get(i);
		return null;
	}

	boolean withDraw(User user, Double amount) {
		if (user.getBalance() < amount) {
			System.out.println("Error");
			return false;
		} else {
			user.setBalance(user.getBalance() - amount);
			transaction.addTransaction(user, "Withdraw", amount);
			return true;
		}
	}

	void deposit(User user, Double amount) {
		user.setBalance(user.getBalance() + amount);
		transaction.addTransaction(user, "Deposit", amount);
	}
	
	Double getBalance(User user, Double balance ) {
		user.setBalance(user.getBalance());
		transaction.addTransaction(user, "Balance", balance);
		
		
		return balance;
		
	}

	boolean verify(User user, int password) {
		return (user.getPassword() == password);
	}
}