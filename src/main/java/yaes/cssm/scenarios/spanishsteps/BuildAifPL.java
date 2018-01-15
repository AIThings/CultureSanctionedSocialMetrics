package yaes.cssm.scenarios.spanishsteps;

import java.util.HashMap;
import java.util.Map;

import yaes.cssm.impactfunctions.AifPL;
import yaes.cssm.impactfunctions.AifPLFactory;
import yaes.cssm.impactfunctions.ParameterizedLogistic;

/**
 * Building the AifPLs for various actions and specific CSSMs
 * 
 */
public class BuildAifPL {

	/**
	 * Returns the value for the politeness.
	 * 
	 * @param loudness
	 *            - the loudness parameter
	 * @param offensiveness
	 *            - the offensiveness parameter
	 * @param cb
	 *            - the value of CB(agreed)
	 * @return
	 */
	public static double A10_Politeness(double loudness, double offensiveness,
			double cb) {
		AifPL aif = aif_A10_Politeness();
		Map<String, Double> map = new HashMap<>();
		map.put("b", cb);
		map.put("x", loudness);
		map.put("y", offensiveness);
		map.put("1", 1.0);
		double retval = aif.getValue(map);
		return retval;
	}

	/**
	 * Builds an AIF for the politeness (generic) for the action A10
	 * 
	 * This AifPL depends on 3 different values: b the CB(agreed), x the
	 * loudness and y the politeness
	 * 
	 * <ul>
	 * <li>the different values of the b = CB(agreed), i.e. the client knows it
	 * did not agree to anything etc.
	 * <li>the different values of the loudness - the client and spouse hears
	 * the message at all loudness levels
	 * <ul>
	 * 
	 * FIXME: it is an early version, must be tweaked
	 * 
	 * @return
	 */
	public static AifPL aif_A10_Politeness() {
		ParameterizedLogistic pl = null;
		//
		// The belief scale
		//
		ParameterizedLogistic beliefScale = new ParameterizedLogistic("b", 1.0,
				0.65, 8.0);
		AifPL aifBeliefScale = new AifPL("BeliefScale");
		aifBeliefScale.addTerm(beliefScale);

		//
		// The negative leg (rudeness)
		//
		AifPL aifNegativeLeg = new AifPL("Negative leg");
		pl = new ParameterizedLogistic("y", -1.0, 0.95, 15.0);
		aifNegativeLeg.addTerm(pl);
		//
		// The negative leg is scaled by rudeness
		//
		AifPL aifBeliefNegative = aifNegativeLeg.multiply(aifBeliefScale);
		//
		// The positive leg (politeness)
		//
		AifPL aifPositiveLeg = new AifPL("Positive leg");
		pl = new ParameterizedLogistic("y", -0.8, 0, 15.0);
		aifPositiveLeg.addTerm(pl);
		pl = AifPLFactory.getConstant(0.8);
		aifPositiveLeg.addTerm(pl);
		//
		// Multiplicative factor on loudness x
		//
		AifPL aifX = new AifPL("loudness");
		AifPLFactory.addParameterIdentity("x", aifX);
		//
		// Now assemble them together
		//
		AifPL aifBY = aifPositiveLeg.add(aifBeliefNegative);
		AifPL aif = aifBY.multiply(aifX);
		// TextUi.println(aif);
		return aif;
	}

	/**
	 * Returns the value for the dignity
	 * 
	 * @param loudness
	 *            - the loudness parameter
	 * @param offensiveness
	 *            - the offensiveness parameter
	 * @param cb
	 *            - the value of CB(agreed)
	 * @return
	 */
	public static double A10_Dignity(double loudness, double offensiveness,
			double cb) {
		AifPL aif = aif_A10_Dignity();
		Map<String, Double> map = new HashMap<>();
		map.put("b", cb);
		map.put("x", loudness);
		map.put("y", offensiveness);
		map.put("1", 1.0);
		double retval = aif.getValue(map);
		return retval;
	}

	/**
	 * Builds an AIF for the dignity (generic) for the action A10
	 * 
	 * This AifPL depends on 3 different values: b the CB(agreed), x the
	 * loudness and y the politeness
	 * 
	 * The difference between the personal / peer and public version is based on
	 * 
	 * <ul>
	 * <li>the different values of the b = CB(agreed), i.e. the client knows it
	 * did not agree to anything etc.
	 * <li>the different values of the loudness - the client and spouse hears
	 * the message at all loudness levels
	 * <ul>
	 * 
	 * FIXME: this is currently the same function as the one for the politeness
	 * - it must be changed in such a way that the yelling will impact the
	 * dignity...
	 * 
	 * @return
	 */
	public static AifPL aif_A10_Dignity() {
		ParameterizedLogistic pl = null;
		//
		// The impact of belief: if we don't believe that a transaction had been
		// agreed upon, this tempers the loss of dignity
		// It should be 0.5 at 0 and about 1 at 1
		//
		ParameterizedLogistic beliefScale = new ParameterizedLogistic("b", 1.0,
				0.0, 4.0);
		AifPL aifBeliefScale = new AifPL("BeliefScale");
		aifBeliefScale.addTerm(beliefScale);
		//
		//  The impact of rudeness on the dignity: similar curve to the politeness
		//  only starts to impact about 0.6
		//
		AifPL aifRudeness = new AifPL("Rudeness");
		pl = new ParameterizedLogistic("y", -1.0, 0.95, 15.0);
		aifRudeness.addTerm(pl);
		//
		//  The impact of yelling on the dignity: only starts at about 0.8
		//
		AifPL aifYelling = new AifPL("Yelling");
		pl = new ParameterizedLogistic("x", -10.0, 1.2, 15.0);
		aifYelling.addTerm(pl);
		//
		//  Assembling it together
		//
		AifPL aifNegative = aifYelling.add(aifRudeness);
		AifPL aif = aifNegative.multiply(aifBeliefScale);
		return aif;
	}

	/**
	 * Using Sigmoid Logistic functions.
	 * 
	 * @param x
	 * @return
	 */
	public static double aifplMultiplicativePublic(double x) {
		AifPL aif = new AifPL("epistemicMultiplicativePublic");
		ParameterizedLogistic pl = new ParameterizedLogistic("x", 0.5, 0.87,
				15.0);
		aif.addTerm(pl);
		pl = new ParameterizedLogistic("x", 0.5, 0.6, 10.0);
		aif.addTerm(pl);
		return getAifWithOneValue(aif, x);

	}

	/**
	 * Using Sigmoid Logistic functions.
	 * 
	 * @param x
	 * @return
	 */
	public static double aifplMultiplicativePeer(double x) {
		AifPL aif = new AifPL("epistemicMultiplicativePeer");
		ParameterizedLogistic pl = new ParameterizedLogistic("x", 1, 0.105,
				2000.0);
		aif.addTerm(pl);
		return getAifWithOneValue(aif, x);
	}

	/**
	 * Using Sigmoid Logistic functions.
	 * 
	 * @param x
	 * @return
	 */
	public static double aifplDignityOfOffensiveness(double x) {
		AifPL aif = new AifPL("impactDignityOfOffensiveness");
		ParameterizedLogistic pl = new ParameterizedLogistic("x", -1.0, 0.95,
				15.0);
		aif.addTerm(pl);
		return getAifWithOneValue(aif, x);
	}

	/**
	 * Returns the v
	 * 
	 * @param aif
	 * @param x
	 * @return
	 */
	public static double getAifWithOneValue(AifPL aif, double x) {
		int count = aif.getParameterCount();
		double val[] = new double[count];
		for (int i = 0; i != count; i++) {
			val[i] = x;
		}
		return aif.getValue(val);

	}
}
