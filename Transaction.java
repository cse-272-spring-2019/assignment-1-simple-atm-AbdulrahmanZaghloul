import java.util.ArrayList;


public class Transaction {
	private ArrayList<String> transactions_list = new ArrayList<String>();
	private int pointer = 0;
	public Transaction() {
		
	}

	void addTransaction(User user, String type, double amount) {
		try {this.transactions_list.remove(pointer);}
		catch (Exception e) {
		}
		this.transactions_list.add(pointer, "Username:" + user.getName() + "\nType:" + type + "\nAmount:" + amount);
		this.pointer++;
		this.pointer %= 5;
		System.out.println(this.transactions_list.size());
	}

	Object[] getTransactions() {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = pointer, j = 0; i < transactions_list.size(); i++, j++) {
			ret.add(j, transactions_list.get(i));
		}
		for (int i = 0, j = ret.size(); i < pointer; i++, j++) {
			ret.add(j, transactions_list.get(i));
		}
		return ret.toArray();
	}

	int getPointer() {
		return pointer;
	}
}