package atm;

/**
 * Class to implement MiniStatement
 * 
 * @author mioann47
 *
 */
public class MiniStatement implements StatementType {
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
	MiniStatement(Account account) {
		setAccount(account);
	}

	/**
	 * print statement
	 */
	public String print() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("Account Number: " + getAccount().getAccountNumber() + "\n");
		sb.append("Name: " + getAccount().getName() + "\n");
		sb.append("Surname: " + getAccount().getSurname() + "\n");
		sb.append("Account Type: " + getAccount().getAccountType() + "\n");
		sb.append("Balance: " + getAccount().getBalance() + "\n");
		return sb.toString();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
