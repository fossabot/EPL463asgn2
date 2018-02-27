package atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to represent an Account
 * @author mioann47
 *
 */
public class Account {

	/**
	 * Constructor
	 * 
	 * @param accountNumber
	 * @param name
	 * @param surname
	 * @param pIN
	 * @param accountType
	 * @param balance
	 * @param transactionsHistory
	 */
	public Account(String accountNumber, String name, String surname, String pIN, String accountType, String balance,
			String transactionsHistory) {
		super();
		AccountNumber = accountNumber;
		Name = name;
		Surname = surname;
		PIN = pIN;
		AccountType = accountType;
		Balance = balance;
		setTransHistory(transactionsHistory);
	}

	private String AccountNumber;
	private String Name;
	private String Surname;
	private String PIN;
	private String AccountType;
	private String Balance;
	private ArrayList<String> TransactionsHistory;

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	public ArrayList<String> getTransactionsHistory() {
		return TransactionsHistory;
	}

	public void setTransactionsHistory(ArrayList<String> transactionsHistory) {

		TransactionsHistory = transactionsHistory;
	}

	/**
	 * Splits the transaction history String into ArrayList for easiest use
	 * 
	 * @param transactionsHistory
	 */
	private void setTransHistory(String transactionsHistory) {
		TransactionsHistory = new ArrayList<String>();
		String hist[] = transactionsHistory.split(", ");
		List<String> newList = Arrays.asList(hist);
		this.TransactionsHistory.addAll(newList);

	}

	/**
	 * Convert to string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account Number: " + getAccountNumber() + "\n");
		sb.append("Name: " + getName() + "\n");
		sb.append("Surname: " + getSurname() + "\n");
		sb.append("PIN: " + getPIN() + "\n");
		sb.append("Account Type: " + getAccountType() + "\n");
		sb.append("Balance: " + getBalance() + "\n");
		sb.append("Transactions History: \n");
		for (String trans : getTransactionsHistory()) {
			sb.append(trans + "\n");
		}
		return sb.toString();
	}
}
