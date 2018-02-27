package atm;
/**
 * Helper class to create a Factory (Using abstract factory)
 * @author Modes
 *
 */
public class AbstractFactoryMaker {
	public static AbstractFactory getStatementFactory(String type) {
		if (type==null) return null;
		AbstractFactory factory = null;
		if (type.equals("StatementFactory")) {
			factory = StatementFactory.getUniqueInstance();
		} else {
			factory = null;
		}
		return factory;
	}

}
