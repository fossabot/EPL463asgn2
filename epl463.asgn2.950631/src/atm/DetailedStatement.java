package atm;

/**
 * Class to implement DetailedStatement
 * 
 * @author mioann47
 *
 */
public class DetailedStatement implements StatementType {

	/**
	 * account for statement
	 */
	private Account account;

	/**
	 * Constructor
	 * 
	 * @param account
	 *            account
	 */
	DetailedStatement(Account account) {
		setAccount(account);
	}

	/**
	 * print statement
	 */
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account Number: " + getAccount().getAccountNumber() + "\n");
		sb.append("Name: " + getAccount().getName() + "\n");
		sb.append("Surname: " + getAccount().getSurname() + "\n");
		sb.append("Account Type: " + getAccount().getAccountType() + "\n");
		sb.append("Balance: " + getAccount().getBalance() + "\n");
		sb.append("Transactions History: \n");
		for (String trans : getAccount().getTransactionsHistory()) {
			sb.append(trans + "\n");
		}
		return sb.toString();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
