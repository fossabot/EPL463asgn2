package atm;
/**
 * Abstract class for Factories (abstract factory design pattern)
 * @author mioann47
 *
 */
public abstract class AbstractFactory {
	
	
	public abstract StatementType createStatements(String statementType,
			Account account);
}
