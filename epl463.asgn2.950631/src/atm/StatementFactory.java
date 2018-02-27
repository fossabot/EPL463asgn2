package atm;

/**
 * Factory for creating Statements
 * 
 * @author mioann47
 *
 */
public class StatementFactory extends AbstractFactory{

	/**
	 * StatementFactory singleton instance
	 */
	private static StatementFactory uniqueInstance = null;

	/**
	 * Empty and private constructor
	 */
	private StatementFactory() {
	}

	/**
	 * Returns the Statement Type based on the given String (@param statementType)
	 * 
	 * @param statementType
	 *            statement type
	 * @param account
	 *            account to make statement from
	 * @return correct StatementType
	 */
	@Override
	public StatementType createStatements(String statementType, Account account) {
		if (statementType.equals("Detailed Statement"))
			return new DetailedStatement(account);
		else if (statementType.equals("Mini Statement"))
			return new MiniStatement(account);
		else if (statementType.equals("Balance Only"))
			return new BalanceOnly(account);
		else
			return null;
	}

	/**
	 * Get unique singleton instance method
	 * 
	 * @return instance of Statement Factory
	 */
	public static StatementFactory getUniqueInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new StatementFactory();

		return uniqueInstance;

	}

}
