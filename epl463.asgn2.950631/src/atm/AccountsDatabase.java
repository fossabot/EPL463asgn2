package atm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to store Accounts using ArrayList. Accounts are read from file
 * (accounts.txt).
 * 
 * @author Modes
 *
 */
public class AccountsDatabase extends ArrayList<Account> {

	private static final long serialVersionUID = 1L;
	/**
	 * account file
	 */
	private String file = "accounts.txt";

	/**
	 * singleton instance
	 */
	private static AccountsDatabase instance = null;

	/**
	 * private constructor
	 */
	private AccountsDatabase() {
		readAccountsFromTXT(file);
	}

	/**
	 * Get unique singleton instance method
	 * 
	 * @return instance of AccountsDatabase
	 */
	public static AccountsDatabase getAccountsDatabase() {
		if (instance == null)
			instance = new AccountsDatabase();
		return instance;
	}

	/**
	 * Method to read accounts from .txt file and add them to ArrayList
	 * 
	 * @param filepath
	 */
	private void readAccountsFromTXT(String filepath) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filepath));
			while (sc.hasNextLine()) {

				String accLine = sc.nextLine();
				if (accLine.equals(""))
					continue;
				accLine = accLine.replace("Account Number:", "");
				String nameLine = sc.nextLine();
				if (nameLine.equals(""))
					continue;
				nameLine = nameLine.replace("Name:", "");
				String surnameLine = sc.nextLine();
				if (surnameLine.equals(""))
					continue;
				surnameLine = surnameLine.replace("Surname:", "");
				String pinLine = sc.nextLine();
				if (pinLine.equals(""))
					continue;
				pinLine = pinLine.replace("PIN:", "");
				String accountTypeLine = sc.nextLine();
				if (accountTypeLine.equals(""))
					continue;
				accountTypeLine = accountTypeLine.replace("Account Type:", "");
				String balanceLine = sc.nextLine();
				if (balanceLine.equals(""))
					continue;
				balanceLine = balanceLine.replace("Balance:", "");
				String transactionHistoryLine = sc.nextLine();
				if (transactionHistoryLine.equals(""))
					continue;
				transactionHistoryLine = transactionHistoryLine.replace("Transactions History:", "");
				Account toAdd = new Account(accLine, nameLine, surnameLine, pinLine, accountTypeLine, balanceLine,
						transactionHistoryLine);

				super.add(toAdd);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("File "+filepath+" not found");
			
		} finally {
			if (sc != null)
				sc.close();
		}

	}

	/**
	 * Method to return account based on account number
	 * 
	 * @param AccountNumber
	 *            account number
	 * @return account
	 */
	public Account getAccount(String AccountNumber) {
		for (Account acc : instance) {
			if (acc.getAccountNumber().equals(AccountNumber))
				return acc;
		}

		return null;

	}

	/**
	 * Add only if account doesn't exists in ArrayList, else don't add.
	 * @return true if added, false if not
	 */
	@Override
	public boolean add(Account newAcc) {
		for (Account acc : instance) {
			if (acc.getAccountNumber().equals(newAcc.getAccountNumber()))
				return false;
		}
		super.add(newAcc);
		return true;
	}

}
