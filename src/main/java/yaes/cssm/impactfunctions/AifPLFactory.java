package yaes.cssm.impactfunctions;

/**
 * Factory class for adding parameterizes impact functions to an AIF
 * 
 * @author Lotzi
 * 
 */
public class AifPLFactory {

	/**
	 * Adds the identity function over its parameters
	 * 
	 * This will be two terms of one factor each
	 * 
	 * The parameters passed must be (x, 1)
	 * 
	 * FIXME: discuss in what range will this work
	 * 
	 * @return
	 */
	public static void addParameterIdentity(String label, AifPL aif) {
		// this creates an extended 45% angle
		ParameterizedLogistic pl = new ParameterizedLogistic(label, 50, 0, 0.08);
		aif.addTerm(pl);
		// shift term, brings it to 0 (by putting the shift at -100
		// in all the area of interest for us, this is a constant
		pl = new ParameterizedLogistic("1", -25, -100, 100);
		aif.addTerm(pl);
	}

	/**
	 * Gets a constant PL
	 * 
	 * @param constant
	 * @return
	 */
	public static ParameterizedLogistic getConstant(double constant) {
		ParameterizedLogistic pl = new ParameterizedLogistic("1", constant,
				-100, 100);
		return pl;
	}

	/**
	 * Adds a constant term
	 * 
	 * The parameters passed must be (1)
	 * 
	 * @param aif
	 */
	public static void addConstant(AifPL aif, double constant) {
		aif.addTerm(getConstant(constant));
	}

}
