package atm;

/**
 * Class to implement Balance Only Statement
 * 
 * @author mioann47
 *
 */
public class BalanceOnly implements StatementType {

	/**
	 * account for statement
	 */
	private Account account;
	/**
	 * Constructor
	 * @param account account
	 */
	BalanceOnly(Account account) {
		setAccount(account);
	}

	/**
	 * print statement
	 */
	public String print() {
		// TODO Auto-generated method stub
		return "Balance: " + getAccount().getBalance();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
